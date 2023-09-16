package com.min.edu.ctrl.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;


public class autoReservationStatus extends HttpServlet {

    private static final long serialVersionUID = 4285082199678319483L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    IUserDao dao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("autoReservationStatus 실행");
        int n =dao.autoReservationStatus();

        resp.setContentType("text/plain");
        resp.getWriter().write(n);
    }
}

