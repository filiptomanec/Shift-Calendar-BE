package com.filiptomanec.shiftcalendarbe.calendar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Calendar endpoint", description = "Calendars related operations.")
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "Creates user calendars.")
	public ResponseEntity<CalendarResponse> createUserCalendars(@RequestParam String name) {
		Calendar calendar = calendarService.createCalendar(name);
		return new ResponseEntity<>(modelMapper.map(calendar, CalendarResponse.class), HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Returns all user calendars.")
	public ResponseEntity<List<CalendarResponse>> getAllUserCalendars() {
		List<Calendar> calendars = calendarService.findAllCalendars();
		return ResponseEntity.ok(calendars.stream().map(calendar -> modelMapper.map(calendar, CalendarResponse.class)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Returns calendar with given ID.")
	public ResponseEntity<CalendarResponse> getCalendarById(@PathVariable Integer id) {
		Calendar calendar = calendarService.get(id);
		return calendar != null ? ResponseEntity.ok(modelMapper.map(calendar, CalendarResponse.class)) : ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Updates calendar with given ID.")
	public ResponseEntity<CalendarResponse> updateCalendar(@PathVariable Integer id, @RequestParam String name) {
		Calendar calendar = calendarService.updateCalendar(id, name);
		return calendar != null ? ResponseEntity.ok(modelMapper.map(calendar, CalendarResponse.class)) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes calendar with given ID.")
	public ResponseEntity<Void> deleteCalendar(@PathVariable Integer id) {
		calendarService.removeCalendar(id);
		return ResponseEntity.ok().build();
	}
}
