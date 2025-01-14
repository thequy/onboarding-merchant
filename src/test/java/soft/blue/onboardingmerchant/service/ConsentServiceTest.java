package soft.blue.onboardingmerchant.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import soft.blue.onboardingmerchant.model.ConsentRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsentServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsentService consentService;

    @Test
    void testRequestConsent() {
        // Given
        String requestId = "09872e2f-05e9-46b4-aee7-8104ad57ab0c";
        String requestTime = "17072024093800";
        ConsentRequest request = new ConsentRequest();
        request.setCitizenPid("038200013772");

        Object mockResponse = new Object();
        when(restTemplate.postForObject(
            eq("http://eid-gw.dev.eidas.vn:8825/gw/api/tcdn/biometric-share-info/api/agent/init?code=VCB"),
            any(HttpEntity.class),
            eq(Object.class)
        )).thenReturn(mockResponse);

        // When
        consentService.requestConsent(requestId, requestTime, request);

        // Then
        verify(restTemplate).postForObject(
            eq("http://eid-gw.dev.eidas.vn:8825/gw/api/tcdn/biometric-share-info/api/agent/init?code=VCB"),
            any(HttpEntity.class),
            eq(Object.class)
        );
    }
}
