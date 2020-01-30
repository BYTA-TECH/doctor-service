package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.Qualification;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Qualification} entity.
 */
public interface QualificationSearchRepository extends ElasticsearchRepository<Qualification, Long> {
}
