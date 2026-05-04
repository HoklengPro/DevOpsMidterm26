package com.midterm.devops.task3.web;

import com.midterm.devops.task3.domain.DemoMessage;
import com.midterm.devops.task3.repo.DemoMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DemoMessageRepository messages;

    @GetMapping("/")
    public String index(Model model) {
        List<DemoMessage> all = messages.findAll();
        model.addAttribute("messages", all);
        model.addAttribute("ping", "Task 3 Spring Boot is running with Thymeleaf + JPA.");
        return "index";
    }
}
