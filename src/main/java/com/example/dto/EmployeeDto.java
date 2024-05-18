package com.example.dto;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	private Long id;
	private String name;
	private String designation;
	private String department;
	private Boolean extracted;
	private String fileName;
}
