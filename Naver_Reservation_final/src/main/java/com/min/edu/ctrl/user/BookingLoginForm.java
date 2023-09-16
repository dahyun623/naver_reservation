package com.min.edu.ctrl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingLoginForm extends HttpServlet {

	private static final long serialVersionUID = 8543972859729741092L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("BookingLoginForm doGet 실행");
		req.getRequestDispatcher("./WEB-INF/views/bookingLoginForm.jsp").forward(req, resp);
	}
}
