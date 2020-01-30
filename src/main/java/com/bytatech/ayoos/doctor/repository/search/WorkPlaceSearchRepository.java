package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.WorkPlace;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link WorkPlace} entity.
 */
public interface WorkPlaceSearchRepository extends ElasticsearchRepository<WorkPlace, Long> {
}
