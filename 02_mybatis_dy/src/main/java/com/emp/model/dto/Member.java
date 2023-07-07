package com.emp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private String age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private String enrollDate;
}
