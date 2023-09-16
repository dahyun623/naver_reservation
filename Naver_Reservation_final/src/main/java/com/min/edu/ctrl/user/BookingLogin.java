package com.min.edu.ctrl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingLogin extends HttpServlet {

	private static final long serialVersionUID = 7034922179695397342L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("BookingLoginForm doGet 실행");
		
		String email = req.getParameter("resrv_email");
		logger.info("전달 받은 값 : {} ",email);
		
		HttpSession session = req.getSession();
		session.setAttribute("reservationEmail", email);
		logger.info("세션 값 확인 : {}",session.getAttribute("reservationEmail"));
		resp.sendRedirect("./myReservation.do");		
	}
}
