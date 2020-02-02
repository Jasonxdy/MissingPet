package com.kh.semiproject.management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semiproject.AskBoard.model.service.AskBoardService;
import com.kh.semiproject.AskBoard.model.vo.Answer;
import com.kh.semiproject.AskBoard.model.vo.AskBoard;
import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.common.ExceptionForward;

import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.qaBoard.model.service.QnABoardService;
import com.kh.semiproject.qaBoard.model.vo.QnABoard;
import com.kh.semiproject.report.model.service.ReportService;
import com.kh.semiproject.report.model.vo.Report;

import sun.misc.Perf.GetPerfAction;


@WebServlet("/Management/*")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ManagementController() {
        super();
    }

    @SuppressWarnings("unchecked") 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/management").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		
		if(command.equals("/management_Main")) {
			
			path = "/WEB-INF/views/management/management_Main.jsp";
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
	
			
			} else if (command.equals("/management_Member")) {
				
				try{
					
					List<Member> mList = new MemberService().memberList();
				
					request.setAttribute("mList", mList);
					
					path = "/WEB-INF/views/management/management_Member.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "회원 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
			
			
			} else if (command.equals("/updateMember")) {
				
				String newId = request.getParameter("newId");
				String newName = request.getParameter("newName");
				String newEmail = request.getParameter("newEmail");
			
				System.out.println(newId);
				System.out.println(newName);
				System.out.println(newEmail);
			
				try{
					
					int result = new MemberService().updateMember(newId, newName, newEmail);
										
					path = "management_Member";
					
					System.out.println(result +"수정 완료");
					
					response.sendRedirect(path);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "회원 정보 수정", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
			} else if (command.equals("/deleteMember")) {
				
				String memberId = request.getParameter("deleteMemberId");
					
				System.out.println("선택 아이디 : " + memberId);
			
				try{
					
					int result = new MemberService().deleteMember(memberId);
										
					path = "management_Member";
					
					System.out.println(result +"회원 삭제 완료");
					
					response.sendRedirect(path);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "회원 삭제", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
				
			} else if (command.equals("/searchMember")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Member> searchMList = new MemberService().searchMember(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Member.jsp";
					
					System.out.println(searchMList +"검색 완료");
					
					request.setAttribute("mList", searchMList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "회원 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
				
				
				
			} else if (command.equals("/management_Board")) {
				
				path = "/WEB-INF/views/management/management_Board.jsp";
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} else if (command.equals("/management_Board_Find")) {
				
				try{
					
					List<Board> FindBoardList = new BoardService().FindBoardList();
				
					request.setAttribute("FindBoardList", FindBoardList);
					
					path = "/WEB-INF/views/management/management_Board_Find.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "찾아요 게시판 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
			} else if (command.equals("/searchFindBoard")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Board> FindBoardList = new BoardService().searchFindBoard(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Board_Find.jsp";
					
					System.out.println(FindBoardList +"검색 완료");
					
					request.setAttribute("FindBoardList", FindBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "찾아요 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
			
			} else if (command.equals("/management_Board_See")) {
				
					try{
					
					List<Board> SeeBoardList = new BoardService().SeeBoardList();
				
					request.setAttribute("SeeBoardList", SeeBoardList);
					
					path = "/WEB-INF/views/management/management_Board_See.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "봤어요 게시판 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
					
			
			
			} else if (command.equals("/searchSeeBoard")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Board> SeeBoardList = new BoardService().searchSeeBoard(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Board_See.jsp";
					
					System.out.println(SeeBoardList +"검색 완료");
					
					request.setAttribute("SeeBoardList", SeeBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "봤어요 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
			} else if (command.equals("/management_Board_Adopt")) {
							
				try{
					
					List<Board> AdoptBoardList = new BoardService().AdoptBoardList();
				
					request.setAttribute("AdoptBoardList", AdoptBoardList);
					
					path = "/WEB-INF/views/management/management_Board_Adopt.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "분양합니다 게시판 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
			} else if (command.equals("/searchAdoptBoard")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Board> AdoptBoardList = new BoardService().searchAdoptBoard(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Board_Adopt.jsp";
					
					System.out.println(AdoptBoardList +"검색 완료");
					
					request.setAttribute("AdoptBoardList", AdoptBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "분양합니다 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
							

			} else if (command.equals("/management_Board_Review")) {
				
				try{
					
					List<Board> ReviewBoardList = new BoardService().ReviewBoardList();
				
					request.setAttribute("ReviewBoardList", ReviewBoardList);
					
					path = "/WEB-INF/views/management/management_Board_Review.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "만남 그 후 게시판 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
			} else if (command.equals("/searchReviewBoard")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Board> ReviewBoardList = new BoardService().searchReviewBoard(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Board_Review.jsp";
					
					System.out.println(ReviewBoardList +"검색 완료");
					
					request.setAttribute("ReviewBoardList", ReviewBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "만남 그 후 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
							
				
			} else if (command.equals("/management_Board_Free")) {
				
				try{
					
					List<Board> FreeBoardList = new BoardService().FreeBoardList();
				
					request.setAttribute("FreeBoardList", FreeBoardList);
					
					path = "/WEB-INF/views/management/management_Board_Free.jsp";
					
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "자유게시판 목록 조회", e);
				
				
				} finally {
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
			} else if (command.equals("/searchFreeBoard")) {
				
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Board> FreeBoardList = new BoardService().searchFreeBoard(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Board_Free.jsp";
					
					System.out.println(FreeBoardList +"검색 완료");
					
					request.setAttribute("FreeBoardList", FreeBoardList);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);

					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "자유게시판 검색", e);
	
				
				}
							
				
				
			} else if (command.equals("/management_QnA")) {
				
				
				try{
					
					List<QnABoard> qaBoardList = new QnABoardService().qaBoardList();
										
					path = "/WEB-INF/views/management/management_QnA.jsp";
					
					System.out.println("qaBoardList 조회 완료");
					
					request.setAttribute("qaBoardList", qaBoardList);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);

					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "QnA게시판 조회", e);
	
				
				}

			
				
				
				
			} else if (command.equals("/insertQnA")) {
				
				String QnATitle = request.getParameter("QnATitle");
				String QnAContent = request.getParameter("QnAContent");
				System.out.println("등록 제목 : " + QnATitle);
				System.out.println("등록 내용 : " + QnAContent);
				
							
				try{
					
					int result = new QnABoardService().insertQnA(QnATitle,QnAContent);
										
					path = "management_QnA";
					
					System.out.println(result +"개의 글 등록 완료");
				
					response.sendRedirect(path);
					
//					view = request.getRequestDispatcher(path);
//					view.forward(request, response);

					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "QnA게시판 글 등록", e);
				}
				
				
				
				
				} else if (command.equals("/updateQnA")) {
					int UpdateQnANo = Integer.parseInt(request.getParameter("UpdateQnANo"));
					String UpdateQnATitle = request.getParameter("UpdateQnATitle");
					String UpdateQnAContent = request.getParameter("UpdateQnAContent");
					System.out.println("수정할 글 번호 : " + UpdateQnANo);
					System.out.println("수정 제목 : " + UpdateQnATitle);
					System.out.println("수정 내용 : " + UpdateQnAContent);
					
								
					try{
						
						int result = new QnABoardService().updateQnA(UpdateQnANo,UpdateQnATitle,UpdateQnAContent);
						
						path = "management_QnA";
						
						System.out.println(result +"개의 글 수정 완료");
					
						response.sendRedirect(path);
						
						
					} catch(Exception e){
						
						ExceptionForward.errorPage(request, response, "QnA게시판 글 수정", e);
		
					
					}
				
					
					
					
					
				} else if (command.equals("/deleteQnA")) {
				
					int DeleteQnANo = Integer.parseInt(request.getParameter("DeleteQnANo"));

					System.out.println("삭제할 글 번호 : " + DeleteQnANo);

					
								
					try{
						
						int result = new QnABoardService().deleteQnA(DeleteQnANo);
						
						path = "management_QnA";
						
						System.out.println(result +"개의 글 삭제 완료");
					
						response.sendRedirect(path);
						
						
					} catch(Exception e){
						
						ExceptionForward.errorPage(request, response, "QnA게시판 글 삭제", e);
		
					}
					
				} else if (command.equals("/searchQnA")) {
					
					String searchKey = request.getParameter("searchKey");
					String searchValue = request.getParameter("searchValue");
					System.out.println("선택 옵션 : " + searchKey);
					System.out.println("검색 내용 : " + searchValue);
					try{
						
						List<QnABoard> searchQnAList = new QnABoardService().searchQnA(searchKey, searchValue);
											
						path = "/WEB-INF/views/management/management_QnA.jsp";
						
						System.out.println(searchQnAList +"검색 완료");
						
						request.setAttribute("qaBoardList", searchQnAList);
						
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
						
					} catch(Exception e){
						
						ExceptionForward.errorPage(request, response, "QnA 게시판 검색", e);
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
					
					}
					

			} else if (command.equals("/management_Ask")) {
					
				//int askNo = Integer.parseInt(request.getParameter("askNo"));
				
				try{
					
					
					
					List<AskBoard> AskBoardList = new AskBoardService().AskBoardList();
										
					path = "/WEB-INF/views/management/management_Ask.jsp";
					
					System.out.println(AskBoardList + "ASK 목록 조회 완료");
					
					request.setAttribute("AskBoardList", AskBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "1:1 문의 게시판 조회", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
				
				
			
				
				
				
				
				} else if (command.equals("/answer")) {
				
				int answerAskNo = Integer.parseInt(request.getParameter("answerAskNo"));
				String answerContent = request.getParameter("answerContent");
				
				try{
					
					int result = new AskBoardService().insertAnswer(answerAskNo,answerContent);
					
					request.setAttribute("AnswerList", result);
					
					path = "management_Ask";
					
					System.out.println("ASK 답변 등록 완료");
						
					response.sendRedirect(path);
					//view = request.getRequestDispatcher(path);
					//view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "1:1 문의 게시판 답변 등록", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
				
				
					
					} else if (command.equals("/searchAsk")) {
					
					String searchKey = request.getParameter("searchKey");
					String searchValue = request.getParameter("searchValue");
					System.out.println("선택 옵션 : " + searchKey);
					System.out.println("검색 내용 : " + searchValue);
					try{
						
						List<AskBoard> searchAsk = new AskBoardService().searchAsk(searchKey, searchValue);
											
						path = "/WEB-INF/views/management/management_Ask.jsp";
						
						System.out.println(searchAsk +"검색 완료");
						
						request.setAttribute("AskBoardList", searchAsk);
						
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
						
					} catch(Exception e){
						
						ExceptionForward.errorPage(request, response, "1:1문의 게시판 검색", e);
						view = request.getRequestDispatcher(path);
						view.forward(request, response);
					
					}
					
					
					
					
					
				
			} else if (command.equals("/management_Report")) {
				
				
				try {
					
					List<Report> reportBoardList = new ReportService().ReportBoardList();
					
					path = "/WEB-INF/views/management/management_Report.jsp";
					
					System.out.println(reportBoardList + "신고 목록 조회 완료");
					
					request.setAttribute("reportBoardList", reportBoardList);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch (Exception e) {
					ExceptionForward.errorPage(request, response, "1:1문의 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
				
				
			} else if (command.equals("/searchReport")) {
					
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				System.out.println("선택 옵션 : " + searchKey);
				System.out.println("검색 내용 : " + searchValue);
				try{
					
					List<Report> searchReport = new ReportService().searchReport(searchKey, searchValue);
										
					path = "/WEB-INF/views/management/management_Report.jsp";
					
					System.out.println(searchReport +"검색 완료");
					
					request.setAttribute("reportBoardList", searchReport);
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} catch(Exception e){
					
					ExceptionForward.errorPage(request, response, "신고 게시판 검색", e);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				
				}
					
					
				
				
				
			}
			
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
