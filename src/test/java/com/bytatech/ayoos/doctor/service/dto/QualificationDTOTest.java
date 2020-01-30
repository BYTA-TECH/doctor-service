package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class QualificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualificationDTO.class);
        QualificationDTO qualificationDTO1 = new QualificationDTO();
        qualificationDTO1.setId(1L);
        QualificationDTO qualificationDTO2 = new QualificationDTO();
        assertThat(qualificationDTO1).isNotEqualTo(qualificationDTO2);
        qualificationDTO2.setId(qualificationDTO1.getId());
        assertThat(qualificationDTO1).isEqualTo(qualificationDTO2);
        qualificationDTO2.setId(2L);
        assertThat(qualificationDTO1).isNotEqualTo(qualificationDTO2);
        qualificationDTO1.setId(null);
        assertThat(qualificationDTO1).isNotEqualTo(qualificationDTO2);
    }
}
