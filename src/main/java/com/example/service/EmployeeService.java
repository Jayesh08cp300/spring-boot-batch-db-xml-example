package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployeesNotExtracted(int limit) {
		Pageable pageable = PageRequest.of(0, limit);
		return employeeRepository.findByExtractedFalse(pageable);
	}

	public boolean isEmployeeAvailable() {
		return employeeRepository.countByExtractedFalse() > 0;
	}

	@Transactional
	public Employee updateEmployeeIsExtracted(EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findById(employeeDto.getId())
				.orElseThrow(() -> new RuntimeException("There is no Employee found to update."));
		employee.setExtracted(true);
		return employeeRepository.save(employee);
	}
}
