package com.filiptomanec.shiftcalendarbe.shiftRecord;

import com.filiptomanec.shiftcalendarbe.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShiftRecordService {

	private final ShiftRecordRepository shiftRecordRepository;
	private final CalendarService calendarService;

	public ShiftRecord createShiftRecord(ShiftRecordRequest request) {
		ShiftRecord shiftRecord = new ShiftRecord();
		shiftRecord.setText(request.getText());
		shiftRecord.setDate(request.getDate());
		shiftRecord.setStartAt(request.getStartAt());
		shiftRecord.setEndAt(request.getEndAt());
		shiftRecord.setCalendar(calendarService.get(request.getCalendarId()));
		return shiftRecordRepository.save(shiftRecord);
	}

	public List<ShiftRecord> findAllShiftRecordsWithinMonth(LocalDate date) {
		return this.shiftRecordRepository.findAllByMonthAndYear(date.getMonth().getValue(), date.getYear());
	}

	public ShiftRecord get(Integer id) {
		return this.shiftRecordRepository.findById(id).orElse(null);
	}

	public ShiftRecord updateShiftRecord(Integer id, ShiftRecordRequest request) {
		ShiftRecord shiftRecord = get(id);
		shiftRecord.setText(request.getText());
		shiftRecord.setDate(request.getDate());
		shiftRecord.setStartAt(request.getStartAt());
		shiftRecord.setEndAt(request.getEndAt());
		shiftRecord.setCalendar(calendarService.get(request.getCalendarId()));
		return this.shiftRecordRepository.save(shiftRecord);
	}

	public void removeShiftRecord(Integer id) {
		this.shiftRecordRepository.deleteById(id);
	}
}
