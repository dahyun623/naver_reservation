package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.FileInfo;
import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public interface IReviewDao {

	public Review getReviewScore(String productId);
	
	public List<Review> getReview(String productId);
	
	public List<Review> getReviewMore(Map<String, Object> map);
	
	public int setReview(Review rVo, List<FileInfo> fVos, String reservno);
	
//	public boolean setReviewFile(List<FileInfo> fVos);
	
}
