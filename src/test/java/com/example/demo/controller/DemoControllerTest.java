package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // add your test cases here

    // Test normal case with > 2 characters
    @Test
    void testRemoveBasicWord() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", "eloquent")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("loquen"));

        mockMvc.perform(get("/remove")
                        .param("input", "country"))
                .andExpect(status().isOk())
                .andExpect(content().string("ountr"));

        mockMvc.perform(get("/remove")
                        .param("input", "person"))
                .andExpect(status().isOk())
                .andExpect(content().string("erso"));
    }

    // Test string with exactly 2 characters
    @Test
    void testRemoveTwoChars() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    // Test string with exactly 3 characters
    @Test
    void testRemoveThreeChars() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", "xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("y"));
    }

    // Test string with numbers and special characters
    @Test
    void testRemoveSpecialChars() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", "123%qwerty+"))
                .andExpect(status().isOk())
                .andExpect(content().string("23%qwerty"));
    }

    // Test string with only 1 character (invalid)
    @Test
    void testRemoveOneChar() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", "a"))
                .andExpect(status().isBadRequest());
    }

    // Test empty input (invalid)
    @Test
    void testRemoveEmptyString() throws Exception {
        mockMvc.perform(get("/remove")
                        .param("input", ""))
                .andExpect(status().isBadRequest());
    }
}

