package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.Slot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Slot} entity.
 */
public interface SlotSearchRepository extends ElasticsearchRepository<Slot, Long> {
}
