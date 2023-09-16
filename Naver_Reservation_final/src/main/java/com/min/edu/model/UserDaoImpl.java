package com.min.edu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.Reservation;


public class UserDaoImpl implements IUserDao{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.UserDaoImpl.";
	
	@Override
	public Map<String, Object> getMyReservationStatus(String email) {
		SqlSession session = manager.openSession();
		Map<String,Object> map = session.selectOne(NS+"getMyReservationStatus",email);
		return map;
	}

	@Override
	public List<Reservation> getMyReservationList(String email) {
		SqlSession session = manager.openSession();
		List<Reservation> lists = session.selectList(NS+"getMyReservationList",email);
		return lists;
	}

	@Override
	public int getMyReservationCancle(String date) {
		SqlSession session = manager.openSession(true);
		int n = session.update(NS+"getMyReservationCancle",date);
		return n;
	}

	@Override
	public int autoReservationStatus() {
		SqlSession session = manager.openSession(true);
		int n = session.update(NS+"autoReservationStatus");
		return n;
	}
}
