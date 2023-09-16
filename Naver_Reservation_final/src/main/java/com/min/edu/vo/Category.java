package com.min.edu.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Category {
	private String pCategoryId;
	private String categoryName;
	private int count;
}
