package com.bytatech.ayoos.doctor.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ContactInfoMapperTest {

    private ContactInfoMapper contactInfoMapper;

    @BeforeEach
    public void setUp() {
        contactInfoMapper = new ContactInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(contactInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(contactInfoMapper.fromId(null)).isNull();
    }
}
