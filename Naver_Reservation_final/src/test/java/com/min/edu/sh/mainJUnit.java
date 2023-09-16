package com.min.edu.sh;

import org.junit.Test;

import com.min.edu.model.IMainDao;
import com.min.edu.model.MainDaoImpl;

public class mainJUnit {

	@Test
	public void test() {
		IMainDao dao = new MainDaoImpl();
		
		// getAllEvent()
//		List<Event> eList = dao.getAllEvent();
//		assertNotNull(eList);
		
		// getAllCategory()
//		List<Category> cList = dao.getAllCategory();
//		assertNotNull(cList);
		
		// getAllProduct()
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("start",5);
//		map1.put("end",6);		
//		List<Product> pList1 = dao.getAllProduct(map1);
//		assertNotNull(pList1);
		
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("start",1);
//		map2.put("end",2);
//		map2.put("pCategoryId", "P001");
//		List<Product> pList2 = dao.getAllProduct(map2);
//		assertNotNull(pList2);
	}

}
