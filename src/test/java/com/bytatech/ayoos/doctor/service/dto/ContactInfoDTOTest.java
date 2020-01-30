package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class ContactInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactInfoDTO.class);
        ContactInfoDTO contactInfoDTO1 = new ContactInfoDTO();
        contactInfoDTO1.setId(1L);
        ContactInfoDTO contactInfoDTO2 = new ContactInfoDTO();
        assertThat(contactInfoDTO1).isNotEqualTo(contactInfoDTO2);
        contactInfoDTO2.setId(contactInfoDTO1.getId());
        assertThat(contactInfoDTO1).isEqualTo(contactInfoDTO2);
        contactInfoDTO2.setId(2L);
        assertThat(contactInfoDTO1).isNotEqualTo(contactInfoDTO2);
        contactInfoDTO1.setId(null);
        assertThat(contactInfoDTO1).isNotEqualTo(contactInfoDTO2);
    }
}
