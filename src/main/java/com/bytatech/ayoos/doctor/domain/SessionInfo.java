package com.bytatech.ayoos.doctor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import com.bytatech.ayoos.doctor.domain.enumeration.SessionStatus;

/**
 * A SessionInfo.
 */
@Entity
@Table(name = "session_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sessioninfo")
public class SessionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_idp_code")
    private String doctorIdpCode;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "from_time")
    private Instant fromTime;

    @Column(name = "to_time")
    private Instant toTime;

    @Column(name = "jhi_interval")
    private Long interval;

    @Column(name = "week_day")
    private Long weekDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_status")
    private SessionStatus sessionStatus;

    @OneToOne
    @JoinColumn(unique = true)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("sessionInfos")
    private WorkPlace workPlace;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorIdpCode() {
        return doctorIdpCode;
    }

    public SessionInfo doctorIdpCode(String doctorIdpCode) {
        this.doctorIdpCode = doctorIdpCode;
        return this;
    }

    public void setDoctorIdpCode(String doctorIdpCode) {
        this.doctorIdpCode = doctorIdpCode;
    }

    public String getSessionName() {
        return sessionName;
    }

    public SessionInfo sessionName(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public SessionInfo date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Instant getFromTime() {
        return fromTime;
    }

    public SessionInfo fromTime(Instant fromTime) {
        this.fromTime = fromTime;
        return this;
    }

    public void setFromTime(Instant fromTime) {
        this.fromTime = fromTime;
    }

    public Instant getToTime() {
        return toTime;
    }

    public SessionInfo toTime(Instant toTime) {
        this.toTime = toTime;
        return this;
    }

    public void setToTime(Instant toTime) {
        this.toTime = toTime;
    }

    public Long getInterval() {
        return interval;
    }

    public SessionInfo interval(Long interval) {
        this.interval = interval;
        return this;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getWeekDay() {
        return weekDay;
    }

    public SessionInfo weekDay(Long weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    public void setWeekDay(Long weekDay) {
        this.weekDay = weekDay;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionInfo sessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Status getStatus() {
        return status;
    }

    public SessionInfo status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    public SessionInfo workPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionInfo)) {
            return false;
        }
        return id != null && id.equals(((SessionInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
            "id=" + getId() +
            ", doctorIdpCode='" + getDoctorIdpCode() + "'" +
            ", sessionName='" + getSessionName() + "'" +
            ", date='" + getDate() + "'" +
            ", fromTime='" + getFromTime() + "'" +
            ", toTime='" + getToTime() + "'" +
            ", interval=" + getInterval() +
            ", weekDay=" + getWeekDay() +
            ", sessionStatus='" + getSessionStatus() + "'" +
            "}";
    }
}
