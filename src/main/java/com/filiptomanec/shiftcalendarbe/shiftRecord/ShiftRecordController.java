package com.filiptomanec.shiftcalendarbe.shiftRecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shift Records endpoint", description = "Shift Records related operations.")
@RequestMapping("/shift-record")
public class ShiftRecordController {

	private final ShiftRecordService shiftRecordService;
	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "Creates shift record.")
	public ResponseEntity<ShiftRecordResponse> createShiftRecord(@RequestBody ShiftRecordRequest request) {
		ShiftRecord shiftRecord = shiftRecordService.createShiftRecord(request);
		return new ResponseEntity<>(modelMapper.map(shiftRecord, ShiftRecordResponse.class), HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Returns all user shifts within given month.")
	public ResponseEntity<List<ShiftRecordResponse>> getAllUserShiftsWithinMonth(@RequestParam LocalDate date) {
		List<ShiftRecord> shiftRecords = shiftRecordService.findAllShiftRecordsWithinMonth(date);
		return ResponseEntity.ok(
				shiftRecords.stream().map(shiftRecord -> modelMapper.map(shiftRecord, ShiftRecordResponse.class)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Returns shift record with given ID.")
	public ResponseEntity<ShiftRecordResponse> getShiftRecordById(@PathVariable Integer id) {
		ShiftRecord shiftRecord = shiftRecordService.get(id);
		return shiftRecord != null ? ResponseEntity.ok(modelMapper.map(shiftRecord, ShiftRecordResponse.class)) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Updates shift record with given ID.")
	public ResponseEntity<ShiftRecordResponse> updateCalendar(@PathVariable Integer id, @RequestBody ShiftRecordRequest request) {
		ShiftRecord shiftRecord = shiftRecordService.updateShiftRecord(id, request);
		return shiftRecord != null ? ResponseEntity.ok(modelMapper.map(shiftRecord, ShiftRecordResponse.class)) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes shift record with given ID.")
	public ResponseEntity<Void> deleteShiftRecord(@PathVariable Integer id) {
		shiftRecordService.removeShiftRecord(id);
		return ResponseEntity.ok().build();
	}
}
