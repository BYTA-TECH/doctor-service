package com.bytatech.ayoos.doctor.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * A DTO for Doctor session info
 */
public class DoctorSessionInfoDTO implements Serializable{

	private Long id;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalTime fromTime;
	private LocalTime toTime;
	private LocalTime interval;
	private Long weekday;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalTime getInterval() {
		return interval;
	}
	public void setInterval(LocalTime interval) {
		this.interval = interval;
	}

}
