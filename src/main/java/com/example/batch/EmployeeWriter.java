package com.example.batch;

import com.example.dto.EmployeeDto;
import com.example.service.EmployeeService;
import com.example.util.FileOperationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeWriter implements ItemWriter<List<EmployeeDto>> {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FileOperationUtil fileOperationUtil;

	@Override
	public void write(Chunk<? extends List<EmployeeDto>> chunk) {
		chunk.getItems()
				.parallelStream()
				.forEach(s -> {
					log.info("++++++++++++");
					updateEmployeeStatusAndLog(s);
				});
	}

	private void updateEmployeeStatusAndLog(List<EmployeeDto> s) {
		try {
			List<CompletableFuture<Void>> futures = s.parallelStream()
					.map(employeeDto -> {
						return fileOperationUtil.writeFile(employeeDto);
					})
					.collect(Collectors.toList());

			// Combine all CompletableFutures
			CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

			allOf.join(); // Wait for all futures to complete
			System.out.println("All files written successfully.");

			s.parallelStream()
					.forEach(employeeService::updateEmployeeIsExtracted);

		} catch (Exception e) {
			System.err.println("An error occurred. Rolling back...");
			// Rollback by deleting all files
			s.parallelStream()
					.forEach(fileOperationUtil::deleteFile);
		}

	}
}
