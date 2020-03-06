package com.bytatech.ayoos.doctor.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.bytatech.ayoos.doctor.domain.enumeration.SessionStatus;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.SessionInfo} entity.
 */
public class SessionInfoDTO implements Serializable {

    private Long id;

    private String doctorIdpCode;

    private String sessionName;

    private LocalDate date;

    private Instant fromTime;

    private Instant toTime;

    private Long interval;

    private Long weekDay;

    private SessionStatus sessionStatus;


    private Long statusId;

    private Long workPlaceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorIdpCode() {
        return doctorIdpCode;
    }

    public void setDoctorIdpCode(String doctorIdpCode) {
        this.doctorIdpCode = doctorIdpCode;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Instant getFromTime() {
        return fromTime;
    }

    public void setFromTime(Instant fromTime) {
        this.fromTime = fromTime;
    }

    public Instant getToTime() {
        return toTime;
    }

    public void setToTime(Instant toTime) {
        this.toTime = toTime;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Long weekDay) {
        this.weekDay = weekDay;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getWorkPlaceId() {
        return workPlaceId;
    }

    public void setWorkPlaceId(Long workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SessionInfoDTO sessionInfoDTO = (SessionInfoDTO) o;
        if (sessionInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sessionInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SessionInfoDTO{" +
            "id=" + getId() +
            ", doctorIdpCode='" + getDoctorIdpCode() + "'" +
            ", sessionName='" + getSessionName() + "'" +
            ", date='" + getDate() + "'" +
            ", fromTime='" + getFromTime() + "'" +
            ", toTime='" + getToTime() + "'" +
            ", interval=" + getInterval() +
            ", weekDay=" + getWeekDay() +
            ", sessionStatus='" + getSessionStatus() + "'" +
            ", statusId=" + getStatusId() +
            ", workPlaceId=" + getWorkPlaceId() +
            "}";
    }
}
