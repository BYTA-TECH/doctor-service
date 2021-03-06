package com.bytatech.ayoos.doctor.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserRatingReview.
 */
@Entity
@Table(name = "user_rating_review")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userratingreview")
public class UserRatingReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review")
    private String review;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "userRatingReview")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reply> replies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("userRatingReviews")
    private Doctor doctor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public UserRatingReview userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getRating() {
        return rating;
    }

    public UserRatingReview rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public UserRatingReview review(String review) {
        this.review = review;
        return this;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getDate() {
        return date;
    }

    public UserRatingReview date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public UserRatingReview replies(Set<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public UserRatingReview addReply(Reply reply) {
        this.replies.add(reply);
        reply.setUserRatingReview(this);
        return this;
    }

    public UserRatingReview removeReply(Reply reply) {
        this.replies.remove(reply);
        reply.setUserRatingReview(null);
        return this;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public UserRatingReview doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRatingReview)) {
            return false;
        }
        return id != null && id.equals(((UserRatingReview) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserRatingReview{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", rating=" + getRating() +
            ", review='" + getReview() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
