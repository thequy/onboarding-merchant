package soft.blue.onboardingmerchant.model;

public class CustomerInfoRequest {
    private String serviceCode;
    private CustomerInfoData data;

    public CustomerInfoRequest(String citizenPid) {
        this.serviceCode = "biometric-share-info";
        this.data = new CustomerInfoData(citizenPid);
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public CustomerInfoData getData() {
        return data;
    }

    public void setData(CustomerInfoData data) {
        this.data = data;
    }

    public static class CustomerInfoData {
        private String citizenPid;

        public CustomerInfoData(String citizenPid) {
            this.citizenPid = citizenPid;
        }

        public String getCitizenPid() {
            return citizenPid;
        }

        public void setCitizenPid(String citizenPid) {
            this.citizenPid = citizenPid;
        }
    }
}
