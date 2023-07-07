package com.emp.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//dto에 둘중 하나라도 부르면 서로가 서로를 부르기 때문에 스택이 남지않게 되어 오류를 일으킨다, 이를 해결하기 위해 아래 toString을 이용해서 employee를 
//Tostring에서 제외 시킨다.
//@ToString은 toString을 사용할 시 계속부르는것을 막기위한 것이기 때문에 일반적인 호출에는 전혀 문제가 없다.
@ToString(exclude= {"employee"})
public class Department {
	private String deptId;
	private String deptTitle;
	private String LocationId;
	private List<Employee> employee;
	
	
}
