package com.bytatech.ayoos.doctor.domain.search;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Reply.
 */


public class Reply  {

 
    private Long id;

  
    private String reply;

  
    private UserRatingReview userRatingReview;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public Reply reply(String reply) {
        this.reply = reply;
        return this;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public UserRatingReview getUserRatingReview() {
        return userRatingReview;
    }

    public Reply userRatingReview(UserRatingReview userRatingReview) {
        this.userRatingReview = userRatingReview;
        return this;
    }

    public void setUserRatingReview(UserRatingReview userRatingReview) {
        this.userRatingReview = userRatingReview;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reply)) {
            return false;
        }
        return id != null && id.equals(((Reply) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reply{" +
            "id=" + getId() +
            ", reply='" + getReply() + "'" +
            "}";
    }
}
