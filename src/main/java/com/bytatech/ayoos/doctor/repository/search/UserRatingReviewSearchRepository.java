package com.bytatech.ayoos.doctor.repository.search;

import com.bytatech.ayoos.doctor.domain.search.UserRatingReview;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserRatingReview} entity.
 */
public interface UserRatingReviewSearchRepository extends ElasticsearchRepository<UserRatingReview, Long> {
}
