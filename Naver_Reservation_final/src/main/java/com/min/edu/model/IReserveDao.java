package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.ProductReservation;
import com.min.edu.vo.Reservation;

public interface IReserveDao {

	/**
	 * 업체 이름, 업체 이미지 조회
	 * @param map 업체ID & 예약상품시퀀스
	 * @return 업체 이름과 이미지를 담은 DTO
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public List<ProductReservation> getShopDetail(Map<String, Object> map);
	
	/**
	 * 달력에 표기하기 위해, 업체의 휴무일 조회
	 * @param map 업체ID & 예약상품시퀀스
	 * @return 업체의 휴무일을 담은 String 리스트
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public List<String> getShopCalendar(Map<String, Object> map);
	
	/**
	 * 업체 예약을 위해, 예약가능한 시간을 조회
	 * @param map 업체ID & 예약상품시퀀스
	 * @return 업체의 예약 가능 시간을 담은 String 리스트
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public List<String> getProductTime(Map<String, Object> map);
	
	/**
	 * 예약 등록
	 * @param vo
	 * @return 등록한 예약 갯수(성공시 1)
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public int setReservationInfo(Reservation vo);
	
	/**
	 * 중복예약 방지를 위한 조회
	 * @param map 선택날짜, 시간, 업체ID
	 * @return true 중복예약 있음,  false 중복예약 없음
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public boolean getReservationStatus(Map<String, Object> map);
	
	/**
	 * 예약시간 선택시 해당 시간에 예약이 몇개 있는지 조회
	 * @param map 업체ID, 예약가능시간
	 * @return 해당시간의 예약 갯수 int
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public int getReservationCount(Map<String, Object> map);
	
	/**
	 * 업체의 정보 (메뉴 소개 등) 출력
	 * @param productId
	 * @return 업체의 정보를 담은 DTO
	 * @author 김기훈
	 * @since 23.07.27
	 */
	public ProductReservation getReservationList(int productId);
	
}