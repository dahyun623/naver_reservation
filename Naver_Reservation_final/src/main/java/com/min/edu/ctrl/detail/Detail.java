package com.min.edu.ctrl.detail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.model.DetailDaoImpl;
import com.min.edu.model.IDetailDao;
import com.min.edu.model.IReviewDao;
import com.min.edu.model.ReviewDaoImpl;
import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public class Detail extends HttpServlet {

	private static final long serialVersionUID = 3520908187435430286L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		IDetailDao dDao = new DetailDaoImpl();
		IReviewDao rDao = new ReviewDaoImpl();
		Review score = rDao.getReviewScore(productId);
		List<Review> reviews = rDao.getReview(productId);
		List<Product> lists = dDao.getProductImg(productId);
		req.setAttribute("score", score);
		req.setAttribute("lists", lists);
		req.setAttribute("reviews", reviews);
		req.getRequestDispatcher("/WEB-INF/views/productDetail.jsp").forward(req, resp);
	}
}
