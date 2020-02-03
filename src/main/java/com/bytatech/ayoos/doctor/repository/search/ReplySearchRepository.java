package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.Reply;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Reply} entity.
 */
public interface ReplySearchRepository extends ElasticsearchRepository<Reply, Long> {
}
