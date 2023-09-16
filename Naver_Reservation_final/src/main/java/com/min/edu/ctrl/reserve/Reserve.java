package com.min.edu.ctrl.reserve;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReserveDao;
import com.min.edu.model.ReserveDaoImpl;
import com.min.edu.vo.ProductReservation;

public class Reserve extends HttpServlet {

	private static final long serialVersionUID = -6357335294239840364L;

	//예약 페이지 로드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");
		
		int productId = Integer.parseInt(req.getParameter("productId"));
		int shopNo = Integer.parseInt(req.getParameter("shopNo"));
		
		IReserveDao dao = new ReserveDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId",productId);
		map.put("shopNo",shopNo);
		List<ProductReservation> lists = dao.getShopDetail(map);
		req.setAttribute("lists", lists);
		req.setAttribute("productId", productId);
		req.setAttribute("shopNo", shopNo);
		
		req.getRequestDispatcher("/WEB-INF/views/reserve.jsp").forward(req, resp);
	}
}