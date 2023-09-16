SELECT NVL(SUM(DECODE(STATUS, 'U', 1)),0) as U, NVL(SUM(DECODE(STATUS, 'R', 1)),0) as R,
		NVL(SUM(DECODE(STATUS, 'A', 1)),0) as A, NVL(SUM(DECODE(STATUS, 'C', 1)),0) as C
	FROM RESERVATION r
	WHERE RESERVEMAIL = 'WJDDNS7828@gmail.com';
	
SELECT RESERVNAME,"DAY" ,"TIME",PERSONCOUNT ,REQUESTCONTENT,STATUS,
		CASE STATUS WHEN 'U' THEN'1' WHEN 'R' THEN '2' WHEN 'C' THEN '3' WHEN 'A' THEN '4' END AS sort,
		TO_CHAR(RESERVATIONDATE,'YYYYMMDDHH24MISS') AS RESERVATIONDATE
		,RESERVNO ,REVIEW_STATUS,
		p.ADDRESS ,p.PRODUCT_NAME
			FROM RESERVATION r, PRODUCT_RESERVATION pr ,PRODUCT p
				WHERE r.SHOP_NO  = pr.SHOP_NO 
				AND pr.PRODUCT_ID =p.PRODUCT_ID 
				AND r.RESERVEMAIL = 'ugogo@gmail.com'
				ORDER BY SORT ASC;

UPDATE RESERVATION SET STATUS = 'A'
	WHERE RESERVATIONDATE = '11';