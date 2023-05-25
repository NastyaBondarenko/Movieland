package com.bondarenko.movieland.web.controller;

import com.bondarenko.movieland.AbstractWebITest;
import com.bondarenko.movieland.service.dto.request.LoginRequestDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
public class LoginControllerITest extends AbstractWebITest {

    @Test
    @DataSet(value = "datasets/user/dataset_users.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Login with Correct Email then Password and Ok Status Returned")
    void whenLogin_withCorrectEmailAndPassword_thenOkStatusReturned() throws Exception {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("jessie.patterson68@example.com")
                .password("tommy")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(content()
                        .json("""                        
                                {
                                "nickname": "Джесси Паттерсон"
                                }
                                 """))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/user/dataset_users.yml", cleanAfter = true,
            cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Login with Incorrect Email And Password then Not Found Returned")
    void whenLogin_withIncorrectEmailAndPassword_thenNotFoundReturned() throws Exception {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("UnknownEmail")
                .password("tommy")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isNotFound());
    }
}