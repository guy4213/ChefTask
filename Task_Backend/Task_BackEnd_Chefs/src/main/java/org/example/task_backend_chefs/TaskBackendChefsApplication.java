package org.example.task_backend_chefs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TaskBackendChefsApplication {

	public static void main(String[] args) {



		SpringApplication.run(TaskBackendChefsApplication.class, args);

	}

}
