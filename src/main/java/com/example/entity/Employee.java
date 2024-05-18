package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {

	@Id
	private Long id;
	private String name;
	private String designation;
	private String department;
	private Boolean extracted;

}
