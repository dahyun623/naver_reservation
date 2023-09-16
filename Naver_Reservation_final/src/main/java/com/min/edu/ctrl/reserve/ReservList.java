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
import com.min.edu.vo.ProductReservation;

public class ReservList extends HttpServlet {

	private static final long serialVersionUID = -3134589015511363289L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8;");
		
		int productId = Integer.parseInt(req.getParameter("productId"));
		
		IReserveDao dao = new ReserveDaoImpl();
		ProductReservation dto = dao.getReservationList(productId);
		req.setAttribute("dto", dto);
		
		req.getRequestDispatcher("/WEB-INF/views/reserveList.jsp").forward(req, resp);
	}
}