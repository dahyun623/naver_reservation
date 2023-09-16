package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.Category;
import com.min.edu.vo.Event;
import com.min.edu.vo.Product;

public class MainDaoImpl implements IMainDao {
	
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	private Logger logger = LoggerFactory.getLogger(MainDaoImpl.class);
	private final String NS = "com.min.edu.model.MainDaoImpl.";

	@Override
	public List<Event> getAllEvent() {
		SqlSession session = manager.openSession();
		List<Event> list = session.selectList(NS+"getAllEvent");
		return list;
	}

	@Override
	public List<Category> getAllCategory() {
		SqlSession session = manager.openSession();
		List<Category> list = session.selectList(NS+"getAllCategory");
		return list;
	}

	@Override
	public List<Product> getAllProduct(Map<String, Object> map) {
		SqlSession session = manager.openSession();
		List<Product> list = session.selectList(NS+"getAllProduct",map);
		return list;
	}
	
}
