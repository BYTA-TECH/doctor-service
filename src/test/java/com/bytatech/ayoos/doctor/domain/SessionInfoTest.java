package com.bytatech.ayoos.doctor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class SessionInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionInfo.class);
        SessionInfo sessionInfo1 = new SessionInfo();
        sessionInfo1.setId(1L);
        SessionInfo sessionInfo2 = new SessionInfo();
        sessionInfo2.setId(sessionInfo1.getId());
        assertThat(sessionInfo1).isEqualTo(sessionInfo2);
        sessionInfo2.setId(2L);
        assertThat(sessionInfo1).isNotEqualTo(sessionInfo2);
        sessionInfo1.setId(null);
        assertThat(sessionInfo1).isNotEqualTo(sessionInfo2);
    }
}
