package com.min.edu.jeong;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.min.edu.model.IUserDao;
import com.min.edu.model.UserDaoImpl;

public class MainJunitTestJeong {

	@Test
	public void test() {
		IUserDao dao = new UserDaoImpl();
//		String email = "ugogo@gmail.com";
//		//getMyReservationStatus
//		Map<String, Object> map = dao.getMyReservationStatus(email);
//		assertNotNull(map);
//		JSONObject json = JSONObject.fromObject(map);
//		System.out.println(json.toString());
//		//
//		List<Reservation> lists = (List<Reservation>) dao.getMyReservationList(email);
//		Map<String,List<Reservation>> RMap = new HashMap<String, List<Reservation>>();
//		List<Reservation> Ulist = new ArrayList<Reservation>();
//		List<Reservation> Clist = new ArrayList<Reservation>();
//		List<Reservation> Rlist = new ArrayList<Reservation>();
//		List<Reservation> Alist = new ArrayList<Reservation>();
//		
//		for (int i = 0; i < lists.size(); i++) {
//			switch (lists.get(i).getStatus()) {
//			case "U":
//				Ulist.add(lists.get(i));
//				break;
//			case "C":
//				Clist.add(lists.get(i));
//				break;
//			case "R":
//				Rlist.add(lists.get(i));
//				break;
//			case "A":
//				Alist.add(lists.get(i));
//				break;
//			}
//		}
//		RMap.put("U", Ulist);
//		RMap.put("C", Clist);
//		RMap.put("R", Rlist);
//		RMap.put("A", Alist);
//		
//		System.out.println(RMap);
//		JSONObject Rjson = JSONObject.fromObject(RMap);
//		System.out.println(Rjson.toString());
//		
//		String [] sta = {"U","R","A","C"};
//		for (int i = 0; i < sta.length; i++) {
//			System.out.println(RMap.get(sta[i].trim()));
//		}
//		assertNotNull(lists);
        String date = "No.20230221000001".replace("No.", "");

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        int hour = Integer.parseInt(date.substring(8, 10));
        int minute = Integer.parseInt(date.substring(10, 12));
        int second = Integer.parseInt(date.substring(12, 14));
        Date dateObj = new Date(year - 1900, month - 1, day, hour, minute, second);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(dateObj);
        
        int n = dao.getMyReservationCancle(formattedDate);
        assertEquals(n, 1);

	}
}
