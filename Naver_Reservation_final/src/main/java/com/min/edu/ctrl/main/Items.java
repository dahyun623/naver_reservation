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
import com.min.edu.vo.Category;

import net.sf.json.JSONArray;

public class Items extends HttpServlet {

	private static final long serialVersionUID = 7490780489191621244L;
	private Logger logger = LoggerFactory.getLogger(Items.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		logger.info(">>>>>>>>>> Items doGet");
		
		IMainDao dao = new MainDaoImpl();
		List<Category> cList = dao.getAllCategory();
		
		JSONArray obj = JSONArray.fromObject(cList);
		resp.getWriter().print(obj);
	}
}
