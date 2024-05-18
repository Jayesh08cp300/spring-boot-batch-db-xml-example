package com.example.util;

import com.example.dto.EmployeeDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Component
public class FileOperationUtil {
	public CompletableFuture<Void> writeFile(EmployeeDto employeeDto) {
		return CompletableFuture.runAsync(() -> {
			Path path = Paths.get(employeeDto.getFileName());
			try {
				String content = new XmlMapper().writeValueAsString(employeeDto);
				Files.write(path, content.getBytes());
				System.out.println("Written to file: " + employeeDto.getFileName());
			} catch (IOException e) {
				throw new RuntimeException("Failed to write to file: " + employeeDto.getFileName(), e);
			}
		});
	}

	public void deleteFile(EmployeeDto employeeDto) {
		Path path = Paths.get(employeeDto.getFileName());
		try {
			Files.deleteIfExists(path);
			System.out.println("Deleted file: " + employeeDto.getFileName());
		} catch (IOException e) {
			System.err.println("Failed to delete file: " + employeeDto.getFileName());
		}
	}
}
