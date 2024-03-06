package com.filiptomanec.shiftcalendarbe.calendar;

import com.filiptomanec.shiftcalendarbe.shiftRecord.ShiftRecord;
import com.filiptomanec.shiftcalendarbe.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "calendars")
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 64)
	private String name;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@OneToMany(mappedBy = "calendar", orphanRemoval = true)
	private List<ShiftRecord> shiftRecords;
}
