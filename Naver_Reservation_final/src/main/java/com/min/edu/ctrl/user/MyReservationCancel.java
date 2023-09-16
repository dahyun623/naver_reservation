package com.min.edu.ctrl.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;

public class MyReservationCancel extends HttpServlet {

	private static final long serialVersionUID = 56567350148955769L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	IUserDao dao = new UserDaoImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String noDate = req.getParameter("reservationDate").replace("No.", "");;
		logger.info("받아온 값 : {} ", noDate);

		int year = Integer.parseInt(noDate.substring(0, 4));
		int month = Integer.parseInt(noDate.substring(4, 6));
		int day = Integer.parseInt(noDate.substring(6, 8));
		int hour = Integer.parseInt(noDate.substring(8, 10));
		int minute = Integer.parseInt(noDate.substring(10, 12));
		int second = Integer.parseInt(noDate.substring(12, 14));
		Date dateObj = new Date(year - 1900, month - 1, day, hour, minute, second);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(dateObj);

		dao.getMyReservationCancle(formattedDate);
	}
}
