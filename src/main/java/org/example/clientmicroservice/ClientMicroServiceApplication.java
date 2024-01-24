package org.example.clientmicroservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
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
