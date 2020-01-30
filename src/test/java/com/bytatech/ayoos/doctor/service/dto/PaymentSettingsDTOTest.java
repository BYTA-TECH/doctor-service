package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class PaymentSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentSettingsDTO.class);
        PaymentSettingsDTO paymentSettingsDTO1 = new PaymentSettingsDTO();
        paymentSettingsDTO1.setId(1L);
        PaymentSettingsDTO paymentSettingsDTO2 = new PaymentSettingsDTO();
        assertThat(paymentSettingsDTO1).isNotEqualTo(paymentSettingsDTO2);
        paymentSettingsDTO2.setId(paymentSettingsDTO1.getId());
        assertThat(paymentSettingsDTO1).isEqualTo(paymentSettingsDTO2);
        paymentSettingsDTO2.setId(2L);
        assertThat(paymentSettingsDTO1).isNotEqualTo(paymentSettingsDTO2);
        paymentSettingsDTO1.setId(null);
        assertThat(paymentSettingsDTO1).isNotEqualTo(paymentSettingsDTO2);
    }
}
