package soft.blue.onboardingmerchant.model;

public class CustomerInfoResponse {
    private boolean isTimeOut;
    private String description;

    public CustomerInfoResponse(boolean isTimeOut, String description) {
        this.isTimeOut = isTimeOut;
        this.description = description;
    }

    public boolean isTimeOut() {
        return isTimeOut;
    }

    public void setTimeOut(boolean timeOut) {
        isTimeOut = timeOut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
