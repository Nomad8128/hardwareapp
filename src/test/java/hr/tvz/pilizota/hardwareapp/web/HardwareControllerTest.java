package hr.tvz.pilizota.hardwareapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.pilizota.hardwareapp.hardware.Hardware;
import hr.tvz.pilizota.hardwareapp.hardware.HardwareCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcxNjA1MywiaWF0IjoxNjU0MTExMjUzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.t9fXduLi9sivGeOGpIeGWejTwyMYIYshYIogDxiEUzHPo8NoBffYNyhrGBszX843KxZPuR8LfQS9j1WWWGBjJQ";

    final String TEST_NAME = "placeholderName";
    final String TEST_CODE = "9876543210";
    final BigDecimal TEST_PRICE = new BigDecimal("1000");
    final Hardware.Type TEST_TYPE = Hardware.Type.valueOf("CPU");
    final Integer TEST_AMOUNT = 1000;
    HardwareCommand hardwareCommand = new HardwareCommand(TEST_NAME, TEST_CODE, TEST_PRICE, TEST_TYPE, TEST_AMOUNT);

    @Test
    void getAllHardware() throws Exception{
        this.mockMvc.perform(
                        get("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/3924259115")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    @Transactional
    void save() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    @Transactional
    void update() throws Exception{
        /*
        this.mockMvc.perform(
                        put("/hardware/9876543210")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

         */
    }

    @Test
    @Transactional
    void deleteDelete() throws Exception {
        this.mockMvc.perform(
                        delete("/hardware/9876543210")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
