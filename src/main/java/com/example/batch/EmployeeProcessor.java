package com.example.batch;

import com.example.Mapper.EmployeeConverter;
import com.example.dto.EmployeeDto;
import com.example.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<List<Employee>, List<EmployeeDto>> {

	@Autowired
	private EmployeeConverter employeeConverter;

	@Override
	public List<EmployeeDto> process(List<Employee> employees) throws InterruptedException {
		if (employees.isEmpty()) {
			log.info("No Employee Record found.");
			Thread.sleep(30000);
			return null;
		}

		return employees.parallelStream()
				.map(employee -> employeeConverter.convertToDto(employee))
				.collect(Collectors.toList());
	}
}
