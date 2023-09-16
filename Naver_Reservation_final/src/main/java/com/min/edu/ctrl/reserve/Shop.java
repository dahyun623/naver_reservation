package com.min.edu.ctrl.reserve;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReserveDao;
import com.min.edu.model.ReserveDaoImpl;

public class Shop extends HttpServlet {
	//같은 시간에 예약 몇개인지(최대 예약갯수 못넘게)
	private static final long serialVersionUID = 4591250710488916039L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setContentType("application/json; charset=UTF-8");
	    	    
	    int shopNo = Integer.parseInt(req.getParameter("shopNo"));
	    String time = req.getParameter("time");
	    
	    Map<String, Object> map = new HashMap<String, Object>();
		map.put("ableTime", time);
		map.put("shopNo", shopNo);
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 받은 time 값 : "+ time);
		
		IReserveDao dao = new ReserveDaoImpl();
		int n = dao.getReservationCount(map);
		
		String result = (n>4)?"over":"notOver";
	    resp.getWriter().write(result);
	}
}
