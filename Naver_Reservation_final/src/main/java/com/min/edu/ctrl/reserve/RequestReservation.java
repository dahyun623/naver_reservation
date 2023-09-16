package com.min.edu.ctrl.reserve;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.ctrl.main.Main;
import com.min.edu.model.IReserveDao;
import com.min.edu.model.ReserveDaoImpl;
import com.min.edu.vo.Reservation;

public class RequestReservation extends HttpServlet {

	//예약
	private static final long serialVersionUID = -1018535565529010861L;
	private Logger logger = LoggerFactory.getLogger(Main.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");
		
		//값 받기
		int shopNo = Integer.parseInt(req.getParameter("shopNo"));
		String day = req.getParameter("day");
		String time = req.getParameter("time");
		String email = req.getParameter("email");
		String reservName = req.getParameter("reservName");
		String reservPhone = req.getParameter("reservPhone");
		String personCount = req.getParameter("personCount");
		String requestContent = req.getParameter("requestContent");
		
		//vo에 담기
		Reservation vo =new Reservation();
		vo.setShopNo(shopNo);
		vo.setDay(day);
		vo.setTime(time);
		vo.setReservEmail(email);
		vo.setReservName(reservName);
		vo.setReservPhone(reservPhone);
		vo.setPersonCount(personCount);
		vo.setRequestContent(requestContent);
		logger.info("입력할 vo : {}", vo);
		
		//dao 실행
		IReserveDao dao = new ReserveDaoImpl();
		int n = dao.setReservationInfo(vo);
		
		// 응답 데이터 설정
        String result = (n > 0) ? "success" : "fail";
        resp.getWriter().write(result);
	}
}
