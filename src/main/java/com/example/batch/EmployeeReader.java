package com.example.batch;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EmployeeReader implements ItemReader<List<Employee>> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public List<Employee> read() {
		return employeeService.getAllEmployeesNotExtracted(2);
	}
}
