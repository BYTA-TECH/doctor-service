package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PaymentSettingsMapperTest {

    private PaymentSettingsMapper paymentSettingsMapper;

    @BeforeEach
    public void setUp() {
        paymentSettingsMapper = new PaymentSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(paymentSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentSettingsMapper.fromId(null)).isNull();
    }
}
