package com.bytatech.ayoos.doctor.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class WorkPlaceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkPlaceDTO.class);
        WorkPlaceDTO workPlaceDTO1 = new WorkPlaceDTO();
        workPlaceDTO1.setId(1L);
        WorkPlaceDTO workPlaceDTO2 = new WorkPlaceDTO();
        assertThat(workPlaceDTO1).isNotEqualTo(workPlaceDTO2);
        workPlaceDTO2.setId(workPlaceDTO1.getId());
        assertThat(workPlaceDTO1).isEqualTo(workPlaceDTO2);
        workPlaceDTO2.setId(2L);
        assertThat(workPlaceDTO1).isNotEqualTo(workPlaceDTO2);
        workPlaceDTO1.setId(null);
        assertThat(workPlaceDTO1).isNotEqualTo(workPlaceDTO2);
    }
}
