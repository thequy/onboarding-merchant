package soft.blue.onboardingmerchant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soft.blue.onboardingmerchant.service.WorkerService;

@RestController
@Tag(name = "AML", description = "AML Check API")
public class AMLController {

    private final WorkerService workerService;

    @Autowired
    public AMLController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Operation(summary = "Check AML status by mobile number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully checked AML status"),
        @ApiResponse(responseCode = "400", description = "Invalid mobile number provided")
    })
    @GetMapping("/api/check-aml")
    public boolean checkAML(
        @Parameter(description = "Customer mobile number", required = true)
        @RequestParam String mobile
    ) {
        return workerService.checkAML(mobile);
    }
}
