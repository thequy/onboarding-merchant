package soft.blue.onboardingmerchant.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkerServiceTest {
    private WorkerService workerService;
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        workerService = new WorkerService();
        restTemplate = mock(RestTemplate.class);
        // Use reflection to inject mock RestTemplate
        try {
            var field = WorkerService.class.getDeclaredField("restTemplate");
            field.setAccessible(true);
            field.set(workerService, restTemplate);
        } catch (Exception e) {
            fail("Failed to inject mock RestTemplate");
        }
    }

    @Test
    void checkAML_WhenDataExists_ReturnsTrue() {
        Object[] mockResponse = new Object[]{new Object()};
        when(restTemplate.getForObject(anyString(), eq(Object[].class)))
            .thenReturn(mockResponse);

        boolean result = workerService.checkAML("1234567890");
        assertTrue(result);
    }

    @Test
    void checkAML_WhenNoData_ReturnsFalse() {
        when(restTemplate.getForObject(anyString(), eq(Object[].class)))
            .thenReturn(new Object[0]);

        boolean result = workerService.checkAML("1234567890");
        assertFalse(result);
    }

    @Test
    void checkAML_WhenApiNotFound_ReturnsFalse() {
        when(restTemplate.getForObject(anyString(), eq(Object[].class)))
            .thenThrow(HttpClientErrorException.NotFound.class);

        boolean result = workerService.checkAML("1234567890");
        assertFalse(result);
    }
}
