package com.filiptomanec.shiftcalendarbe.shiftRecord;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShiftRecordRequest {
	private String text;
	private LocalDate date;
	private LocalTime startAt;
	private LocalTime endAt;
	private Integer calendarId;
}
