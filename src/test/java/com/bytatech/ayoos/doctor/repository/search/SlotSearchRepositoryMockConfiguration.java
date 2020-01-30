package com.bytatech.ayoos.doctor.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SlotSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SlotSearchRepositoryMockConfiguration {

    @MockBean
    private SlotSearchRepository mockSlotSearchRepository;

}
