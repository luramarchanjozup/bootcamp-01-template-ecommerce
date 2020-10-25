package br.com.carlos.ecommerce.config;

import br.com.carlos.ecommerce.domain.service.EmailService;
import br.com.carlos.ecommerce.domain.service.impl.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class EnvironmentConfig {
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
