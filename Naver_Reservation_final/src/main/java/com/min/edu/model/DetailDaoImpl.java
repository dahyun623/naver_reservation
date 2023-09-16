package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public class DetailDaoImpl implements IDetailDao {
	
	private Logger logger = LoggerFactory.getLogger(DetailDaoImpl.class);
	private final String NS = "com.min.edu.model.DetailDaoImpl.";
	private SqlSessionFactory manager;
	
	public DetailDaoImpl() {
		manager = SqlSessionFactoryManager.getFactory();
	}

	@Override
	public List<Product> getProductImg(String productId) {
		logger.info("업체 상세 조회");
		SqlSession session = manager.openSession();
		return session.selectList(NS+"getProductImg",productId);
	}


}
