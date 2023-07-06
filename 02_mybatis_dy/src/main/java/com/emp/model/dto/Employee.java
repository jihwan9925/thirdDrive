package com.emp.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	private int empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private String deftCode;
	private String jobCode;
	private String salLevel;
	private int salary;
	private double bouns;
	private int managerId;
	private Date hireDate;
	private Date entDate;
	private String entYn;
	private String gender;
}
