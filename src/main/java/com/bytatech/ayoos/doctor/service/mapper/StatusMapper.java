package com.bytatech.ayoos.doctor.service.mapper;

import com.bytatech.ayoos.doctor.domain.*;
import com.bytatech.ayoos.doctor.service.dto.StatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Status} and its DTO {@link StatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotMapper.class})
public interface StatusMapper extends EntityMapper<StatusDTO, Status> {

    @Mapping(source = "slot.id", target = "slotId")
    StatusDTO toDto(Status status);

    @Mapping(source = "slotId", target = "slot")
    Status toEntity(StatusDTO statusDTO);

    default Status fromId(Long id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }
}
