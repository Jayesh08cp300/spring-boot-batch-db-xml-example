package com.example.batch.config;

import com.example.batch.EmployeeProcessor;
import com.example.batch.EmployeeReader;
import com.example.batch.EmployeeWriter;
import com.example.dto.EmployeeDto;
import com.example.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@Slf4j
public class BatchConfig {

	public static final String EMPLOYEE_JOB = "EmployeeJob";
	public static final String EMPLOYEE_STEP = "EmployeeStep";

	final JobRepository jobRepository;
	final PlatformTransactionManager platformTransactionManager;

	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		this.jobRepository = jobRepository;
		this.platformTransactionManager = platformTransactionManager;
	}

	@Bean
	public Job employeeJob() {
		return new JobBuilder(EMPLOYEE_JOB, jobRepository).incrementer(new RunIdIncrementer())
				.start(employeeStep())
				.build();
	}

	@Bean
	public Step employeeStep() {
		return new StepBuilder(EMPLOYEE_STEP, jobRepository).<List<Employee>, List<EmployeeDto>>chunk(1, platformTransactionManager)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public ItemReader<List<Employee>> reader() {
		return new EmployeeReader();
	}

	@Bean
	public ItemProcessor<List<Employee>, List<EmployeeDto>> processor() {
		return new EmployeeProcessor();
	}

	@Bean
	public ItemWriter<List<EmployeeDto>> writer() {
		return new EmployeeWriter();
	}

}
