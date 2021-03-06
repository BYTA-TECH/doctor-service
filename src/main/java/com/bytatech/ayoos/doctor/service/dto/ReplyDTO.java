package com.bytatech.ayoos.doctor.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.Reply} entity.
 */
public class ReplyDTO implements Serializable {

    private Long id;

    private String reply;


    private Long userRatingReviewId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getUserRatingReviewId() {
        return userRatingReviewId;
    }

    public void setUserRatingReviewId(Long userRatingReviewId) {
        this.userRatingReviewId = userRatingReviewId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReplyDTO replyDTO = (ReplyDTO) o;
        if (replyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), replyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
            "id=" + getId() +
            ", reply='" + getReply() + "'" +
            ", userRatingReviewId=" + getUserRatingReviewId() +
            "}";
    }
}
