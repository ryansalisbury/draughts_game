package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player-ui")
public class PlayerUIController {

    @GetMapping("/player-creation-form")
    public String showPlayerCreationForm() {
        return "create-player";
    }
    
}