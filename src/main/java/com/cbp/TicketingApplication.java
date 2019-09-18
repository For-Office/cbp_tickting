package com.cbp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableAutoConfiguration
public class TicketingApplication{
	public static void main(String[] args) {
		SpringApplication.run(TicketingApplication.class, args);
	}
}
