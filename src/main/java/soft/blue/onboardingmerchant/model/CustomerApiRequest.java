package soft.blue.onboardingmerchant.model;

import lombok.Data;

@Data
public class CustomerApiRequest {
    private String serviceCode = "biometric-share-info";
    private CustomerApiData data;

    @Data
    public static class CustomerApiData {
        private String citizenPid;
    }
}
