package com.bytatech.ayoos.doctor.web.rest;

import com.bytatech.ayoos.doctor.DoctorApp;
import com.bytatech.ayoos.doctor.config.TestSecurityConfiguration;
import com.bytatech.ayoos.doctor.domain.PaymentSettings;
import com.bytatech.ayoos.doctor.repository.PaymentSettingsRepository;
import com.bytatech.ayoos.doctor.repository.search.PaymentSettingsSearchRepository;
import com.bytatech.ayoos.doctor.service.PaymentSettingsService;
import com.bytatech.ayoos.doctor.service.dto.PaymentSettingsDTO;
import com.bytatech.ayoos.doctor.service.mapper.PaymentSettingsMapper;
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
 * Integration tests for the {@link PaymentSettingsResource} REST controller.
 */
@SpringBootTest(classes = {DoctorApp.class, TestSecurityConfiguration.class})
public class PaymentSettingsResourceIT {

    private static final Boolean DEFAULT_IS_PAYMENT_ENABLED = false;
    private static final Boolean UPDATED_IS_PAYMENT_ENABLED = true;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_INTENT = "AAAAAAAAAA";
    private static final String UPDATED_INTENT = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE_TO_PAYER = "AAAAAAAAAA";
    private static final String UPDATED_NOTE_TO_PAYER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_GATEWAY_PROVIDER = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_GATEWAY_PROVIDER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_GATEWAY_CREDENTIALS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_GATEWAY_CREDENTIALS = "BBBBBBBBBB";

    @Autowired
    private PaymentSettingsRepository paymentSettingsRepository;

    @Autowired
    private PaymentSettingsMapper paymentSettingsMapper;

    @Autowired
    private PaymentSettingsService paymentSettingsService;

    /**
     * This repository is mocked in the com.bytatech.ayoos.doctor.repository.search test package.
     *
     * @see com.bytatech.ayoos.doctor.repository.search.PaymentSettingsSearchRepositoryMockConfiguration
     */
    @Autowired
    private PaymentSettingsSearchRepository mockPaymentSettingsSearchRepository;

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

    private MockMvc restPaymentSettingsMockMvc;

    private PaymentSettings paymentSettings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymentSettingsResource paymentSettingsResource = new PaymentSettingsResource(paymentSettingsService);
        this.restPaymentSettingsMockMvc = MockMvcBuilders.standaloneSetup(paymentSettingsResource)
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
    public static PaymentSettings createEntity(EntityManager em) {
        PaymentSettings paymentSettings = new PaymentSettings()
            .isPaymentEnabled(DEFAULT_IS_PAYMENT_ENABLED)
            .amount(DEFAULT_AMOUNT)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .currency(DEFAULT_CURRENCY)
            .intent(DEFAULT_INTENT)
            .noteToPayer(DEFAULT_NOTE_TO_PAYER)
            .paymentGatewayProvider(DEFAULT_PAYMENT_GATEWAY_PROVIDER)
            .paymentGatewayCredentials(DEFAULT_PAYMENT_GATEWAY_CREDENTIALS);
        return paymentSettings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentSettings createUpdatedEntity(EntityManager em) {
        PaymentSettings paymentSettings = new PaymentSettings()
            .isPaymentEnabled(UPDATED_IS_PAYMENT_ENABLED)
            .amount(UPDATED_AMOUNT)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .currency(UPDATED_CURRENCY)
            .intent(UPDATED_INTENT)
            .noteToPayer(UPDATED_NOTE_TO_PAYER)
            .paymentGatewayProvider(UPDATED_PAYMENT_GATEWAY_PROVIDER)
            .paymentGatewayCredentials(UPDATED_PAYMENT_GATEWAY_CREDENTIALS);
        return paymentSettings;
    }

    @BeforeEach
    public void initTest() {
        paymentSettings = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentSettings() throws Exception {
        int databaseSizeBeforeCreate = paymentSettingsRepository.findAll().size();

        // Create the PaymentSettings
        PaymentSettingsDTO paymentSettingsDTO = paymentSettingsMapper.toDto(paymentSettings);
        restPaymentSettingsMockMvc.perform(post("/api/payment-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentSettingsDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentSettings in the database
        List<PaymentSettings> paymentSettingsList = paymentSettingsRepository.findAll();
        assertThat(paymentSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentSettings testPaymentSettings = paymentSettingsList.get(paymentSettingsList.size() - 1);
        assertThat(testPaymentSettings.isIsPaymentEnabled()).isEqualTo(DEFAULT_IS_PAYMENT_ENABLED);
        assertThat(testPaymentSettings.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentSettings.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testPaymentSettings.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testPaymentSettings.getIntent()).isEqualTo(DEFAULT_INTENT);
        assertThat(testPaymentSettings.getNoteToPayer()).isEqualTo(DEFAULT_NOTE_TO_PAYER);
        assertThat(testPaymentSettings.getPaymentGatewayProvider()).isEqualTo(DEFAULT_PAYMENT_GATEWAY_PROVIDER);
        assertThat(testPaymentSettings.getPaymentGatewayCredentials()).isEqualTo(DEFAULT_PAYMENT_GATEWAY_CREDENTIALS);

        // Validate the PaymentSettings in Elasticsearch
        verify(mockPaymentSettingsSearchRepository, times(1)).save(testPaymentSettings);
    }

    @Test
    @Transactional
    public void createPaymentSettingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentSettingsRepository.findAll().size();

        // Create the PaymentSettings with an existing ID
        paymentSettings.setId(1L);
        PaymentSettingsDTO paymentSettingsDTO = paymentSettingsMapper.toDto(paymentSettings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentSettingsMockMvc.perform(post("/api/payment-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentSettings in the database
        List<PaymentSettings> paymentSettingsList = paymentSettingsRepository.findAll();
        assertThat(paymentSettingsList).hasSize(databaseSizeBeforeCreate);

        // Validate the PaymentSettings in Elasticsearch
        verify(mockPaymentSettingsSearchRepository, times(0)).save(paymentSettings);
    }


    @Test
    @Transactional
    public void getAllPaymentSettings() throws Exception {
        // Initialize the database
        paymentSettingsRepository.saveAndFlush(paymentSettings);

        // Get all the paymentSettingsList
        restPaymentSettingsMockMvc.perform(get("/api/payment-settings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].isPaymentEnabled").value(hasItem(DEFAULT_IS_PAYMENT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].intent").value(hasItem(DEFAULT_INTENT)))
            .andExpect(jsonPath("$.[*].noteToPayer").value(hasItem(DEFAULT_NOTE_TO_PAYER)))
            .andExpect(jsonPath("$.[*].paymentGatewayProvider").value(hasItem(DEFAULT_PAYMENT_GATEWAY_PROVIDER)))
            .andExpect(jsonPath("$.[*].paymentGatewayCredentials").value(hasItem(DEFAULT_PAYMENT_GATEWAY_CREDENTIALS)));
    }
    
    @Test
    @Transactional
    public void getPaymentSettings() throws Exception {
        // Initialize the database
        paymentSettingsRepository.saveAndFlush(paymentSettings);

        // Get the paymentSettings
        restPaymentSettingsMockMvc.perform(get("/api/payment-settings/{id}", paymentSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymentSettings.getId().intValue()))
            .andExpect(jsonPath("$.isPaymentEnabled").value(DEFAULT_IS_PAYMENT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paymentMethod").value(DEFAULT_PAYMENT_METHOD))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.intent").value(DEFAULT_INTENT))
            .andExpect(jsonPath("$.noteToPayer").value(DEFAULT_NOTE_TO_PAYER))
            .andExpect(jsonPath("$.paymentGatewayProvider").value(DEFAULT_PAYMENT_GATEWAY_PROVIDER))
            .andExpect(jsonPath("$.paymentGatewayCredentials").value(DEFAULT_PAYMENT_GATEWAY_CREDENTIALS));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentSettings() throws Exception {
        // Get the paymentSettings
        restPaymentSettingsMockMvc.perform(get("/api/payment-settings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentSettings() throws Exception {
        // Initialize the database
        paymentSettingsRepository.saveAndFlush(paymentSettings);

        int databaseSizeBeforeUpdate = paymentSettingsRepository.findAll().size();

        // Update the paymentSettings
        PaymentSettings updatedPaymentSettings = paymentSettingsRepository.findById(paymentSettings.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentSettings are not directly saved in db
        em.detach(updatedPaymentSettings);
        updatedPaymentSettings
            .isPaymentEnabled(UPDATED_IS_PAYMENT_ENABLED)
            .amount(UPDATED_AMOUNT)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .currency(UPDATED_CURRENCY)
            .intent(UPDATED_INTENT)
            .noteToPayer(UPDATED_NOTE_TO_PAYER)
            .paymentGatewayProvider(UPDATED_PAYMENT_GATEWAY_PROVIDER)
            .paymentGatewayCredentials(UPDATED_PAYMENT_GATEWAY_CREDENTIALS);
        PaymentSettingsDTO paymentSettingsDTO = paymentSettingsMapper.toDto(updatedPaymentSettings);

        restPaymentSettingsMockMvc.perform(put("/api/payment-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentSettingsDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentSettings in the database
        List<PaymentSettings> paymentSettingsList = paymentSettingsRepository.findAll();
        assertThat(paymentSettingsList).hasSize(databaseSizeBeforeUpdate);
        PaymentSettings testPaymentSettings = paymentSettingsList.get(paymentSettingsList.size() - 1);
        assertThat(testPaymentSettings.isIsPaymentEnabled()).isEqualTo(UPDATED_IS_PAYMENT_ENABLED);
        assertThat(testPaymentSettings.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentSettings.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testPaymentSettings.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPaymentSettings.getIntent()).isEqualTo(UPDATED_INTENT);
        assertThat(testPaymentSettings.getNoteToPayer()).isEqualTo(UPDATED_NOTE_TO_PAYER);
        assertThat(testPaymentSettings.getPaymentGatewayProvider()).isEqualTo(UPDATED_PAYMENT_GATEWAY_PROVIDER);
        assertThat(testPaymentSettings.getPaymentGatewayCredentials()).isEqualTo(UPDATED_PAYMENT_GATEWAY_CREDENTIALS);

        // Validate the PaymentSettings in Elasticsearch
        verify(mockPaymentSettingsSearchRepository, times(1)).save(testPaymentSettings);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentSettings() throws Exception {
        int databaseSizeBeforeUpdate = paymentSettingsRepository.findAll().size();

        // Create the PaymentSettings
        PaymentSettingsDTO paymentSettingsDTO = paymentSettingsMapper.toDto(paymentSettings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentSettingsMockMvc.perform(put("/api/payment-settings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymentSettingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentSettings in the database
        List<PaymentSettings> paymentSettingsList = paymentSettingsRepository.findAll();
        assertThat(paymentSettingsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PaymentSettings in Elasticsearch
        verify(mockPaymentSettingsSearchRepository, times(0)).save(paymentSettings);
    }

    @Test
    @Transactional
    public void deletePaymentSettings() throws Exception {
        // Initialize the database
        paymentSettingsRepository.saveAndFlush(paymentSettings);

        int databaseSizeBeforeDelete = paymentSettingsRepository.findAll().size();

        // Delete the paymentSettings
        restPaymentSettingsMockMvc.perform(delete("/api/payment-settings/{id}", paymentSettings.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentSettings> paymentSettingsList = paymentSettingsRepository.findAll();
        assertThat(paymentSettingsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PaymentSettings in Elasticsearch
        verify(mockPaymentSettingsSearchRepository, times(1)).deleteById(paymentSettings.getId());
    }

    @Test
    @Transactional
    public void searchPaymentSettings() throws Exception {
        // Initialize the database
        paymentSettingsRepository.saveAndFlush(paymentSettings);
        when(mockPaymentSettingsSearchRepository.search(queryStringQuery("id:" + paymentSettings.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(paymentSettings), PageRequest.of(0, 1), 1));
        // Search the paymentSettings
        restPaymentSettingsMockMvc.perform(get("/api/_search/payment-settings?query=id:" + paymentSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].isPaymentEnabled").value(hasItem(DEFAULT_IS_PAYMENT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentMethod").value(hasItem(DEFAULT_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].intent").value(hasItem(DEFAULT_INTENT)))
            .andExpect(jsonPath("$.[*].noteToPayer").value(hasItem(DEFAULT_NOTE_TO_PAYER)))
            .andExpect(jsonPath("$.[*].paymentGatewayProvider").value(hasItem(DEFAULT_PAYMENT_GATEWAY_PROVIDER)))
            .andExpect(jsonPath("$.[*].paymentGatewayCredentials").value(hasItem(DEFAULT_PAYMENT_GATEWAY_CREDENTIALS)));
    }
}
