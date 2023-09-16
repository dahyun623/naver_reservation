package com.min.edu.ctrl.reserve;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReserveDao;
import com.min.edu.model.ReserveDaoImpl;

public class ShopCalendar extends HttpServlet {

	private static final long serialVersionUID = 8567072881285509298L;

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
        List<String> closedDates = dao.getShopCalendar(map);
        List<Integer> closedDateInt = convertDay(closedDates);
        
        
        
        // JSON 형태로 휴무일 정보 전송
        PrintWriter out = resp.getWriter();
        out.print(closedDateInt);
        out.flush();
        out.close();
    }
	
	private static List<Integer> convertDay(List<String> closedDates) {
        HashMap<String, Integer> dayToNumberMap = new HashMap<>();
        dayToNumberMap.put("일", 0);
        dayToNumberMap.put("월", 1);
        dayToNumberMap.put("화", 2);
        dayToNumberMap.put("수", 3);
        dayToNumberMap.put("목", 4);
        dayToNumberMap.put("금", 5);
        dayToNumberMap.put("토", 6);

        // 리스트 요소를 매핑하여 새로운 리스트 생성
        return closedDates.stream()
                .map(day -> dayToNumberMap.getOrDefault(day, -1))
                .filter(dayNumber -> dayNumber != -1)
                .sorted()
                .collect(Collectors.toList());
    }
	
}