package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DoctorSettingsMapperTest {

    private DoctorSettingsMapper doctorSettingsMapper;

    @BeforeEach
    public void setUp() {
        doctorSettingsMapper = new DoctorSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(doctorSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(doctorSettingsMapper.fromId(null)).isNull();
    }
}
