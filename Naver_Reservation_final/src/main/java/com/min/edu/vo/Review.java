package com.min.edu.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Review {
	private String revId;
	private int productId;
	private int score;
	private double reviewavg;
	private String emailId;
	private String content;
	private String regdate;
	private String gubun;
	private String reservno;
	private String productName;
	
	private List<FileInfo> fileInfo;
	
	public Review(String revId, int productId, int score, String emailId, String content, String regdate,
			String gubun) {
		this.revId = revId;
		this.productId = productId;
		this.score = score;
		this.emailId = emailId;
		this.content = content;
		this.regdate = regdate;
		this.gubun = gubun;
	}

	public Review(int productId, String emailId, String content, int score) {
		super();
		this.productId = productId;
		this.emailId = emailId;
		this.content = content;
		this.score = score;
	}
	
	
}
