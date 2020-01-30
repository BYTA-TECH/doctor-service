package com.bytatech.ayoos.doctor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class PaymentSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentSettings.class);
        PaymentSettings paymentSettings1 = new PaymentSettings();
        paymentSettings1.setId(1L);
        PaymentSettings paymentSettings2 = new PaymentSettings();
        paymentSettings2.setId(paymentSettings1.getId());
        assertThat(paymentSettings1).isEqualTo(paymentSettings2);
        paymentSettings2.setId(2L);
        assertThat(paymentSettings1).isNotEqualTo(paymentSettings2);
        paymentSettings1.setId(null);
        assertThat(paymentSettings1).isNotEqualTo(paymentSettings2);
    }
}
