package com.min.edu.ctrl.reserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReserveDao;
import com.min.edu.model.ReserveDaoImpl;

public class ProductTime extends HttpServlet {
	
	//해당 날짜의 예약가능한 시간 출력
	private static final long serialVersionUID = 7348284708683351979L;

	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       resp.setContentType("application/json; charset=UTF-8");

       int productId = Integer.parseInt(req.getParameter("productId"));
		int shopNo = Integer.parseInt(req.getParameter("shopNo"));
		
		IReserveDao dao = new ReserveDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId",productId);
		map.put("shopNo",shopNo);
		List<String> productTime = dao.getProductTime(map);
       
	    // JSON 형태로 예약시간 정보 전송
	    PrintWriter out = resp.getWriter();
	    out.print(productTime);
	    out.flush();
	    out.close();
   }
	
}
