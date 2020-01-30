package com.bytatech.ayoos.doctor.service.mapper;

import com.bytatech.ayoos.doctor.domain.*;
import com.bytatech.ayoos.doctor.service.dto.DoctorSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoctorSettings} and its DTO {@link DoctorSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoctorSettingsMapper extends EntityMapper<DoctorSettingsDTO, DoctorSettings> {



    default DoctorSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoctorSettings doctorSettings = new DoctorSettings();
        doctorSettings.setId(id);
        return doctorSettings;
    }
}
