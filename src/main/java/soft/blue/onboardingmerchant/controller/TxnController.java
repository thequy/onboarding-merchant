package soft.blue.onboardingmerchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import soft.blue.onboardingmerchant.model.TxnInitRequest;
import soft.blue.onboardingmerchant.model.TxnInitResponse;
import soft.blue.onboardingmerchant.service.TxnService;

@RestController
@RequestMapping("/api/v1/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @Operation(summary = "Initialize transaction for customer information sharing with face authentication")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Request processed successfully",
            content = @Content(schema = @Schema(implementation = TxnInitResponse.class))),
        @ApiResponse(responseCode = "401", description = "Token expired"),
        @ApiResponse(responseCode = "403", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "500", description = "System timeout")
    })
    @PostMapping("/init")
    public ResponseEntity<TxnInitResponse> txnInit(@RequestBody TxnInitRequest request) {
        return ResponseEntity.ok(txnService.initTransaction(request));
    }
}
