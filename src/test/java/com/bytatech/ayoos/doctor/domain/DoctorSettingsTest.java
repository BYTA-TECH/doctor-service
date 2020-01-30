package com.bytatech.ayoos.doctor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class DoctorSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoctorSettings.class);
        DoctorSettings doctorSettings1 = new DoctorSettings();
        doctorSettings1.setId(1L);
        DoctorSettings doctorSettings2 = new DoctorSettings();
        doctorSettings2.setId(doctorSettings1.getId());
        assertThat(doctorSettings1).isEqualTo(doctorSettings2);
        doctorSettings2.setId(2L);
        assertThat(doctorSettings1).isNotEqualTo(doctorSettings2);
        doctorSettings1.setId(null);
        assertThat(doctorSettings1).isNotEqualTo(doctorSettings2);
    }
}
