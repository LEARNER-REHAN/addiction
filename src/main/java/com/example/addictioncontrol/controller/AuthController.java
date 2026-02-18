package com.example.addictioncontrol.controller;

import com.example.addictioncontrol.model.User;
import com.example.addictioncontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Optional<User> user = userService.loginUser(email, password);

        if (user.isPresent()) {
            model.addAttribute("name", user.get().getName());
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "login";
        }
    }
}