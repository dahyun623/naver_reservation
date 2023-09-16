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
public class ProductReservation {
	private String reservationName;
	private String reservationContent;
	private int shopNo;
	private int productId;
	private String closedays; // closedays
	private String ableTime; // able_times
	private String abletimeCount; // able_times

	private List<FileInfo> fileInfo;
}
