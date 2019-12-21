package com.codetest.gmail.registration.web;

import com.codetest.gmail.registration.dto.Registration;
import com.codetest.gmail.registration.service.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationServiceImpl registrationService;

    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "registration";
    }

    @PostMapping("/registration")
    public String greetingSubmit(@ModelAttribute Registration registration) {
        if (registrationService.validation(registration).equals("success"))
            return "result";
        else
            return "error";
    }

}