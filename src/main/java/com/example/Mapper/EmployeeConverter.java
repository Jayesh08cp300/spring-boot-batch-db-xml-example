package com.example.Mapper;

import com.example.dto.EmployeeDto;
import com.example.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends Converter<EmployeeDto, Employee> {
	public EmployeeConverter() {
		super(EmployeeConverter::toEntity, EmployeeConverter::toDto);
	}

	private static Employee toEntity(EmployeeDto employeeDto) {
		return Employee.builder()
				.id(employeeDto.getId())
				.designation(employeeDto.getDesignation())
				.department(employeeDto.getDepartment())
				.name(employeeDto.getName())
				.extracted(employeeDto.getExtracted())
				.build();
	}

	private static EmployeeDto toDto(Employee employee) {
		return EmployeeDto.builder()
				.id(employee.getId())
				.designation(employee.getDesignation()
						.toUpperCase())
				.department(employee.getDepartment()
						.toUpperCase())
				.name(employee.getName()
						.toUpperCase())
				.extracted(employee.getExtracted())
				.fileName("c:/test/" + employee.getId() + "_" + employee.getName() + ".txt")
				.build();
	}
}
