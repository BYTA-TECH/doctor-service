package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionInfoMapperTest {

    private SessionInfoMapper sessionInfoMapper;

    @BeforeEach
    public void setUp() {
        sessionInfoMapper = new SessionInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sessionInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sessionInfoMapper.fromId(null)).isNull();
    }
}
