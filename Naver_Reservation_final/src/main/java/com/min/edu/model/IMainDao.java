package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.Category;
import com.min.edu.vo.Event;
import com.min.edu.vo.Product;

public interface IMainDao {

	/**
	 * REV_DB_01001	업체 중 이벤트 정보를 가지고 있는 리스트 조회
	 * @return productId, title, content, eventImg
	 */
	public List<Event> getAllEvent();
	
	/**
	 * REV_DB_01002	카테고리별 전체 업체 개수와 전체 업체 개수 조회
	 * @return pCategoryId, CategoryName, count
	 */
	public List<Category> getAllCategory();
	
	/**
	 * REV_DB_01003	카테고리별 또는 전체 업체 정보 조회
	 * @param map [pCategoryId], start, end
	 * @return productId, pCategoryId, productName, description, saveFileName
	 */
	public List<Product> getAllProduct(Map<String, Object> map);

}
