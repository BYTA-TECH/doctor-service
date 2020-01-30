package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class DoctorSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorSettingsDTO.class);
        DoctorSettingsDTO doctorSettingsDTO1 = new DoctorSettingsDTO();
        doctorSettingsDTO1.setId(1L);
        DoctorSettingsDTO doctorSettingsDTO2 = new DoctorSettingsDTO();
        assertThat(doctorSettingsDTO1).isNotEqualTo(doctorSettingsDTO2);
        doctorSettingsDTO2.setId(doctorSettingsDTO1.getId());
        assertThat(doctorSettingsDTO1).isEqualTo(doctorSettingsDTO2);
        doctorSettingsDTO2.setId(2L);
        assertThat(doctorSettingsDTO1).isNotEqualTo(doctorSettingsDTO2);
        doctorSettingsDTO1.setId(null);
        assertThat(doctorSettingsDTO1).isNotEqualTo(doctorSettingsDTO2);
    }
}
