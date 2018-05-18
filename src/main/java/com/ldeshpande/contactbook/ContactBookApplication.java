package com.ldeshpande.contactbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
@EnableAutoConfiguration
public class ContactBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactBookApplication.class, args);
    }

}
