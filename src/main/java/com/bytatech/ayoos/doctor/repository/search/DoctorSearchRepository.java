package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.search.Doctor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Doctor} entity.
 */
public interface DoctorSearchRepository extends ElasticsearchRepository<Doctor, Long> {
}
