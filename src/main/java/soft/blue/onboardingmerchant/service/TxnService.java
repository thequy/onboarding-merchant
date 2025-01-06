package soft.blue.onboardingmerchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import soft.blue.onboardingmerchant.model.CustomerApiRequest;
import soft.blue.onboardingmerchant.model.TxnInitRequest;
import soft.blue.onboardingmerchant.model.TxnInitResponse;

@Service
public class TxnService {

    private static final String CUSTOMER_API_URL = "https://624281fbd126926d0c524a1d.mockapi.io/api/v1/customer";

    @Autowired
    private RestTemplate restTemplate;

    public TxnInitResponse initTransaction(TxnInitRequest request) {
        CustomerApiRequest apiRequest = new CustomerApiRequest();
        CustomerApiRequest.CustomerApiData data = new CustomerApiRequest.CustomerApiData();
        data.setCitizenPid(request.getCitizenPid());
        apiRequest.setData(data);

        TxnInitResponse response = new TxnInitResponse();

        try {
            restTemplate.postForEntity(CUSTOMER_API_URL, apiRequest, Object.class);
            response.setTimeOut(false);
            response.setDescription("Hệ thống yêu cầu thực hiện thành công");
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setTimeOut(true);
            
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                response.setDescription("Token hết hạn");
            } else if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                response.setDescription("Không có quyền truy cập dịch vụ");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                response.setDescription("Địa chỉ yêu cầu không tồn tại");
            } else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                response.setDescription("Hệ thống timeout");
            }
        }

        return response;
    }
}
