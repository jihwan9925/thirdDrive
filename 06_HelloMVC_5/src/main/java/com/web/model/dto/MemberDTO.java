package com.web.model.dto;


import java.sql.Date;
import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//lombok라이브러리 활용하기
// 1. lombok.jar파일 다운로드
// 2. lombok 설정 => IDE도구 연동하기
// 3. lombok이 제공하는 어노테이션 이용해서 활용하기
@Data//setter, getter, 기본생성자, toString, equals, hash 생성

@AllArgsConstructor//매개변수 있는 생성자(전체 필드에 대해)
//@NoArgsConstructor//기본생성자
@Builder//빌더패턴
//@Getter
//@Setter
//@ToString(exclude= {"memberPwd"})
//@EqualsAndHashCode
@RequiredArgsConstructor//NonNull(notnull)설정, final설정된 필드를 매개변수 하는 생성자 생성

//window-show view-outline를 보며 변동사항 확인 가능
public class MemberDTO {
	
	private final String userId;
	private String password;
	private String userName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;

	
}
