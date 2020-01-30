package com.bytatech.ayoos.doctor.repository;

import com.bytatech.ayoos.doctor.domain.UserRatingReview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserRatingReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRatingReviewRepository extends JpaRepository<UserRatingReview, Long> {

}
