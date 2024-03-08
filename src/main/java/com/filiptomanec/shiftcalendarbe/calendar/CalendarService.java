package com.filiptomanec.shiftcalendarbe.calendar;

import com.filiptomanec.shiftcalendarbe.auth.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final SecurityContext secContext;

	public Calendar createCalendar(String name) {
		Calendar calendar = new Calendar();
		calendar.setName(name);
		calendar.setUser(secContext.getUser());
		return calendarRepository.save(calendar);
	}

	public List<Calendar> findAllCalendars() {
		return this.calendarRepository.findAllByUser(secContext.getUser());
	}

	public Calendar get(Integer id) {
		return this.calendarRepository.findById(id).orElse(null);
	}

	public Calendar updateCalendar(Integer id, String name) {
		Calendar calendar = get(id);
		calendar.setName(name);
		return this.calendarRepository.save(calendar);
	}

	public void removeCalendar(Integer id) {
		this.calendarRepository.deleteById(id);
	}
}
