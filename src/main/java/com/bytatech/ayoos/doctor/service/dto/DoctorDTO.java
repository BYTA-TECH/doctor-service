package com.bytatech.ayoos.doctor.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Lob;

/**
 * A DTO for the {@link com.bytatech.ayoos.doctor.domain.Doctor} entity.
 */
public class DoctorDTO implements Serializable {

    private Long id;

    private String imageLink;

    private String doctorIdpCode;

    private String specialization;

    private String registerNumber;

    private LocalDate practiceSince;

    private Double totalRating;

    private String firstName;

    private String email;

    private Long phoneNumber;

    private String dmsId;
    
    @Lob
    private byte[] image;
    
    private String imageContentType;


    private Long contactInfoId;

    private Long paymentSettingsId;

    private Long doctorSettingsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDoctorIdpCode() {
        return doctorIdpCode;
    }

    public void setDoctorIdpCode(String doctorIdpCode) {
        this.doctorIdpCode = doctorIdpCode;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public LocalDate getPracticeSince() {
        return practiceSince;
    }

    public void setPracticeSince(LocalDate practiceSince) {
        this.practiceSince = practiceSince;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDmsId() {
        return dmsId;
    }

    public void setDmsId(String dmsId) {
        this.dmsId = dmsId;
    }

    public Long getContactInfoId() {
        return contactInfoId;
    }

    public void setContactInfoId(Long contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    public Long getPaymentSettingsId() {
        return paymentSettingsId;
    }

    public void setPaymentSettingsId(Long paymentSettingsId) {
        this.paymentSettingsId = paymentSettingsId;
    }

    public Long getDoctorSettingsId() {
        return doctorSettingsId;
    }

    public void setDoctorSettingsId(Long doctorSettingsId) {
        this.doctorSettingsId = doctorSettingsId;
    }
    
    public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoctorDTO doctorDTO = (DoctorDTO) o;
        if (doctorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doctorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
            "id=" + getId() +
            ", imageLink='" + getImageLink() + "'" +
            ", doctorIdpCode='" + getDoctorIdpCode() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", registerNumber='" + getRegisterNumber() + "'" +
            ", practiceSince='" + getPracticeSince() + "'" +
            ", totalRating=" + getTotalRating() +
            ", firstName='" + getFirstName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", dmsId='" + getDmsId() + "'" +
            ", contactInfoId=" + getContactInfoId() +
            ", paymentSettingsId=" + getPaymentSettingsId() +
            ", doctorSettingsId=" + getDoctorSettingsId() +
            "}";
    }
}
