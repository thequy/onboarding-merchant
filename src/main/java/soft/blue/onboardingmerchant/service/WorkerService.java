package soft.blue.onboardingmerchant.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class WorkerService {
    private static final String AML_API_URL = "https://624281fbd126926d0c524a1d.mockapi.io/api/v1/customer";
    private final RestTemplate restTemplate;

    public WorkerService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean checkAML(String mobile) {
        try {
            Object[] response = restTemplate.getForObject(AML_API_URL, Object[].class);
            return response != null && response.length > 0;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
