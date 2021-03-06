package com.bytatech.ayoos.doctor.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.Status} entity.
 */
@ApiModel(description = "@deprecated This entity is replaced by\nSessionStatus enum")
public class StatusDTO implements Serializable {

    private Long id;

    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatusDTO statusDTO = (StatusDTO) o;
        if (statusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
