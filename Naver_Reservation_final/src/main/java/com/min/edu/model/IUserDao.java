package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Reservation;

public interface IUserDao {
	//예약 현황 가져오기
	public Map<String, Object> getMyReservationStatus(String email);
	//예약 리스트 가져오기
	public List<Reservation> getMyReservationList(String email);
	//취소 했을 경우
	public int getMyReservationCancle(String date);
	// 매일 00시00분 이용완료로 바꾸기
	public int autoReservationStatus();
}
