package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {
	@GetMapping("/remove")
    public ResponseEntity<String> removeFirstAndLast(@RequestParam("input") String input) {
        if (input == null || input.length() < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Input string must have at least 2 characters.");
        }

        if (input.length() == 2) {
            return ResponseEntity.ok("");
        }

        String result = input.substring(1, input.length() - 1);
        return ResponseEntity.ok(result);
    }
}
