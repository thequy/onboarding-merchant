package soft.blue.onboardingmerchant.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import soft.blue.onboardingmerchant.model.TxnInitRequest;
import soft.blue.onboardingmerchant.model.TxnInitResponse;
import soft.blue.onboardingmerchant.service.TxnService;

@WebMvcTest(TxnController.class)
public class TxnControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TxnService txnService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testTxnInitSuccess() throws Exception {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        TxnInitResponse response = new TxnInitResponse();
        response.setTimeOut(false);
        response.setDescription("Hệ thống yêu cầu thực hiện thành công");

        when(txnService.initTransaction(any(TxnInitRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/txn/init")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timeOut").value(false))
                .andExpect(jsonPath("$.description").value("Hệ thống yêu cầu thực hiện thành công"));
    }
}
