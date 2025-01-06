package soft.blue.onboardingmerchant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TxnInitRequest {
    @Schema(description = "Citizen Personal ID", example = "035187003000")
    private String citizenPid;
}
