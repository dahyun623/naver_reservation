package com.min.edu.ctrl.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.min.edu.vo.Reservation;

import net.sf.json.JSONObject;

public class MyReservationList extends HttpServlet {

	private static final long serialVersionUID = -8081320537255651258L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("reservationEmail");
		IUserDao dao = new UserDaoImpl();
		List<Reservation> lists = dao.getMyReservationList(email);
		Map<String,List<Reservation>> RMap = new HashMap<String, List<Reservation>>();
		List<Reservation> Ulist = new ArrayList<Reservation>();
		List<Reservation> Clist = new ArrayList<Reservation>();
		List<Reservation> Rlist = new ArrayList<Reservation>();
		List<Reservation> Alist = new ArrayList<Reservation>();
		for (int i = 0; i < lists.size(); i++) {
			switch (lists.get(i).getStatus()) {
			case "U":
				Ulist.add(lists.get(i));
				break;
			case "C":
				Clist.add(lists.get(i));
				break;
			case "R":
				Rlist.add(lists.get(i));
				break;
			case "A":
				Alist.add(lists.get(i));
				break;
			}
		}
		RMap.put("U", Ulist);
		RMap.put("C", Clist);
		RMap.put("R", Rlist);
		RMap.put("A", Alist);
		JSONObject rlist = JSONObject.fromObject(RMap);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		System.out.println(rlist.toString());
		resp.getWriter().println(rlist.toString());
	}
}
