package soft.blue.onboardingmerchant.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import soft.blue.onboardingmerchant.model.CustomerInfoRequest;
import soft.blue.onboardingmerchant.model.CustomerInfoResponse;

@Service
public class CustomerInfoService {
    private static final String API_URL = "https://624281fbd126926d0c524a1d.mockapi.io/api/v1/customer";

    private final RestTemplate restTemplate;

    public CustomerInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerInfoResponse initTransaction(String citizenPid) {
        try {
            CustomerInfoRequest request = new CustomerInfoRequest(citizenPid);
            ResponseEntity<Object> response = restTemplate.postForEntity(API_URL, request, Object.class);
            
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                return new CustomerInfoResponse(false, "Hệ thống yêu cầu thực hiện thành công");
            }
            
            return handleErrorResponse(HttpStatus.valueOf(response.getStatusCode().value()));
        } catch (HttpClientErrorException e) {
            return handleErrorResponse(HttpStatus.valueOf(e.getStatusCode().value()));
        } catch (HttpServerErrorException e) {
            return handleErrorResponse(HttpStatus.valueOf(e.getStatusCode().value()));
        } catch (Exception e) {
            return new CustomerInfoResponse(true, "Hệ thống timeout");
        }
    }

    private CustomerInfoResponse handleErrorResponse(HttpStatus status) {
        switch (status) {
            case UNAUTHORIZED:
                return new CustomerInfoResponse(true, "Token hết hạn");
            case FORBIDDEN:
                return new CustomerInfoResponse(true, "Không có quyền truy cập dịch vụ");
            case NOT_FOUND:
                return new CustomerInfoResponse(true, "Địa chỉ yêu cầu không tồn tại");
            case INTERNAL_SERVER_ERROR:
                return new CustomerInfoResponse(true, "Hệ thống timeout");
            default:
                return new CustomerInfoResponse(true, "Hệ thống timeout");
        }
    }
}
