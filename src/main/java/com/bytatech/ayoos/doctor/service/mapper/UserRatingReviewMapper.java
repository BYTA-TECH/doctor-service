package com.bytatech.ayoos.doctor.service.mapper;

import com.bytatech.ayoos.doctor.domain.*;
import com.bytatech.ayoos.doctor.service.dto.UserRatingReviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserRatingReview} and its DTO {@link UserRatingReviewDTO}.
 */
@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface UserRatingReviewMapper extends EntityMapper<UserRatingReviewDTO, UserRatingReview> {

    @Mapping(source = "doctor.id", target = "doctorId")
    UserRatingReviewDTO toDto(UserRatingReview userRatingReview);

    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "removeReply", ignore = true)
    @Mapping(source = "doctorId", target = "doctor")
    UserRatingReview toEntity(UserRatingReviewDTO userRatingReviewDTO);

    default UserRatingReview fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserRatingReview userRatingReview = new UserRatingReview();
        userRatingReview.setId(id);
        return userRatingReview;
    }
}
