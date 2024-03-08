package com.filiptomanec.shiftcalendarbe.calendar;

import com.filiptomanec.shiftcalendarbe.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
	List<Calendar> findAllByUser(User user);
}
