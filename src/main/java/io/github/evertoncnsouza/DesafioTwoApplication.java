package io.github.evertoncnsouza;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DesafioTwoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DesafioTwoApplication.class, args);

    }
}