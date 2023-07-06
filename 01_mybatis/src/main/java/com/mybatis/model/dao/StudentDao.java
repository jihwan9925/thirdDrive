package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Student;

public class StudentDao {
	
	public int insertStudent(SqlSession session) {
		//sql문을 실행할 때는 session이 제공하는 메소드를 호출하면 된다
		//selectOne(), selectList(), insert(), update(), delete()
		//statement 인수는 "mapper태그 namespace값.sql태그id값"
		int result = session.insert("student.insertStudent");
		return result;
	}
	
	public int insertStudentByName(SqlSession session, String name) {
		int result = session.insert("student.insertStudentByName",name);
		return result;
	}
	
	public int insertStudentAll(SqlSession session, Student s) {
		return session.insert("student.insertStudentAll",s);
	}
	
	public int updateStudent(SqlSession session, Student s) {
		return session.update("student.updateStudent",s);
	}

	public int deleteStudent(SqlSession session, String no) {
		return session.delete("student.deleteStudent",no);
	}
	
	public int selectStudentCount(SqlSession session) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		return session.selectOne("student.selectStudentCount");
	}

	public Student selectStudent(SqlSession session,int no) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		return session.selectOne("student.selectStudent",no);
	}

	public List<Student> selectStudentAll(SqlSession session) {
		//selectOne()메소드를 이용해서 데이터를 조회할 수 있다.
		return session.selectList("student.selectStudentAll");
	}
	
	public List<Student> selectStudentByName(SqlSession session, String name) {
		String rename = "%"+name+"%";
		return session.selectList("student.selectStudentByName",rename);
	}
	
	public Map selectStudentByName(SqlSession session, int no) {
		return session.selectOne("student.selectStudentMap",no);
	}
	
	public List<Map> selectStudentListMap(SqlSession session){
		return session.selectList("student.selectStudentListMap");
	}
	
	public List<Student> selectStudentPage(SqlSession session, int cPage, int numPerpage){
		//페이지처리할때는 mybatis가 제공하는 페이징처리 클래스를 사용한다
		//RowBounds 클래스를 사용
		//selectList호출시 세번째 매개변수에 RowBounds클래스를 생성해서 전달하면 된다.
		//매개변수가 있는 생성자를 이용
		// 첫번째 매개변수 : offset -> 시작 row번호 (cPage-1)*numPerpage
		// 두번째 매개변수 : 범위 -> numPerpage
		
		// mapper에 보낼 세번째 매개변수 : new RowBounds((cPage-1)*numPerpage,numPerpage);
		RowBounds rb = new RowBounds((cPage-1)*numPerpage,numPerpage);
		List<Student> list = session.selectList("student.selectStudentPage",null,rb);
		System.out.println(list);
		return list;
	}
}
