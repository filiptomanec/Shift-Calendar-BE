package com.filiptomanec.shiftcalendarbe.shiftRecord;

import com.filiptomanec.shiftcalendarbe.calendar.Calendar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "shift-records")
public class ShiftRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20)
	private String text;

	private LocalDate date;

	private LocalTime startAt;

	private LocalTime endAt;

	@ManyToOne
	@JoinColumn(name = "calendar")
	private Calendar calendar;
}
