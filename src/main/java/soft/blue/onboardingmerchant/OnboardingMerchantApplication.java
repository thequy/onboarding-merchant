package soft.blue.onboardingmerchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Onboarding Merchant API",
        version = "1.0",
        description = "API for merchant onboarding"
    )
)
public class OnboardingMerchantApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnboardingMerchantApplication.class, args);
    }
}
