package com.bytatech.ayoos.doctor.domain.search;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Slot.
 */

public class Slot{

  

   

    private Long id;


    private LocalDate date;


    private Double startTime;


    private Double endTime;


    private Set<Status> statuses = new HashSet<>();

  
    private Doctor doctor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Slot date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getStartTime() {
        return startTime;
    }

    public Slot startTime(Double startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public Slot endTime(Double endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    public Set<Status> getStatuses() {
        return statuses;
    }

    public Slot statuses(Set<Status> statuses) {
        this.statuses = statuses;
        return this;
    }

    public Slot addStatus(Status status) {
        this.statuses.add(status);
        status.setSlot(this);
        return this;
    }

    public Slot removeStatus(Status status) {
        this.statuses.remove(status);
        status.setSlot(null);
        return this;
    }

    public void setStatuses(Set<Status> statuses) {
        this.statuses = statuses;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Slot doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Slot)) {
            return false;
        }
        return id != null && id.equals(((Slot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Slot{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            "}";
    }
}
