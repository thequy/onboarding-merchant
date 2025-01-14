package soft.blue.onboardingmerchant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.blue.onboardingmerchant.model.ConsentRequest;
import soft.blue.onboardingmerchant.service.ConsentService;

@RestController
@RequestMapping("/api/consent")
@Tag(name = "Consent", description = "APIs for handling citizen information sharing consent")
public class ConsentController {

    @Autowired
    private ConsentService consentService;

    @PostMapping("/request")
    @Operation(
        summary = "Request citizen information sharing consent",
        description = "Initiates a request for citizen information sharing with face authentication",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            )
        }
    )
    public ResponseEntity<Object> requestConsent(
        @Parameter(description = "Unique request ID", required = true)
        @RequestHeader("request_id") String requestId,

        @Parameter(description = "Request timestamp in format ddMMyyyyHHmmss", required = true)
        @RequestHeader("request_time") String requestTime,

        @Parameter(description = "Request body containing citizen PID", required = true)
        @RequestBody ConsentRequest request
    ) {
        Object response = consentService.requestConsent(requestId, requestTime, request);
        return ResponseEntity.ok(response);
    }
}
