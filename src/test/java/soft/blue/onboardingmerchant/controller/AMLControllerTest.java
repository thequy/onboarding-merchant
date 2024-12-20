package soft.blue.onboardingmerchant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import soft.blue.onboardingmerchant.service.WorkerService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AMLController.class)
class AMLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkerService workerService;

    @Test
    void checkAML_WhenDataExists_ReturnsTrue() throws Exception {
        when(workerService.checkAML(anyString())).thenReturn(true);

        mockMvc.perform(get("/api/check-aml")
                .param("mobile", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void checkAML_WhenNoData_ReturnsFalse() throws Exception {
        when(workerService.checkAML(anyString())).thenReturn(false);

        mockMvc.perform(get("/api/check-aml")
                .param("mobile", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
