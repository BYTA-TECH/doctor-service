package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QualificationMapperTest {

    private QualificationMapper qualificationMapper;

    @BeforeEach
    public void setUp() {
        qualificationMapper = new QualificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qualificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qualificationMapper.fromId(null)).isNull();
    }
}
