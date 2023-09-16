package com.min.edu.ctrl.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends HttpServlet {

	private static final long serialVersionUID = 2790077288748655316L;
	private Logger logger = LoggerFactory.getLogger(Main.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		logger.info(">>>>>>>>>> Main doGet");
		
		req.getRequestDispatcher("WEB-INF/views/main.jsp").forward(req, resp);
	}
}
