package com.min.edu.ctrl.review;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.min.edu.model.IReviewDao;
import com.min.edu.model.ReviewDaoImpl;
import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public class ReviewMore extends HttpServlet {

	private static final long serialVersionUID = -4177555691735873447L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String productId =  req.getParameter("productId");
		
		IReviewDao dao = new ReviewDaoImpl();
		Review score = dao.getReviewScore(productId);
		List<Review> lists = dao.getReview(productId);
		
		req.setAttribute("score", score);
		req.setAttribute("lists", lists);
		
		req.getRequestDispatcher("/WEB-INF/views/review.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8;");
		
		String command = req.getParameter("command");
		System.out.println(command);
		if(command.equalsIgnoreCase("more")) {
			String productId =  req.getParameter("productId");
			String start = req.getParameter("start");
			String end = req.getParameter("end");
			
			System.out.println("start:"+start);
			System.out.println("end:"+end);
			System.out.println("productId:"+productId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productId", productId);
			map.put("start", start);
			map.put("end", end);
			
			IReviewDao dao = new ReviewDaoImpl();
			List<Review> lists = dao.getReviewMore(map);
			
			Gson gson = new Gson();
			String json = gson.toJson(lists);
			
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.flush();
			System.out.println("resp json:" + json);
		} else if(command.equalsIgnoreCase("thumbs")) {
				String originalFilename = req.getParameter("image");  // 이미지 파일명을 가져옴
				
				String[] URI = req.getRequestURI().split("/");
				String imageUrl = "/"+URI[1]+"/upload/"+originalFilename;
				
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("imageUrl: "+imageUrl);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imageUrl", imageUrl);
				
				Gson gson = new Gson();
				
				String json = gson.toJson(map);
			    
			    // 이미지 URL을 JSON 형태로 클라이언트에게 반환
			    PrintWriter out = resp.getWriter();
			    out.print(json);
			    out.flush();
		}
		
	}

}
