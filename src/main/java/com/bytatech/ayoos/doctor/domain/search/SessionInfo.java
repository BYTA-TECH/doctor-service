package com.bytatech.ayoos.doctor.domain.search;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A SessionInfo.
 */

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sessioninfo")
public class SessionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

   
    private String sessionName;

    
    private LocalDate date;

   
    private Integer weekDay;


    private Instant fromTime;

  
    private Instant toTime;

   
    private Double interval;

 
    private WorkPlace workPlace;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getWeekDay() {
        return weekDay;
    }

    public SessionInfo weekDay(Integer weekDay) {
        this.weekDay = weekDay;
        return this;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
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

    public Double getInterval() {
        return interval;
    }

    public SessionInfo interval(Double interval) {
        this.interval = interval;
        return this;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
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
            ", sessionName='" + getSessionName() + "'" +
            ", date='" + getDate() + "'" +
            ", weekDay=" + getWeekDay() +
            ", fromTime='" + getFromTime() + "'" +
            ", toTime='" + getToTime() + "'" +
            ", interval=" + getInterval() +
            "}";
    }
}
