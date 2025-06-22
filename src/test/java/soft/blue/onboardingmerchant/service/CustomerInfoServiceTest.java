package soft.blue.onboardingmerchant.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import soft.blue.onboardingmerchant.model.CustomerInfoRequest;
import soft.blue.onboardingmerchant.model.CustomerInfoResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerInfoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private CustomerInfoService customerInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerInfoService = new CustomerInfoService(restTemplate);
    }

    @Test
    void whenApiReturns200_thenReturnSuccessResponse() {
        when(restTemplate.postForEntity(anyString(), any(CustomerInfoRequest.class), any()))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        CustomerInfoResponse response = customerInfoService.initTransaction("035187003000");

        assertFalse(response.isTimeOut());
        assertEquals("Hệ thống yêu cầu thực hiện thành công", response.getDescription());
    }

    @Test
    void whenApiReturns401_thenReturnTokenExpiredResponse() {
        when(restTemplate.postForEntity(anyString(), any(CustomerInfoRequest.class), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        CustomerInfoResponse response = customerInfoService.initTransaction("035187003000");

        assertTrue(response.isTimeOut());
        assertEquals("Token hết hạn", response.getDescription());
    }

    @Test
    void whenApiReturns403_thenReturnAccessDeniedResponse() {
        when(restTemplate.postForEntity(anyString(), any(CustomerInfoRequest.class), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));

        CustomerInfoResponse response = customerInfoService.initTransaction("035187003000");

        assertTrue(response.isTimeOut());
        assertEquals("Không có quyền truy cập dịch vụ", response.getDescription());
    }

    @Test
    void whenApiReturns404_thenReturnNotFoundResponse() {
        when(restTemplate.postForEntity(anyString(), any(CustomerInfoRequest.class), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        CustomerInfoResponse response = customerInfoService.initTransaction("035187003000");

        assertTrue(response.isTimeOut());
        assertEquals("Địa chỉ yêu cầu không tồn tại", response.getDescription());
    }

    @Test
    void whenApiReturns500_thenReturnTimeoutResponse() {
        when(restTemplate.postForEntity(anyString(), any(CustomerInfoRequest.class), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        CustomerInfoResponse response = customerInfoService.initTransaction("035187003000");

        assertTrue(response.isTimeOut());
        assertEquals("Hệ thống timeout", response.getDescription());
    }
}
