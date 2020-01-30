package com.bytatech.ayoos.doctor.service.mapper;

import com.bytatech.ayoos.doctor.domain.*;
import com.bytatech.ayoos.doctor.service.dto.DoctorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Doctor} and its DTO {@link DoctorDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContactInfoMapper.class, PaymentSettingsMapper.class, DoctorSettingsMapper.class})
public interface DoctorMapper extends EntityMapper<DoctorDTO, Doctor> {

    @Mapping(source = "contactInfo.id", target = "contactInfoId")
    @Mapping(source = "paymentSettings.id", target = "paymentSettingsId")
    @Mapping(source = "doctorSettings.id", target = "doctorSettingsId")
    DoctorDTO toDto(Doctor doctor);

    @Mapping(source = "contactInfoId", target = "contactInfo")
    @Mapping(source = "paymentSettingsId", target = "paymentSettings")
    @Mapping(source = "doctorSettingsId", target = "doctorSettings")
    @Mapping(target = "workPlaces", ignore = true)
    @Mapping(target = "removeWorkPlace", ignore = true)
    @Mapping(target = "qualifications", ignore = true)
    @Mapping(target = "removeQualification", ignore = true)
    @Mapping(target = "userRatingReviews", ignore = true)
    @Mapping(target = "removeUserRatingReview", ignore = true)
    @Mapping(target = "slots", ignore = true)
    @Mapping(target = "removeSlot", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);

    default Doctor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setId(id);
        return doctor;
    }
}
