package soft.blue.onboardingmerchant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import soft.blue.onboardingmerchant.model.TxnInitRequest;
import soft.blue.onboardingmerchant.model.TxnInitResponse;

@ExtendWith(MockitoExtension.class)
public class TxnServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TxnService txnService;

    @Test
    public void testInitTransactionSuccess() {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        when(restTemplate.postForEntity(anyString(), any(), any())).thenReturn(null);

        TxnInitResponse response = txnService.initTransaction(request);

        assertFalse(response.isTimeOut());
        assertEquals("Hệ thống yêu cầu thực hiện thành công", response.getDescription());
    }

    @Test
    public void testInitTransactionUnauthorized() {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        when(restTemplate.postForEntity(anyString(), any(), any()))
            .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        TxnInitResponse response = txnService.initTransaction(request);

        assertTrue(response.isTimeOut());
        assertEquals("Token hết hạn", response.getDescription());
    }

    @Test
    public void testInitTransactionForbidden() {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        when(restTemplate.postForEntity(anyString(), any(), any()))
            .thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        TxnInitResponse response = txnService.initTransaction(request);

        assertTrue(response.isTimeOut());
        assertEquals("Không có quyền truy cập dịch vụ", response.getDescription());
    }

    @Test
    public void testInitTransactionNotFound() {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        when(restTemplate.postForEntity(anyString(), any(), any()))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        TxnInitResponse response = txnService.initTransaction(request);

        assertTrue(response.isTimeOut());
        assertEquals("Địa chỉ yêu cầu không tồn tại", response.getDescription());
    }

    @Test
    public void testInitTransactionTimeout() {
        TxnInitRequest request = new TxnInitRequest();
        request.setCitizenPid("035187003000");

        when(restTemplate.postForEntity(anyString(), any(), any()))
            .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        TxnInitResponse response = txnService.initTransaction(request);

        assertTrue(response.isTimeOut());
        assertEquals("Hệ thống timeout", response.getDescription());
    }
}
