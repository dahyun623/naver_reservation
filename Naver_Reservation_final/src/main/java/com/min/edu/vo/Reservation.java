package com.min.edu.vo;

import java.util.List;

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
public class Reservation {
	private int reservNo;
	private int shopNo;
	private String day;
	private String time;
	private String reservationDate;
	private String reservEmail;
	private String reservName;
	private String reservPhone;
	private String personCount;
	private String requestContent;
	private String status;
	private String reviewStatus;	
	
	private List<Product> productDTO;
}
