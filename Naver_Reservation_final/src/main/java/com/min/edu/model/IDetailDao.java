package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Product;
import com.min.edu.vo.Review;

public interface IDetailDao {
	
	//업체 이미지와 정보를 불러옴
	public List<Product> getProductImg(String productId);


}
