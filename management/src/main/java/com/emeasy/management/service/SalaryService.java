package com.emeasy.management.service;

import com.emeasy.management.entity.Attendance;
import com.emeasy.management.entity.User;
import com.emeasy.management.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Month;
import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public BigDecimal calculateSalary(User user, Month month) {
        List<Attendance> attendances = attendanceRepository.findByUser(user);
        long totalMinutes = attendances.stream()
                .filter(a -> a.getDate().getMonth() == month)
                .mapToLong(a -> Duration.between(a.getCheckIn(), a.getCheckOut()).toMinutes())
                .sum();
        BigDecimal hourlyRate = new BigDecimal("15"); // Example hourly rate
        return hourlyRate.multiply(new BigDecimal(totalMinutes / 60.0));
    }
}
