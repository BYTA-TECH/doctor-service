package com.bytatech.ayoos.doctor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Reply.
 */
@Entity
@Table(name = "reply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "reply")
    private String reply;

    @ManyToOne
    @JsonIgnoreProperties("replies")
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
