package com.min.edu.vo;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Product {
	private int productId;
	private String productName;
	private String pCategoryId;
	private String address;
	private String phone;
	private String openHour;
	private String etc1;
	private String etc2;
	private String description;
	private String lat;
	private String lng;
	
	private List<Review> review;
	private List<FileInfo> fileInfo;
}
