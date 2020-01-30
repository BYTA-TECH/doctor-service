package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.DoctorApp;
import com.bytatech.ayoos.doctor.config.TestSecurityConfiguration;
import com.bytatech.ayoos.doctor.domain.ContactInfo;
import com.bytatech.ayoos.doctor.repository.ContactInfoRepository;
import com.bytatech.ayoos.doctor.repository.search.ContactInfoSearchRepository;
import com.bytatech.ayoos.doctor.service.ContactInfoService;
import com.bytatech.ayoos.doctor.service.dto.ContactInfoDTO;
import com.bytatech.ayoos.doctor.service.mapper.ContactInfoMapper;
import com.bytatech.ayoos.doctor.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.bytatech.ayoos.doctor.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ContactInfoResource} REST controller.
 */
@SpringBootTest(classes = {DoctorApp.class, TestSecurityConfiguration.class})
public class ContactInfoResourceIT {

    private static final String DEFAULT_FACEBOOK_URL = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_URL = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER_URL = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER_URL = "BBBBBBBBBB";

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private ContactInfoMapper contactInfoMapper;

    @Autowired
    private ContactInfoService contactInfoService;

    /**
     * This repository is mocked in the com.bytatech.ayoos.doctor.repository.search test package.
     *
     * @see com.bytatech.ayoos.doctor.repository.search.ContactInfoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ContactInfoSearchRepository mockContactInfoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restContactInfoMockMvc;

    private ContactInfo contactInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactInfoResource contactInfoResource = new ContactInfoResource(contactInfoService);
        this.restContactInfoMockMvc = MockMvcBuilders.standaloneSetup(contactInfoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactInfo createEntity(EntityManager em) {
        ContactInfo contactInfo = new ContactInfo()
            .facebookURL(DEFAULT_FACEBOOK_URL)
            .twitterURL(DEFAULT_TWITTER_URL);
        return contactInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactInfo createUpdatedEntity(EntityManager em) {
        ContactInfo contactInfo = new ContactInfo()
            .facebookURL(UPDATED_FACEBOOK_URL)
            .twitterURL(UPDATED_TWITTER_URL);
        return contactInfo;
    }

    @BeforeEach
    public void initTest() {
        contactInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactInfo() throws Exception {
        int databaseSizeBeforeCreate = contactInfoRepository.findAll().size();

        // Create the ContactInfo
        ContactInfoDTO contactInfoDTO = contactInfoMapper.toDto(contactInfo);
        restContactInfoMockMvc.perform(post("/api/contact-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the ContactInfo in the database
        List<ContactInfo> contactInfoList = contactInfoRepository.findAll();
        assertThat(contactInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ContactInfo testContactInfo = contactInfoList.get(contactInfoList.size() - 1);
        assertThat(testContactInfo.getFacebookURL()).isEqualTo(DEFAULT_FACEBOOK_URL);
        assertThat(testContactInfo.getTwitterURL()).isEqualTo(DEFAULT_TWITTER_URL);

        // Validate the ContactInfo in Elasticsearch
        verify(mockContactInfoSearchRepository, times(1)).save(testContactInfo);
    }

    @Test
    @Transactional
    public void createContactInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactInfoRepository.findAll().size();

        // Create the ContactInfo with an existing ID
        contactInfo.setId(1L);
        ContactInfoDTO contactInfoDTO = contactInfoMapper.toDto(contactInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactInfoMockMvc.perform(post("/api/contact-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactInfo in the database
        List<ContactInfo> contactInfoList = contactInfoRepository.findAll();
        assertThat(contactInfoList).hasSize(databaseSizeBeforeCreate);

        // Validate the ContactInfo in Elasticsearch
        verify(mockContactInfoSearchRepository, times(0)).save(contactInfo);
    }


    @Test
    @Transactional
    public void getAllContactInfos() throws Exception {
        // Initialize the database
        contactInfoRepository.saveAndFlush(contactInfo);

        // Get all the contactInfoList
        restContactInfoMockMvc.perform(get("/api/contact-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].facebookURL").value(hasItem(DEFAULT_FACEBOOK_URL)))
            .andExpect(jsonPath("$.[*].twitterURL").value(hasItem(DEFAULT_TWITTER_URL)));
    }
    
    @Test
    @Transactional
    public void getContactInfo() throws Exception {
        // Initialize the database
        contactInfoRepository.saveAndFlush(contactInfo);

        // Get the contactInfo
        restContactInfoMockMvc.perform(get("/api/contact-infos/{id}", contactInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contactInfo.getId().intValue()))
            .andExpect(jsonPath("$.facebookURL").value(DEFAULT_FACEBOOK_URL))
            .andExpect(jsonPath("$.twitterURL").value(DEFAULT_TWITTER_URL));
    }

    @Test
    @Transactional
    public void getNonExistingContactInfo() throws Exception {
        // Get the contactInfo
        restContactInfoMockMvc.perform(get("/api/contact-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactInfo() throws Exception {
        // Initialize the database
        contactInfoRepository.saveAndFlush(contactInfo);

        int databaseSizeBeforeUpdate = contactInfoRepository.findAll().size();

        // Update the contactInfo
        ContactInfo updatedContactInfo = contactInfoRepository.findById(contactInfo.getId()).get();
        // Disconnect from session so that the updates on updatedContactInfo are not directly saved in db
        em.detach(updatedContactInfo);
        updatedContactInfo
            .facebookURL(UPDATED_FACEBOOK_URL)
            .twitterURL(UPDATED_TWITTER_URL);
        ContactInfoDTO contactInfoDTO = contactInfoMapper.toDto(updatedContactInfo);

        restContactInfoMockMvc.perform(put("/api/contact-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactInfoDTO)))
            .andExpect(status().isOk());

        // Validate the ContactInfo in the database
        List<ContactInfo> contactInfoList = contactInfoRepository.findAll();
        assertThat(contactInfoList).hasSize(databaseSizeBeforeUpdate);
        ContactInfo testContactInfo = contactInfoList.get(contactInfoList.size() - 1);
        assertThat(testContactInfo.getFacebookURL()).isEqualTo(UPDATED_FACEBOOK_URL);
        assertThat(testContactInfo.getTwitterURL()).isEqualTo(UPDATED_TWITTER_URL);

        // Validate the ContactInfo in Elasticsearch
        verify(mockContactInfoSearchRepository, times(1)).save(testContactInfo);
    }

    @Test
    @Transactional
    public void updateNonExistingContactInfo() throws Exception {
        int databaseSizeBeforeUpdate = contactInfoRepository.findAll().size();

        // Create the ContactInfo
        ContactInfoDTO contactInfoDTO = contactInfoMapper.toDto(contactInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactInfoMockMvc.perform(put("/api/contact-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactInfo in the database
        List<ContactInfo> contactInfoList = contactInfoRepository.findAll();
        assertThat(contactInfoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ContactInfo in Elasticsearch
        verify(mockContactInfoSearchRepository, times(0)).save(contactInfo);
    }

    @Test
    @Transactional
    public void deleteContactInfo() throws Exception {
        // Initialize the database
        contactInfoRepository.saveAndFlush(contactInfo);

        int databaseSizeBeforeDelete = contactInfoRepository.findAll().size();

        // Delete the contactInfo
        restContactInfoMockMvc.perform(delete("/api/contact-infos/{id}", contactInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactInfo> contactInfoList = contactInfoRepository.findAll();
        assertThat(contactInfoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ContactInfo in Elasticsearch
        verify(mockContactInfoSearchRepository, times(1)).deleteById(contactInfo.getId());
    }

    @Test
    @Transactional
    public void searchContactInfo() throws Exception {
        // Initialize the database
        contactInfoRepository.saveAndFlush(contactInfo);
        when(mockContactInfoSearchRepository.search(queryStringQuery("id:" + contactInfo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(contactInfo), PageRequest.of(0, 1), 1));
        // Search the contactInfo
        restContactInfoMockMvc.perform(get("/api/_search/contact-infos?query=id:" + contactInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].facebookURL").value(hasItem(DEFAULT_FACEBOOK_URL)))
            .andExpect(jsonPath("$.[*].twitterURL").value(hasItem(DEFAULT_TWITTER_URL)));
    }
}
