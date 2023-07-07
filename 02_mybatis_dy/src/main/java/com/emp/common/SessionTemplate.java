package com.emp.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionTemplate {
	//mybatis가 제공하는 SqlSession객체를 생성해주는 공용메소드를 선언 -> static
	
	public static SqlSession getSession() {
		SqlSession session = null;
		String file = "mybatis-config.xml";
		try(InputStream is = Resources.getResourceAsStream(file);){
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public static SqlSession getWebSession() {
		String file = "mybatis-config.xml";
		SqlSession session=null;
		try(InputStream is = Resources.getResourceAsStream(file);){
			session = new SqlSessionFactoryBuilder().build(is,"web")
					.openSession(false);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
