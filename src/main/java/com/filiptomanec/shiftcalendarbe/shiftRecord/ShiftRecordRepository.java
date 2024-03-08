package com.filiptomanec.shiftcalendarbe.shiftRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRecordRepository extends JpaRepository<ShiftRecord, Integer> {
	@Query("SELECT s FROM ShiftRecord s WHERE MONTH(s.date) = :month AND YEAR(s.date) = :year")
	List<ShiftRecord> findAllByMonthAndYear(int month, int year);
}
