package com.bytatech.ayoos.doctor.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.Slot} entity.
 */
public class SlotDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private Double startTime;

    private Double endTime;


    private Long doctorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
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

        SlotDTO slotDTO = (SlotDTO) o;
        if (slotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            ", doctorId=" + getDoctorId() +
            "}";
    }
}
