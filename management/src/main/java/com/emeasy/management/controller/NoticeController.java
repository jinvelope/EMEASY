package com.emeasy.management.controller;

import com.emeasy.management.entity.Notice;
import com.emeasy.management.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public String showNotices(Model model) {
        model.addAttribute("notices", noticeRepository.findAll());
        return "notices";
    }

    @GetMapping("/notices/new")
    public String showNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "notice-form";
    }

    @PostMapping("/notices")
    public String saveNotice(@ModelAttribute Notice notice) {
        notice.setDate(LocalDate.now());
        noticeRepository.save(notice);
        return "redirect:/notices";
    }
}
