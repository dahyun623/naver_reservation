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

public class ProductReservatinoList extends HttpServlet {
	//중복 예약 확인
	private static final long serialVersionUID = 5637569662351767194L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    resp.setContentType("application/json; charset=UTF-8");
	    	    
	    int shopNo = Integer.parseInt(req.getParameter("shopNo"));
	    String day = req.getParameter("day");
	    String time = req.getParameter("time");
	    String email = req.getParameter("email");
	    
	    Map<String, Object> map = new HashMap<String, Object>();
		map.put("day", day);
		map.put("time", time);
		map.put("shopNo", shopNo);
		map.put("reservEmail", email);
		
		IReserveDao dao = new ReserveDaoImpl();
		boolean isc = dao.getReservationStatus(map);
		
		String result = (isc)?"addict":"notAddict";
        resp.getWriter().write(result);
	}
}