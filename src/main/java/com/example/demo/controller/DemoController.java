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

	 /**
     * GET /remove endpoint
     * Accepts a query parameter "input" (the original string).
     * - If string length < 2 → return 400 Bad Request
     * - If string length == 2 → return empty string
     * - If string length >= 3 → return string without first and last character
     */
	@GetMapping("/remove")
    public ResponseEntity<String> removeFirstAndLast(@RequestParam("input") String input) {
        if (input == null || input.length() < 2) {
			// Returning 400 Bad Request for invalid input
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Input string must have at least 2 characters.");
        }

		// For exactly 2 characters, return empty string
        if (input.length() == 2) {
            return ResponseEntity.ok("");
        }

		// For 3 or more characters, remove first and last
        String result = input.substring(1, input.length() - 1);
        return ResponseEntity.ok(result);
    }
}

