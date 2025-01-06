package soft.blue.onboardingmerchant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soft.blue.onboardingmerchant.model.CustomerInfoResponse;
import soft.blue.onboardingmerchant.service.CustomerInfoService;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Customer Info", description = "APIs for customer information sharing with face authentication")
public class CustomerInfoController {

    private final CustomerInfoService customerInfoService;

    public CustomerInfoController(CustomerInfoService customerInfoService) {
        this.customerInfoService = customerInfoService;
    }

    @Operation(
        summary = "Initialize transaction for customer information sharing",
        description = "Initiates a request to share customer information with face authentication"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
            content = @Content(schema = @Schema(implementation = CustomerInfoResponse.class))),
        @ApiResponse(responseCode = "401", description = "Token expired"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "Service not found"),
        @ApiResponse(responseCode = "500", description = "System timeout")
    })
    @PostMapping("/txnInit")
    public ResponseEntity<CustomerInfoResponse> initTransaction(
            @Parameter(description = "Customer's citizen ID", required = true)
            @RequestParam String citizenPid) {
        CustomerInfoResponse response = customerInfoService.initTransaction(citizenPid);
        return ResponseEntity.ok(response);
    }
}
