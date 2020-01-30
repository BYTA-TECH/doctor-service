package com.bytatech.ayoos.doctor.service.mapper;

import com.bytatech.ayoos.doctor.domain.*;
import com.bytatech.ayoos.doctor.service.dto.WorkPlaceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkPlace} and its DTO {@link WorkPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface WorkPlaceMapper extends EntityMapper<WorkPlaceDTO, WorkPlace> {

    @Mapping(source = "doctor.id", target = "doctorId")
    WorkPlaceDTO toDto(WorkPlace workPlace);

    @Mapping(target = "sessionInfos", ignore = true)
    @Mapping(target = "removeSessionInfo", ignore = true)
    @Mapping(source = "doctorId", target = "doctor")
    WorkPlace toEntity(WorkPlaceDTO workPlaceDTO);

    default WorkPlace fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkPlace workPlace = new WorkPlace();
        workPlace.setId(id);
        return workPlace;
    }
}
