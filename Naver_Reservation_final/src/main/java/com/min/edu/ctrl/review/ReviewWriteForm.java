package com.min.edu.ctrl.review;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.IReviewDao;
import com.min.edu.model.ReviewDaoImpl;
import com.min.edu.vo.FileInfo;
import com.min.edu.vo.Review;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewWriteForm extends HttpServlet {

	private static final long serialVersionUID = 8318568853473330514L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
	    String saveDirectory = context.getRealPath("upload");
//		String saveDirectory = "C:\\Users\\GDJ58\\git\\naver_reservation_real\\Naver_Reservation_final\\src\\main\\webapp\\upload";
	    
	    int maxPostSize = 10 * 1024 * 1024;
	    String encoding = "UTF-8";
	    
	    MultipartRequest multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		
	    String reservno = multi.getParameter("reservno");
		String productId = multi.getParameter("productId");
		String emailId = multi.getParameter("emailId");
		String content = multi.getParameter("content");
		String score = multi.getParameter("score");
		System.out.printf("reservno: %s, emailId: %s, content: %s, score %s", productId, emailId, content, score);
		Review rVo = new Review(Integer.parseInt(productId), emailId, content, Integer.parseInt(score));
		
		IReviewDao dao = new ReviewDaoImpl();
		
		String saveFileName = null;
	    FileInfo fvo = null;
		List<FileInfo> fVos = new ArrayList<FileInfo>();
		for(int i=0; i<3; i++) {
			String files = "filename"+i;
			String fileName = multi.getOriginalFileName(files);
			
			if(fileName != null) {
				saveFileName = UUID.randomUUID().toString().replace("-", "")+fileName.substring(0,fileName.lastIndexOf("."));
				
				fvo = new FileInfo();
				fvo.setFileName(fileName);
				fvo.setSaveFileName(saveFileName);
				System.out.println("첨부파일명: "+fileName);
				fVos.add(fvo);
				
				String systemFileName = multi.getFilesystemName(files);
				File oldFile = new File(saveDirectory+"/"+systemFileName);
				File newFile = new File(saveDirectory+"/"+saveFileName);
				oldFile.renameTo(newFile);
				System.out.println("newFile:"+newFile);
				System.out.println("oldFile:"+oldFile);
			}

		}
		
		int n = dao.setReview(rVo,fVos, reservno);
		
		if(n>0) {
			resp.sendRedirect("./myReservation.do");
		}
	}

}
