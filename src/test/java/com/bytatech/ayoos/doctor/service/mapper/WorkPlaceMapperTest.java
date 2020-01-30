package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class WorkPlaceMapperTest {

    private WorkPlaceMapper workPlaceMapper;

    @BeforeEach
    public void setUp() {
        workPlaceMapper = new WorkPlaceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(workPlaceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(workPlaceMapper.fromId(null)).isNull();
    }
}
