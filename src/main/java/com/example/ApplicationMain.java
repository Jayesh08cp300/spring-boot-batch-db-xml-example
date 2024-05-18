package com.example;

import com.example.batch.config.EmployeeJobRunner;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ApplicationMain implements ApplicationRunner {

	@Autowired
	EmployeeJobRunner employeeJobRunner;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		employeeJobRunner.runEmployeeJob();
	}
}
