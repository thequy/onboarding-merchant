package soft.blue.onboardingmerchant.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import soft.blue.onboardingmerchant.model.ConsentRequest;
import soft.blue.onboardingmerchant.service.ConsentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConsentControllerTest {

    @Mock
    private ConsentService consentService;

    @InjectMocks
    private ConsentController consentController;

    @Test
    void testRequestConsent() {
        // Given
        String requestId = "09872e2f-05e9-46b4-aee7-8104ad57ab0c";
        String requestTime = "17072024093800";
        ConsentRequest request = new ConsentRequest();
        request.setCitizenPid("038200013772");

        Object mockResponse = new Object();
        when(consentService.requestConsent(eq(requestId), eq(requestTime), any(ConsentRequest.class)))
            .thenReturn(mockResponse);

        // When
        ResponseEntity<Object> response = consentController.requestConsent(requestId, requestTime, request);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockResponse, response.getBody());
    }
}
