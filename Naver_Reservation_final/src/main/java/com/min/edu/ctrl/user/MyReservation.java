package com.min.edu.ctrl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyReservation extends HttpServlet {

	private static final long serialVersionUID = -5098354520529094459L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("MyReservation doGet 실행");
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("reservationEmail");
		logger.info("Session에서 가져온 값 : {} ",email);
		req.setAttribute("reservationEmail", email);
		if(email==null) {
			logger.info("email이 Null임");
			resp.sendRedirect("./bookingLoginForm.do");
		}else {
			req.getRequestDispatcher("/WEB-INF/views/myReservation.jsp").forward(req,resp);
		}
	}
}
