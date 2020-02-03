package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.search.ContactInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ContactInfo} entity.
 */
public interface ContactInfoSearchRepository extends ElasticsearchRepository<ContactInfo, Long> {
}
