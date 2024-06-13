package com.emeasy.management.repository;

import com.emeasy.management.entity.Attendance;
import com.emeasy.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUser(User user);
}
