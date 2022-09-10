package com.axonactive.roomLeaseManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Project Swagger", version = "0.0.1"))
@SpringBootApplication
public class RoomLeaseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomLeaseManagementApplication.class, args);
	}

}
