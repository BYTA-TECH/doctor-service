package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class SessionInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionInfoDTO.class);
        SessionInfoDTO sessionInfoDTO1 = new SessionInfoDTO();
        sessionInfoDTO1.setId(1L);
        SessionInfoDTO sessionInfoDTO2 = new SessionInfoDTO();
        assertThat(sessionInfoDTO1).isNotEqualTo(sessionInfoDTO2);
        sessionInfoDTO2.setId(sessionInfoDTO1.getId());
        assertThat(sessionInfoDTO1).isEqualTo(sessionInfoDTO2);
        sessionInfoDTO2.setId(2L);
        assertThat(sessionInfoDTO1).isNotEqualTo(sessionInfoDTO2);
        sessionInfoDTO1.setId(null);
        assertThat(sessionInfoDTO1).isNotEqualTo(sessionInfoDTO2);
    }
}
