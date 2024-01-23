package org.example.clientmicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientMicroServiceApplication {




    public static void main(String[] args) {
        SpringApplication.run(ClientMicroServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(){
        return args -> {

        };
    }

}
