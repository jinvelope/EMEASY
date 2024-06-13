package com.emeasy.management.controller;

import com.emeasy.management.entity.Attendance;
import com.emeasy.management.entity.User;
import com.emeasy.management.repository.AttendanceRepository;
import com.emeasy.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/attendance")
    public String showAttendancePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("attendances", attendanceRepository.findByUser(user));
        return "attendance";
    }

    @PostMapping("/attendance/check-in")
    public String checkIn() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalTime.now());

        attendanceRepository.save(attendance);
        return "redirect:/attendance";
    }

    @PostMapping("/attendance/check-out")
    public String checkOut() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        Attendance attendance = attendanceRepository.findByUser(user).stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No check-in found for today"));

        attendance.setCheckOut(LocalTime.now());

        attendanceRepository.save(attendance);
        return "redirect:/attendance";
    }
}
