package com.min.edu.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Event {
	private int productId;
	private String title;
	private String content;
	private String eventImg;
}
