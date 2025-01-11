package soft.blue.onboardingmerchant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloFaoController.class)
class HelloFaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHelloFaoMessage() throws Exception {
        mockMvc.perform(get("/api/hello-fao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Hello Fao"));
    }
}
