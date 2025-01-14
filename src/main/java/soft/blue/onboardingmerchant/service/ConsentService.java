package soft.blue.onboardingmerchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import soft.blue.onboardingmerchant.model.ConsentRequest;

@Service
public class ConsentService {
    private static final String API_URL = "http://eid-gw.dev.eidas.vn:8825/gw/api/tcdn/biometric-share-info/api/agent/init?code=VCB";

    @Autowired
    private RestTemplate restTemplate;

    public Object requestConsent(String requestId, String requestTime, ConsentRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("request_id", requestId);
        headers.set("request_time", requestTime);
        headers.set("client-id", "4f4cfd055287493b8f3221183b46470e");
        headers.set("client-secret", "4f4cfd055287493b8f3221183b46470e");

        HttpEntity<ConsentRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(API_URL, entity, Object.class);
    }
}
