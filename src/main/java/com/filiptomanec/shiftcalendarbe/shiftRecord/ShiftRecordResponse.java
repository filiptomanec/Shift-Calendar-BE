package com.filiptomanec.shiftcalendarbe.shiftRecord;

import com.filiptomanec.shiftcalendarbe.calendar.CalendarResponse;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class ShiftRecordResponse {
	private Integer id;
	private String text;
	private LocalDate date;
	private Time startAt;
	private Time endAt;
	private CalendarResponse calendar;
}
