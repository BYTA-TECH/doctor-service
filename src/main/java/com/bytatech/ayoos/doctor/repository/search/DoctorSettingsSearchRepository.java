package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.search.DoctorSettings;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DoctorSettings} entity.
 */
public interface DoctorSettingsSearchRepository extends ElasticsearchRepository<DoctorSettings, Long> {
}
