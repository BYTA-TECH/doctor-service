package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.DoctorApp;
import com.bytatech.ayoos.doctor.config.TestSecurityConfiguration;
import com.bytatech.ayoos.doctor.domain.Slot;
import com.bytatech.ayoos.doctor.repository.SlotRepository;
import com.bytatech.ayoos.doctor.repository.search.SlotSearchRepository;
import com.bytatech.ayoos.doctor.service.SlotService;
import com.bytatech.ayoos.doctor.service.dto.SlotDTO;
import com.bytatech.ayoos.doctor.service.mapper.SlotMapper;
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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link SlotResource} REST controller.
 */
@SpringBootTest(classes = {DoctorApp.class, TestSecurityConfiguration.class})
public class SlotResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_FROM_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FROM_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TO_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TO_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private SlotMapper slotMapper;

    @Autowired
    private SlotService slotService;

    /**
     * This repository is mocked in the com.bytatech.ayoos.doctor.repository.search test package.
     *
     * @see com.bytatech.ayoos.doctor.repository.search.SlotSearchRepositoryMockConfiguration
     */
    @Autowired
    private SlotSearchRepository mockSlotSearchRepository;

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

    private MockMvc restSlotMockMvc;

    private Slot slot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotResource slotResource = new SlotResource(slotService);
        this.restSlotMockMvc = MockMvcBuilders.standaloneSetup(slotResource)
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
    public static Slot createEntity(EntityManager em) {
        Slot slot = new Slot()
            .date(DEFAULT_DATE)
            .fromTime(DEFAULT_FROM_TIME)
            .toTime(DEFAULT_TO_TIME);
        return slot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Slot createUpdatedEntity(EntityManager em) {
        Slot slot = new Slot()
            .date(UPDATED_DATE)
            .fromTime(UPDATED_FROM_TIME)
            .toTime(UPDATED_TO_TIME);
        return slot;
    }

    @BeforeEach
    public void initTest() {
        slot = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlot() throws Exception {
        int databaseSizeBeforeCreate = slotRepository.findAll().size();

        // Create the Slot
        SlotDTO slotDTO = slotMapper.toDto(slot);
        restSlotMockMvc.perform(post("/api/slots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(slotDTO)))
            .andExpect(status().isCreated());

        // Validate the Slot in the database
        List<Slot> slotList = slotRepository.findAll();
        assertThat(slotList).hasSize(databaseSizeBeforeCreate + 1);
        Slot testSlot = slotList.get(slotList.size() - 1);
        assertThat(testSlot.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSlot.getFromTime()).isEqualTo(DEFAULT_FROM_TIME);
        assertThat(testSlot.getToTime()).isEqualTo(DEFAULT_TO_TIME);

        // Validate the Slot in Elasticsearch
        verify(mockSlotSearchRepository, times(1)).save(testSlot);
    }

    @Test
    @Transactional
    public void createSlotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotRepository.findAll().size();

        // Create the Slot with an existing ID
        slot.setId(1L);
        SlotDTO slotDTO = slotMapper.toDto(slot);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotMockMvc.perform(post("/api/slots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(slotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Slot in the database
        List<Slot> slotList = slotRepository.findAll();
        assertThat(slotList).hasSize(databaseSizeBeforeCreate);

        // Validate the Slot in Elasticsearch
        verify(mockSlotSearchRepository, times(0)).save(slot);
    }


    @Test
    @Transactional
    public void getAllSlots() throws Exception {
        // Initialize the database
        slotRepository.saveAndFlush(slot);

        // Get all the slotList
        restSlotMockMvc.perform(get("/api/slots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slot.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].fromTime").value(hasItem(DEFAULT_FROM_TIME.toString())))
            .andExpect(jsonPath("$.[*].toTime").value(hasItem(DEFAULT_TO_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getSlot() throws Exception {
        // Initialize the database
        slotRepository.saveAndFlush(slot);

        // Get the slot
        restSlotMockMvc.perform(get("/api/slots/{id}", slot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(slot.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.fromTime").value(DEFAULT_FROM_TIME.toString()))
            .andExpect(jsonPath("$.toTime").value(DEFAULT_TO_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSlot() throws Exception {
        // Get the slot
        restSlotMockMvc.perform(get("/api/slots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlot() throws Exception {
        // Initialize the database
        slotRepository.saveAndFlush(slot);

        int databaseSizeBeforeUpdate = slotRepository.findAll().size();

        // Update the slot
        Slot updatedSlot = slotRepository.findById(slot.getId()).get();
        // Disconnect from session so that the updates on updatedSlot are not directly saved in db
        em.detach(updatedSlot);
        updatedSlot
            .date(UPDATED_DATE)
            .fromTime(UPDATED_FROM_TIME)
            .toTime(UPDATED_TO_TIME);
        SlotDTO slotDTO = slotMapper.toDto(updatedSlot);

        restSlotMockMvc.perform(put("/api/slots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(slotDTO)))
            .andExpect(status().isOk());

        // Validate the Slot in the database
        List<Slot> slotList = slotRepository.findAll();
        assertThat(slotList).hasSize(databaseSizeBeforeUpdate);
        Slot testSlot = slotList.get(slotList.size() - 1);
        assertThat(testSlot.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSlot.getFromTime()).isEqualTo(UPDATED_FROM_TIME);
        assertThat(testSlot.getToTime()).isEqualTo(UPDATED_TO_TIME);

        // Validate the Slot in Elasticsearch
        verify(mockSlotSearchRepository, times(1)).save(testSlot);
    }

    @Test
    @Transactional
    public void updateNonExistingSlot() throws Exception {
        int databaseSizeBeforeUpdate = slotRepository.findAll().size();

        // Create the Slot
        SlotDTO slotDTO = slotMapper.toDto(slot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotMockMvc.perform(put("/api/slots")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(slotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Slot in the database
        List<Slot> slotList = slotRepository.findAll();
        assertThat(slotList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Slot in Elasticsearch
        verify(mockSlotSearchRepository, times(0)).save(slot);
    }

    @Test
    @Transactional
    public void deleteSlot() throws Exception {
        // Initialize the database
        slotRepository.saveAndFlush(slot);

        int databaseSizeBeforeDelete = slotRepository.findAll().size();

        // Delete the slot
        restSlotMockMvc.perform(delete("/api/slots/{id}", slot.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Slot> slotList = slotRepository.findAll();
        assertThat(slotList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Slot in Elasticsearch
        verify(mockSlotSearchRepository, times(1)).deleteById(slot.getId());
    }

    @Test
    @Transactional
    public void searchSlot() throws Exception {
        // Initialize the database
        slotRepository.saveAndFlush(slot);
        when(mockSlotSearchRepository.search(queryStringQuery("id:" + slot.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(slot), PageRequest.of(0, 1), 1));
        // Search the slot
        restSlotMockMvc.perform(get("/api/_search/slots?query=id:" + slot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slot.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].fromTime").value(hasItem(DEFAULT_FROM_TIME.toString())))
            .andExpect(jsonPath("$.[*].toTime").value(hasItem(DEFAULT_TO_TIME.toString())));
    }
}
