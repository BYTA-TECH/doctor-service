package com.bytatech.ayoos.doctor.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.UserRatingReview} entity.
 */
public class UserRatingReviewDTO implements Serializable {

    private Long id;

    private String userName;

    private Double rating;

    private String review;

    private LocalDate date;


    private Long doctorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRatingReviewDTO userRatingReviewDTO = (UserRatingReviewDTO) o;
        if (userRatingReviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userRatingReviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserRatingReviewDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", rating=" + getRating() +
            ", review='" + getReview() + "'" +
            ", date='" + getDate() + "'" +
            ", doctorId=" + getDoctorId() +
            "}";
    }
}
