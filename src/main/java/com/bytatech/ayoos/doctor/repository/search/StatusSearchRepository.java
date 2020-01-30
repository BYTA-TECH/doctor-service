package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.Status;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Status} entity.
 */
public interface StatusSearchRepository extends ElasticsearchRepository<Status, Long> {
}
