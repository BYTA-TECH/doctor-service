package com.bytatech.ayoos.doctor.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * A DTO for Doctor session info
 */
public class TimingDetailDTO implements Serializable{

	private Long id;
	private String doctorIdpCode;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalTime fromTime;
	private LocalTime toTime;
	private Long interval;
	private Long weekday;
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public LocalTime getFromTime() {
		return fromTime;
	}
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}
	public Long getWeekday() {
		return weekday;
	}
	public void setWeekday(Long weekday) {
		this.weekday = weekday;
	}
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
	public Long getWorkPlaceId() {
		return workPlaceId;
	}
	public void setWorkPlaceId(Long workPlaceId) {
		this.workPlaceId = workPlaceId;
	}
	@Override
	public String toString() {
		return "TimingDetailDTO [id=" + id + ", doctorIdpCode=" + doctorIdpCode + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", fromTime=" + fromTime + ", toTime=" + toTime + ", interval=" + interval + ", weekday="
				+ weekday + ", workPlaceId=" + workPlaceId + "]";
	}

}
