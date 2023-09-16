package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.FileInfo;
import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public class ReviewDaoImpl implements IReviewDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.min.edu.model.ReviewDaoImpl.";
	private SqlSessionFactory manager = SqlSessionFactoryManager.getFactory();
	
	
	@Override
	public Review getReviewScore(String productId) {
		logger.info("getReviewScore 리뷰 별점 가져오기");
		SqlSession session = manager.openSession();
		return session.selectOne(NS+"getReviewScore", productId);
	}

	@Override
	public List<Review> getReview(String productId) {
		logger.info("getReview 리뷰 5개까지 가져오기");
		SqlSession session = manager.openSession();
		return session.selectList(NS+"getReview", productId);
	}

	@Override
	public List<Review> getReviewMore(Map<String, Object> map) {
		logger.info("getReviewMore 리뷰 5개 더 가져오기");
		SqlSession session = manager.openSession();
		return session.selectList(NS+"getReviewMore", map);
	}

	@Override
	public int setReview(Review rVo, List<FileInfo> fVos, String reservno) {
		logger.info("setReview 작성 리뷰 저장");
		int cnt = 0;
		SqlSession session = manager.openSession();
		try {
			cnt += session.insert(NS+"setReview", rVo);
			if(fVos != null) {
				for (FileInfo fVo : fVos) {
					cnt += session.insert(NS+"setReviewFile", fVo);
					cnt += session.insert(NS+"setReviewFileConn");
				}
			}
			session.update(NS+"setReviewStatus", reservno);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

//	@Override
//	public boolean setReviewFile(List<FileInfo> fVos) {
//		logger.info("setReviewFile 첨부파일 저장");
//		SqlSession session = manager.openSession();
//		int cnt = 0;
//		try {
//			for (FileInfo fVo : fVos) {
//				cnt += session.insert(NS+"setReviewFile", fVo);
//				cnt += session.insert(NS+"setReviewFileConn");
//			}
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		
//		return cnt>0?true:false;
//	}

}
