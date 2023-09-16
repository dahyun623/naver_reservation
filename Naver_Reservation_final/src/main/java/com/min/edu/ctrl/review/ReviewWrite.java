package com.min.edu.ctrl.review;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class ReviewWrite extends HttpServlet {

	private static final long serialVersionUID = -3318544782724171571L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reservno = req.getParameter("reservno");
		String productId = req.getParameter("productId");
		req.setAttribute("reservno", reservno);
		req.setAttribute("productId", productId);
		req.getRequestDispatcher("WEB-INF/views/reviewWriteForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");
		
		logger.info("이미지 썸네일 doPost실행");
		
		ServletContext context = req.getServletContext();
		// 서버 실제 저장 경로
		String saveDirectory = context.getRealPath("upload");
		// 상대경로
		String[] URI = req.getRequestURI().split("/");
		
		String encoding = "UTF-8";
		int maxPostSize = 10*1024*1024;
		MultipartRequest multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		int imageIndex = Integer.parseInt(multi.getParameter("imageIndex"));
		
		StringBuffer sb = new StringBuffer();
		sb.append("<li id='item" + imageIndex + "' class='item'>");
		sb.append("<a href=\"#\" class=\"anchor\" onclick=\"removeItem('item" + imageIndex + "')\">\n"
	            + " <span class=\"spr_book ico_del\">삭제</span>\n"
	            + "</a>");
		sb.append("<span class=\"img_border\"></span>");
		sb.append("<img id='item"+ imageIndex +"'src='/"+URI[1]+"/upload/"+multi.getOriginalFileName("image")+"'width=\"130\" alt=\"\" class=\"item_thumb\">");
		sb.append("</li>");
		System.out.println(sb);
		resp.getWriter().write(sb.toString());
	}
}
