package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeJobRunner {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public void runEmployeeJob()
			throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException,
			JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder().addString("EmployeeJob", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, jobParameters);
	}
}
