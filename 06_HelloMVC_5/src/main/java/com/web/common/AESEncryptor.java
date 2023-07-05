package com.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//양방향 암호화하는 메소드를 제공하는 클래스
//java에서 제공하는 글래스를 이용해서 양방향 암호화를 진행.
//java.crypto패키지, java.security패키지에서 제공하는 클래스를 이용
// 양방향암호화는 암호화와 복호화할 수 있는 key가 있어야한다.
// 대칭키 암호화 : 암호화, 복호화 키가 한개
// 비대칭 암호화 : 암호화키 1개(공개키), 복호화키 1개(개인키) 두개가 쌍으로 있다.
// ㄴRSA방식의 암호화 알고리즘

//1. key를 생성
//2. 암호화 메소드 선언
//3. 복호화 메소드 선언

public class AESEncryptor {
	
	private static SecretKey key; //암호화키 저장하는 객체
	//한번 생성한 키를 유지하기 위해 파일로 저장
	private String path; //키가 파일로 저장된 위치를 알려주는 변수
	
	public AESEncryptor() {
		//key값을 생성하거나 가져오기
		//1. 생성된 키가 있으면 저장된 파일에서 key를 가져오고
		//2. 생성된 키가 없으면 SecretKey클래스를 생성하여 key변수에 저장
		// ㄴ key를 저장할 파일명은 bslove.bs
		this.path=AESEncryptor.class.getResource("/").getPath(); //AESEncryptor.class.getResource("/")
		this.path=this.path.substring(0,this.path.indexOf("classes"));
		System.out.println(path);
		File keyFile=new File(this.path+"bslove.bs");
		if(keyFile.exists()) { //있으면 없으면
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(keyFile));) {
				this.key=(SecretKey)ois.readObject();
			}catch(IOException|ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			generateKey(keyFile);
		}
	}
	
	private void generateKey(File keyFile) {
		//java.crypto패키지에서 제공하는 KeyGenerate클래스를 이용해서 SecretKey를 생성한다.
		
		//key생성시 salt값이 필요함
		SecureRandom rnd=new SecureRandom(); //일반랜덤보다 중복이 적은 랜덤
		KeyGenerator keygen=null;
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(keyFile))) {
			keygen=KeyGenerator.getInstance("AES");	
			keygen.init(128,rnd); //128비트 , rnd랜덤값
			//키를 생성해서 멤버변수에 저장
			AESEncryptor.key=keygen.generateKey();
			//생성된 키를 파일로 저장
			oos.writeObject(key);
			
		}catch(IOException|NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//암호화 메소드
	public static String encryptData(String oriData) throws Exception {
		//암호화 처리하는 메소드
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, AESEncryptor.key);
		
		byte[] targetData=oriData.getBytes(Charset.forName("UTF-8"));
		byte[] encryptData=cipher.doFinal(targetData);//=========================
		
		return Base64.getEncoder().encodeToString(encryptData);
	}
	
	
	//복호화 메소드
	public static String decryptData(String encryptData) throws Exception{
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncryptor.key);
		
		byte[] encrypt=Base64.getDecoder().decode(encryptData.getBytes(Charset.forName("UTF-8")));
		byte[] decryptData=cipher.doFinal(encrypt);
		return new String(decryptData);
	}
}
