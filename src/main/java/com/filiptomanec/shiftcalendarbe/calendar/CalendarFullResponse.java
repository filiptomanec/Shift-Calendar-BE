package com.filiptomanec.shiftcalendarbe.calendar;

import com.filiptomanec.shiftcalendarbe.shiftRecord.ShiftRecordResponse;
import lombok.Data;

import java.util.List;

@Data
public class CalendarFullResponse extends CalendarResponse {
	private List<ShiftRecordResponse> shiftRecords;
}
