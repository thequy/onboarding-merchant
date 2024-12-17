package soft.blue.onboardingmerchant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorld.class)
public class HelloWorldTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHelloWorld() throws Exception {
        mockMvc.perform(get("/helloworld"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Hello World"));
    }
}
