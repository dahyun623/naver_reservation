package com.min.edu.ctrl.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;

import net.sf.json.JSONObject;

public class MyReservationStatus extends HttpServlet {

	private static final long serialVersionUID = -539333287681659442L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("MyReservationStatus doGET 실행");
		IUserDao dao = new UserDaoImpl();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("reservationEmail");
		logger.info("Session에서 가져온 email 값 : {}", email);
		Map<String,Object> status = dao.getMyReservationStatus(email);
		JSONObject json = JSONObject.fromObject(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        System.out.println(json.toString());
        resp.getWriter().println(json.toString());
	}
}
