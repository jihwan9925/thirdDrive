package com.web.common.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptoWrapper extends HttpServletRequestWrapper{
	
	public PasswordEncryptoWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		if(key.equals("password")) {
			//암호화 처리 로직
			System.out.println("원본 : "+super.getParameter(key));
			String encyriptData=getSHA512(super.getParameter(key));
			System.out.println("암호화값 : "+encyriptData);
			return encyriptData;
		}
		//그냥 그대로 보내기
		return super.getParameter(key);
	}
	
	private String getSHA512(String oriVal) {
		//단방향암호화 처리하기
		//java에서 제공하는 클래스와 메소드를 이용한다.
		//MessageDigest클레스 이용
		//java제공하는 암호화 알고리즘을 한다.
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//생성된 MessageDigest클레스를 이용해서 원본값 암호화처리
		//암호화처리는 byte단위로 쪼개서 처리를 함.
		byte[] oriValByte=oriVal.getBytes();
		md.update(oriValByte);
		byte[] encryptData=md.digest();
		String encyriptStrData=Base64.getEncoder().encodeToString(encryptData);
		
		return encyriptStrData;
	}
}
