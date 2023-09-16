package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.ProductReservation;
import com.min.edu.vo.Reservation;

public class ReserveDaoImpl implements IReserveDao {

	private Logger logger = LoggerFactory.getLogger(DetailDaoImpl.class);
	private final String NS = "com.min.edu.model.ReserveDaoImpl.";
	private SqlSessionFactory manager;
	
	public ReserveDaoImpl() {
		manager = SqlSessionFactoryManager.getFactory();
	}
	
	@Override
	public List<ProductReservation> getShopDetail(Map<String, Object> map) {
		logger.info("DAO 실행 - 업체 정보 가져오기, 전달 받은 값 : {}", map);
		SqlSession session = manager.openSession();
		List<ProductReservation> lists = session.selectList(NS+"getShopDetail",map);
		return lists;
	}

	@Override
	public List<String> getShopCalendar(Map<String, Object> map) {
		logger.info("DAO 실행 - 업체의 휴무일 조회, 전달 받은 값 : {}", map);
		SqlSession session = manager.openSession();
		List<String> lists = session.selectList(NS+"getShopCalendar",map);
		return lists;
	}

	@Override
	public List<String> getProductTime(Map<String, Object> map) {
		logger.info("DAO 실행 - 업체 예약 가능한 시간 조회, 전달 받은 값 : {}", map);
		SqlSession session = manager.openSession();
		List<String> lists = session.selectList(NS+"getProductTime",map);
		return lists;
	}

	@Override
	public int setReservationInfo(Reservation vo) {
		logger.info("DAO 실행 - 예약 등록하기, 전달 받은 값 : {}", vo);
		SqlSession session = manager.openSession();
		int n =0;
		if(vo != null) {
			n = session.insert(NS+"setReservationInfo",vo);
			session.commit();
			session.close();
		}else {
			n=0;
		}
		return n;
	}

	@Override
	public boolean getReservationStatus(Map<String, Object> map) {
		logger.info("DAO 실행 - 중복 방지를 위한 조회, 전달 받은 값 : {}", map);
		SqlSession session = manager.openSession();
		Reservation dto = session.selectOne(NS+"getReservationStatus",map);
		return (dto != null)?true:false;
	}

	@Override
	public int getReservationCount(Map<String, Object> map) {
		logger.info("DAO 실행 - 선택 시간의 예약 갯수 조회, 전달 받은 값 : {}", map);
		SqlSession session = manager.openSession();
		int reserves = session.selectOne(NS+"getReservationCount",map);
		return reserves;
	}

	@Override
	public ProductReservation getReservationList(int productId) {
		logger.info("DAO 실행 - 업체 소개 , 전달 받은 값 : {}", productId);
		SqlSession session = manager.openSession();
		ProductReservation dto = session.selectOne(NS+"getReservationList",productId);
		return dto;
	}

}