package soft.blue.onboardingmerchant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/helloworld")
@Tag(name = "Hello World", description = "Hello World API")
public class HelloWorld {

    @GetMapping
    @Operation(
        summary = "Get Hello World message",
        description = "Returns a simple Hello World message in JSON format",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful response",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = HelloWorldResponse.class)
                )
            )
        }
    )
    public HelloWorldResponse getHelloWorld() {
        return new HelloWorldResponse("Hello World");
    }

    public static class HelloWorldResponse {
        private final String data;

        public HelloWorldResponse(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}
