package soft.blue.onboardingmerchant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Hello Fao", description = "Hello Fao API")
public class HelloFaoController {

    @GetMapping("/hello-fao")
    @Operation(summary = "Get Hello Fao message", description = "Returns a JSON response with 'Hello Fao' message")
    public ResponseEntity<Map<String, String>> getHelloFao() {
        return ResponseEntity.ok(Map.of("data", "Hello Fao"));
    }
}
