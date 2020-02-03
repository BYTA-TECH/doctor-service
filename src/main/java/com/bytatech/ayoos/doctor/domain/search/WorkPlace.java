package com.bytatech.ayoos.doctor.domain.search;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A WorkPlace.
 */


public class WorkPlace  {


    private Long id;

    private String name;

   
    private String locationName;

   
    private String location;

   
    private Set<SessionInfo> sessionInfos = new HashSet<>();

    private Doctor doctor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WorkPlace name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationName() {
        return locationName;
    }

    public WorkPlace locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocation() {
        return location;
    }

    public WorkPlace location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<SessionInfo> getSessionInfos() {
        return sessionInfos;
    }

    public WorkPlace sessionInfos(Set<SessionInfo> sessionInfos) {
        this.sessionInfos = sessionInfos;
        return this;
    }

    public WorkPlace addSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfos.add(sessionInfo);
        sessionInfo.setWorkPlace(this);
        return this;
    }

    public WorkPlace removeSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfos.remove(sessionInfo);
        sessionInfo.setWorkPlace(null);
        return this;
    }

    public void setSessionInfos(Set<SessionInfo> sessionInfos) {
        this.sessionInfos = sessionInfos;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public WorkPlace doctor(Doctor doctor) {
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
        if (!(o instanceof WorkPlace)) {
            return false;
        }
        return id != null && id.equals(((WorkPlace) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WorkPlace{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
