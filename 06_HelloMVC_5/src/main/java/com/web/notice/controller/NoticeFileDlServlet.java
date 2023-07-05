package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDlServlet
 */
@WebServlet("/fileDl.do")
public class NoticeFileDlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일에 대해 다운로드기능 추가
		//1. 클라이언트가 보낸 파일이름받기
		String filename=request.getParameter("name");
		//2. 파일의 절대경로를 가져오기
		String path=getServletContext().getRealPath("/upload/notice/");
		File f=new File(path+filename);
		//3. InputStream을 생성 -> FileInputStream을 생성
		FileInputStream fils=new FileInputStream(f);
		//ㄴ BufferedInputStream : 속도증가 클래스
		BufferedInputStream bis=new BufferedInputStream(fils);
		//4. 한글파일명이 깨지지않도록 인코딩처리하기(파일명에 한글이 없다면 안해도 된다.)
		String fileRename="";
		String header=request.getHeader("user-agent");
		//IE,와 그외 브라우저는 인코딩처리방식이 서로 달라서 인코딩을 분리해서 처리해야한다.
		boolean isMSIE=header.indexOf("MSIE")!=-1||header.indexOf("Trident")!=-1;
		if(isMSIE) {
			fileRename=URLEncoder.encode(filename,"UTF-8").replaceAll("//+","%20");
		}else {
			fileRename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		//5. 응답해더설정 -> contentType설정
		response.setContentType("application/octet-stream");
		//attachment : 다운가능하게 , index : 웹상에서 보여줄 수 있게
		response.setHeader("Content-disposition", "attachment;filename="+fileRename);
		
		//6. 사용자에게 파일 보내기
		//ㄴ 클라이언트의 스트림을 가져오기 -> getOutputStream();
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		//ㄴ
		int read=-1; //-1 : 더읽을 값이 없다!!!
		while((read=bis.read())!=-1){
			bos.write(read);
		}
		bis.close();
		bos.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
