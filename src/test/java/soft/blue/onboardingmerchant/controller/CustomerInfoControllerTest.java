package soft.blue.onboardingmerchant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import soft.blue.onboardingmerchant.model.CustomerInfoResponse;
import soft.blue.onboardingmerchant.service.CustomerInfoService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerInfoController.class)
class CustomerInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerInfoService customerInfoService;

    @Test
    void whenInitTransaction_thenReturnSuccessResponse() throws Exception {
        CustomerInfoResponse mockResponse = new CustomerInfoResponse(false, "Hệ thống yêu cầu thực hiện thành công");
        when(customerInfoService.initTransaction(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/txnInit")
                .param("citizenPid", "035187003000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timeOut").value(false))
                .andExpect(jsonPath("$.description").value("Hệ thống yêu cầu thực hiện thành công"));
    }

    @Test
    void whenInitTransaction_thenReturnErrorResponse() throws Exception {
        CustomerInfoResponse mockResponse = new CustomerInfoResponse(true, "Token hết hạn");
        when(customerInfoService.initTransaction(anyString())).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/txnInit")
                .param("citizenPid", "035187003000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timeOut").value(true))
                .andExpect(jsonPath("$.description").value("Token hết hạn"));
    }
}
