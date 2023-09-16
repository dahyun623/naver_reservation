package com.min.edu.ctrl.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.model.IMainDao;
import com.min.edu.model.MainDaoImpl;
import com.min.edu.vo.Event;

import net.sf.json.JSONArray;

public class Promotion extends HttpServlet {

	private static final long serialVersionUID = -7181561793364403370L;
	private Logger logger = LoggerFactory.getLogger(Items.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		logger.info(">>>>>>>>>> Promotion doGet");
		
		IMainDao dao = new MainDaoImpl();
		List<Event> eList = dao.getAllEvent();
		
		JSONArray obj = JSONArray.fromObject(eList);
		resp.getWriter().print(obj);
	}
}
