package com.kh.semiproject.free.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semiproject.board.model.vo.BoardEH;
import com.kh.semiproject.board.model.vo.Img;
import com.kh.semiproject.board.model.vo.M_Comment;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.MyFileRenamePolicy;
import com.kh.semiproject.free.model.service.FreeService;
import com.kh.semiproject.free.model.vo.Free;
import com.kh.semiproject.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;




@WebServlet("/free/*")
public class FreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public FreeController() {
        super();
     
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/free").length()); 

		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		FreeService freeService = new FreeService();
		
		
		
		if(command.equals("/list")) {
			
			try {
				
				
			// 현재 게시글 전체 수
			int listCount = freeService.getListCount();
					
			int limit = 5; // 한 페이지에 보여질 게시글의 수 
			int pagingBarSize = 5;// 보여질 페이징바의 페이지 개수
				
			int currentPage = 0;// 현재 페이지 번호를 표시할 변수
			int maxPage = 0;// 전체 페이지의 수(== 마지막 페이지)
			int startPage = 0;// 페이징바 시작페이지 번호
			int endPage = 0;// 페이징바 끝 페이지 번호 
			
			// currentPage - 현재 페이지 번호를 표시할 변수
			
			if(request.getParameter("currentPage") == null) {
				currentPage = 1;
			}else {
				// 전달받은 값이 있을 경우 해당 번호를 저장
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			// maxPage - 총 페이지 수(== 마지막 페이지)
			maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
			
			// startPage = 페이징바의 시작 페이지 번호
			startPage = (currentPage-1) / pagingBarSize * pagingBarSize + 1;
			
			// endPage = 페이징바의 끝 페이지 번호
			endPage = startPage + pagingBarSize - 1;
			if(maxPage <= endPage) {
				endPage = maxPage;
			}
			
			
			PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
			
			System.out.println(pInf);
			
			
			List<BoardEH> blist = freeService.selectList(currentPage, limit);	
			
			List<Free> flist = freeService.selectfList(currentPage, limit);
			
			System.out.println("flist" + flist);
			System.out.println("blist" + blist);
			
			
			path = "/WEB-INF/views/free/freeList.jsp";
			request.setAttribute("blist", blist);
			request.setAttribute("pInf", pInf);
			request.setAttribute("flist", flist);
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
			}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 목록 조회", e);
		
			}
		}
		
		  //  상세조회 Controller
		 else if(command.equals("/view")) {
			 
	    	  int no = Integer.parseInt(request.getParameter("no"));
	    	  
	    	  try {
	    		  BoardEH board = FreeService.selectFree(no); 
	    		  
	    		  if(board != null) { // 상세조회 성공
	    			  
	    			  
	    			  Free free = FreeService.selectFree2(no);
	    			  
	    			  if(free != null) {
	    				  request.setAttribute("free", free);
	    			  }
	    			  
	    			  List<Img> img = FreeService.selectImg(no);
	    			  
	    			  if(!img.isEmpty()){
							request.setAttribute("img", img);
						}
	    			  
	    			  path = "/WEB-INF/views/free/freeView.jsp";
	    			  request.setAttribute("board", board);
	    			  request.setAttribute("iList", img);
	    			  view = request.getRequestDispatcher(path);
	    			  view.forward(request, response);
	    			  
	    			  System.out.println("img = " + img);
	    			  
	    		  }else {
	    			  request.getSession().setAttribute("msg","공지사항 상세조회 실패");
	    			  response.sendRedirect("list");
	    		  }
	    	  
	    	  }catch(Exception e) {
	    		  ExceptionForward.errorPage(request, response, "공지사항 상세 조회", e);
	    	  }
	      }
		
		
		 //  등록 화면 Controller
		else if(command.equals("/writeForm")) {
			path = "/WEB-INF/views/free/freeWrite.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		
		 //  등록 Controller
	      else if(command.equals("/write")) {
	    	  
	    	 
	    	  
	    	  try {
	    		  if(ServletFileUpload.isMultipartContent(request)) {
	    		  
	    			  int maxSize = 10 * 1024 * 1024;
	    			  
	    			  String root 
	    				= request.getSession().getServletContext().getRealPath("/");
	    	  
	    			  String savePath = root + "resources/uploadImages/";
	    			  
	    			  MultipartRequest multiRequest = 
	    						new MultipartRequest
	    						(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	    			  
	    			  
	    			  ArrayList<String> saveFiles = new ArrayList<String>();
	    			  ArrayList<String> originFiles = new ArrayList<String>();
	    			  
	    			  Enumeration<String> files = multiRequest.getFileNames();
	    			  
	    			  
	    			  while(files.hasMoreElements()) {
	    					// 다음 요소가 더 있는가
	    					
	    					// 업로드 된 파일은 역순으로 전달됨. 0번 index에 제일 마지막 파일 들어감
	    					String name = files.nextElement();
	    					
	    					if(multiRequest.getFilesystemName(name) != null) {
	    						// getFilesystemName(key) : rename 된 파일명 얻어오기
	    						saveFiles.add(multiRequest.getFilesystemName(name));
	    						// 새로운것 저장 하여라
	    						originFiles.add(multiRequest.getOriginalFileName(name));
	    						// 원본저장 하여라
	    					}
	    				}
	    			  
	    			  
	    			  String boardTitle =  multiRequest.getParameter("title");
	    		      String boardContent = multiRequest.getParameter("content");
	    			  String boardUrl = multiRequest.getParameter("url");
	    			  
	    			  BoardEH board = new BoardEH(boardTitle, boardContent, boardUrl);
	    			  
	    			  Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	    			  String memberId = loginMember.getMemberId();

	    			  String freeCategory = multiRequest.getParameter("category");
	    			  
	    			  Free free = new Free(freeCategory);
	    			  
	    			 
	    			  ArrayList<Img> fList = new ArrayList<Img>();
	    			  
	    			  
	    			  for(int i = originFiles.size()-1; i>=0 ; i--) {
	    					Img file = new Img();
	    					file.setImgPath(savePath);
	    					file.setImgOriginName(originFiles.get(i));
	    					file.setImgChangeName(saveFiles.get(i));
	    					
	    					// 썸네일 이미지는 fileLevel 0 으로 
	    					// 나머지 이미지에는 fileLevel 1 부여
	    					if( (i==originFiles.size()-1)  // 제일 마지막 index
	    							&& multiRequest.getFilesystemName("img1") != null) {
	    						       // 파일넣고 rename 시켰을때 있으면
	    						file.setImgLevel(0);   // 0으로 하고  (썸네일)
	    					}else {
	    						file.setImgLevel(1);  // 나머지는 1로 한다
	    					}
	    					
	    					fList.add(file);
	    				}
	    			  
	    				int result = FreeService.insertFree(board, memberId, fList,free);
	    				
	    				
	    				if(result > 0) msg = "게시글 등록 성공";
	    				else           msg = "게시글 등록 실패";	
	    				
	    				request.getSession().setAttribute("msg", msg);
	    				response.sendRedirect("list");
	    			  
	    		  }
	  			
	    	
	    	  }catch(Exception e) {
	    		  ExceptionForward.errorPage(request, response, "공지사항 등록", e);
	    	  }
	    	  
	      }
	      
		
		
		 // 공지사항 검색용 Controller
	      
	      else if(command.equals("/search")) {
	    	  
	    	  String searchKey = request.getParameter("searchKey");
	    	  String searchValue = request.getParameter("searchValue");
	    	  
	            System.out.println("선택 옵션 : " + searchKey);
	            System.out.println("검색 내용 : " + searchValue);
	    	  
	    	  try {
	    		  
	    		  List<BoardEH>blist = FreeService.searchFree(searchKey,searchValue);
	    		  PageInfo pInf = FreeService.searchPinf(searchKey,searchValue);
	    		  
	    		  path = "/WEB-INF/views/free/freeList.jsp";
	    		  request.setAttribute("blist", blist);
	    		  request.setAttribute("pInf", pInf);
	    		  view = request.getRequestDispatcher(path);
	    		  view.forward(request, response);
	    		  
	    		  
	    	  }catch(Exception e) {
	    		  ExceptionForward.errorPage(request, response, "공지사항 검색", e);
	    	  }
	    	  
	      }
		
		
		// 댓글 등록용 Controller
			else if(command.equals("/insertComment")) {
				
				String memId = request.getParameter("writer");
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String commContent = request.getParameter("content");
				
				M_Comment comm = new M_Comment(commContent, boardNo);
				
				try {
					
					int result = FreeService.insertComm(comm,memId);
					
					response.getWriter().print(result);
					// 문자를 내보내는 스트림
					// DB에 있는 result를 내보낸다
					
					
				}catch(Exception e) {
					ExceptionForward.errorPage(request, response, "댓글 등록", e);
				}
				
			} 
				
		
		
		// 댓글 리스트 출력용 controller
			else if(command.equals("/selectCommentList")) {
				
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				
				try {
					
					List<M_Comment> rList = FreeService.selectCommList(boardNo);
					
					response.setCharacterEncoding("UTF-8");
					
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
					
					gson.toJson(rList, response.getWriter());
					
				}catch(Exception e) {
					ExceptionForward.errorPage(request, response, "댓글 조회", e);
					
				}
		
				
			}
		
		
		  // 공지사항 삭제 controller
	      
		      else if(command.equals("/delete")) {
		    	
		    	  int no = Integer.parseInt(request.getParameter("no"));
		    	  
		    	  try {
		    		  int result = FreeService.deleteFree(no);
		    		  
		    		  if(result > 0) { 
		    			  msg = "공지사항이 삭제되었습니다";
		    			  path = "list";
		    		 }else{
		    			 msg = "공지사항 삭제 실패";
		    			 path = "detail?no="+no;
		    		 }
		    		  
		    		  request.getSession().setAttribute("msg", msg);
		    		  response.sendRedirect(path);
		    		  
		    	  }catch(Exception e) {
		    		  ExceptionForward.errorPage(request, response, "공지사항 삭제", e);
		    	  }
		    	  
		      }
		
		
		   // 공지사항 수정화면 Controller
		      else if(command.equals("/updateForm")) {
		    	  
		    	  // DB에서 해당 글을 조회하여 화면으로 전달
		    	  int no = Integer.parseInt(request.getParameter("no"));
		    	  
		    	  try {
		    		  	BoardEH board = FreeService.updateForm(no);
		    		  	
		    		  	if(board != null) {
		    		  		
		    		  		List<Img> img = freeService.selectImg(no);
		    		  		
		    		  		Free free  = freeService.selectFree2(no);
		    		  		
		    		  		if(!img.isEmpty()) {
		    		  			request.setAttribute("iList", img);
		    		  		}
		    		  		
		    		  		request.setAttribute("board", board);
		    		  		request.setAttribute("free", free);
		    		  		
		    		  		path = "/WEB-INF/views/free/freeUpdate.jsp";
		    		  		view = request.getRequestDispatcher(path);
		    		  		view.forward(request, response);
		    		  	}else {
		    		  		request.getSession().setAttribute("msg", "공지사항 수정 화면 출력 실패");
		    		  	}
		    		  	
		    	  } catch (Exception e) {
		    		  	ExceptionForward.errorPage(request, response, "공지사항 수정 화면으로 전환하는", e);
				}
		    	  
		      }
		
		
		
		
		
		// 공지사항 수정 controller
		      else if(command.equals("/update")) {
		    	  
		    	  try {
		    		 
		    		  
		    		  int maxSize = 10 * 1024 * 1024;
		    		  
		    		  String root = request.getSession().getServletContext().getRealPath("/");
		    		  
		    		  String savePath =  root + "resources/uploadImages/";
		    		  
		    		  MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		    		  
		    		  int boardNo = Integer.parseInt(multiRequest.getParameter("no"));
						
		    		  
							
							
							
							String boardTitle =  multiRequest.getParameter("title");
			    		    String boardContent = multiRequest.getParameter("content");
			    			String boardUrl = multiRequest.getParameter("url");
							
							BoardEH board = new BoardEH(boardNo, boardTitle, boardContent, boardUrl);
							
							
							 String freeCategory = multiRequest.getParameter("category");
			    			  
			    			 Free free = new Free(freeCategory);
			    			 
			    			 
			    	          String[] beforeImg = multiRequest.getParameterValues("beforeImg");
			    	          System.out.println("beforeImg : " + Arrays.toString(beforeImg));
							
							
			    			  ArrayList<Img> fList = null;
							 
			    			  
			    			  
			    			  if(ServletFileUpload.isMultipartContent(request)) {
			    				  ArrayList<String> saveFiles = new ArrayList<String>();
			    				  
			    				  ArrayList<String> originFiles = new ArrayList<String>();
			    				  
			    				  Enumeration<String> files = multiRequest.getFileNames();
			    				  
			    				  while(files.hasMoreElements()) {
			    					  
			    					  // 업로드된 파일은 역순으로 전달됨
			    					  String name = files.nextElement();
			    					  
			    					  if(multiRequest.getFilesystemName(name) != null) {
			    						  // getFilesystemName(key) : rename된 파일명 얻어오기
			    						  saveFiles.add(multiRequest.getFilesystemName(name));
			    						  
			    						  originFiles.add(multiRequest.getOriginalFileName(name));
			    					  }
			    				  }
			    				  
			    				  fList = new ArrayList<Img>();
			    				  
			    				  for(int i = originFiles.size()-1; i>=0 ; i--) {
			    					Img file = new Img();
			    					file.setImgPath(savePath);
			    					file.setImgOriginName(originFiles.get(i));
			    					file.setImgChangeName(saveFiles.get(i));
			    					
			    					// 썸네일 이미지는 fileLevel 0 으로 
			    					// 나머지 이미지에는 fileLevel 1 부여
			    					if( (i==originFiles.size()-1)  // 제일 마지막 index
			    							&& multiRequest.getFilesystemName("img1") != null) {
			    						       // 파일넣고 rename 시켰을때 있으면
			    						file.setImgLevel(0);   // 0으로 하고  (썸네일)
			    					}else {
			    						file.setImgLevel(1);  // 나머지는 1로 한다
			    					}
			    					
			    					fList.add(file);
			    				}
			    				  
			    			  }
							
			    			  int result = FreeService.updateFree(board,fList,free,beforeImg);
			    				
			    				
			    				if(result > 0) msg = "게시글 등록 성공";
			    				else           msg = "게시글 등록 실패";	
			    				
			    				request.getSession().setAttribute("msg", msg);
			    				response.sendRedirect("list");
			    			  
							
							
					} catch(Exception e) {
						ExceptionForward.errorPage(request, response, "게시글 등록", e);
					}
				}
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
