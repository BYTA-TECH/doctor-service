package com.bytatech.ayoos.doctor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class WorkPlaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkPlace.class);
        WorkPlace workPlace1 = new WorkPlace();
        workPlace1.setId(1L);
        WorkPlace workPlace2 = new WorkPlace();
        workPlace2.setId(workPlace1.getId());
        assertThat(workPlace1).isEqualTo(workPlace2);
        workPlace2.setId(2L);
        assertThat(workPlace1).isNotEqualTo(workPlace2);
        workPlace1.setId(null);
        assertThat(workPlace1).isNotEqualTo(workPlace2);
    }
}
