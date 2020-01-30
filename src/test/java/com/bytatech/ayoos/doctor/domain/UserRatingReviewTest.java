package com.bytatech.ayoos.doctor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bytatech.ayoos.doctor.web.rest.TestUtil;

public class UserRatingReviewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRatingReview.class);
        UserRatingReview userRatingReview1 = new UserRatingReview();
        userRatingReview1.setId(1L);
        UserRatingReview userRatingReview2 = new UserRatingReview();
        userRatingReview2.setId(userRatingReview1.getId());
        assertThat(userRatingReview1).isEqualTo(userRatingReview2);
        userRatingReview2.setId(2L);
        assertThat(userRatingReview1).isNotEqualTo(userRatingReview2);
        userRatingReview1.setId(null);
        assertThat(userRatingReview1).isNotEqualTo(userRatingReview2);
    }
}
