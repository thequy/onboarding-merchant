package soft.blue.onboardingmerchant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TxnInitResponse {
    @Schema(description = "Timeout status", example = "false")
    private boolean isTimeOut;
    
    @Schema(description = "Response description", example = "Hệ thống yêu cầu thực hiện thành công")
    private String description;
}
