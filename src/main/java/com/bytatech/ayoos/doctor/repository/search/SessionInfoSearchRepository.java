package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.SessionInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SessionInfo} entity.
 */
public interface SessionInfoSearchRepository extends ElasticsearchRepository<SessionInfo, Long> {
}
