package com.min.edu.ctrl.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.model.IMainDao;
import com.min.edu.model.MainDaoImpl;
import com.min.edu.vo.Product;

import net.sf.json.JSONArray;

public class ProductItem extends HttpServlet {

	private static final long serialVersionUID = 8883447739549692675L;
	private Logger logger = LoggerFactory.getLogger(ProductItem.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String pCategoryId = req.getParameter("pCategoryId");
		String start = req.getParameter("start");
		logger.info(">>>>>>>>>> ProductItem doGet : {}, {}", pCategoryId, start);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pCategoryId", pCategoryId);
		map.put("start", start);
		map.put("end", Integer.parseInt(start)+1);
		IMainDao dao = new MainDaoImpl();
		List<Product> pList = dao.getAllProduct(map);
		
		JSONArray obj = JSONArray.fromObject(pList);
		resp.getWriter().print(obj);
	}

}
