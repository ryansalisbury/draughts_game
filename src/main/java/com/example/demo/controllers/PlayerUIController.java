package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


// @Controller
// @RequestMapping("/player-ui")
// public class PlayerUIController {

//     @GetMapping("/player-creation-form")
//     public String showPlayerCreationForm() {
//         return "create-player";
//     }
    
// }



@RestController
@RequestMapping("/player-ui")
public class PlayerUIController {

    @GetMapping("/player-creation-form")
    public ResponseEntity<String> showPlayerCreationForm() {
        try {
            String htmlContent = new String(Files.readAllBytes(Paths.get(new ClassPathResource("static/create-player.html").getURI())));
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error loading create-player.html");
        }
    }
}