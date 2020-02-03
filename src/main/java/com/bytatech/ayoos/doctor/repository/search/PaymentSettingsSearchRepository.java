package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.search.PaymentSettings;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentSettings} entity.
 */
public interface PaymentSettingsSearchRepository extends ElasticsearchRepository<PaymentSettings, Long> {
}
