/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-01-30 08:39:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.management;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.semiproject.member.model.vo.Member;

public final class management_005fBoard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/management/../common/sidebar.jsp", Long.valueOf(1580371987840L));
    _jspx_dependants.put("/WEB-INF/views/management/../common/footer.jsp", Long.valueOf(1579838816000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.kh.semiproject.member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write(" <title> 미씽펫 - 관리자 페이지 </title>\n");
      out.write("            <link rel=\"stylesheet\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/index.css\" type=\"text/css\">\n");
      out.write("            <link rel=\"stylesheet\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/management.css\" type=\"text/css\">\n");
      out.write("            <link href=\"https://fonts.googleapis.com/css?family=Song+Myung|Noto+Sans+KR|Do+Hyeon|Yeon+Sung|Nanum+Myeongjo|Sunflower:300&display=swap\" rel=\"stylesheet\">\n");
      out.write("          </head>\n");
      out.write("        <body>\n");
      out.write("       <div class=\"container-fluid row-md-2\" style=\"height: 1000px;\">\n");
      out.write("\t\t\t\t\n");
      out.write("                ");
      out.write("\r\n");
      out.write("\r\n");

	String msg = (String)session.getAttribute("msg");
	Member loginMember = (Member)session.getAttribute("loginMember");
	

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("<script>\r\n");
      out.write("\t\t// 로그인 실패 메세지 출력 후 session에 남아있는 \"msg\" 속성 제거 \r\n");
      out.write("\t\t");
 if(msg != null){ 
      out.write("\r\n");
      out.write("\t\t\talert(\"");
      out.print( msg );
      out.write("\");\r\n");
      out.write("\t\t");
	session.removeAttribute("msg");
		 } 
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    \r\n");
      out.write("<div class=\"d-md-block bg-white sidebar\" id=\"sidebarAll\" style=\"z-index: 1100;\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"sidebar-sticky\" id=\"sidebarAll\">\r\n");
      out.write("\r\n");
      out.write("          <table class=\"table-responsive\">\r\n");
      out.write("\r\n");
      out.write("            <ul class=\"nav flex-column\">\r\n");
      out.write("              <li class=\"align-items-center text-center\">\r\n");
      out.write("                <a id=\"logo\" href=\"index.jsp\">\r\n");
      out.write("                  <img src=\"");
      out.print( request.getContextPath() );
      out.write("/img/logo7.png\" width=150px>\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\t\t\t");
 if(loginMember == null){ 
      out.write("\r\n");
      out.write("              <li class=\"nav-item align-items-center\">\r\n");
      out.write("              <a href=\"member/loginPage\">\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-outline-primary btn-sm px-5 mt-5 p-3\" id=\"loginBtn\">로그인</button>\r\n");
      out.write("              </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\t\t\t");
 } else {
      out.write("\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("              \t<div class=\"justif-content-end align-items-center text-center\">\r\n");
      out.write("\t\t\t\t");
      out.print( loginMember.getMemberName() );
      out.write("님 환영합니다. \r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<a href=\"member/logout\">\r\n");
      out.write("                <button type=\"button\" class=\"btn btn-outline-secondary btn-sm px-3 mt-5 p-2\" id=\"loginBtn\">로그아웃</button>\r\n");
      out.write("              </a>\r\n");
      out.write("              </li>\r\n");
      out.write("              ");
 } 
      out.write("\r\n");
      out.write("              \r\n");
      out.write("              <li class=\"nav-item mt-5\">\r\n");
      out.write("                <p>&nbsp;</p>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mt-3\">\r\n");
      out.write("                <p>&nbsp;</p>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mb-1\">\r\n");
      out.write("                <a class=\"nav-link active text-center mt-4 mb-1\" href=\"#\">\r\n");
      out.write("                  찾아주세요\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mb-1\">\r\n");
      out.write("                <a class=\"nav-link  text-center\" href=\"#\">\r\n");
      out.write("                  봤어요\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mb-1\">\r\n");
      out.write("                <a class=\"nav-link  text-center\" href=\"#\">\r\n");
      out.write("                  입양했어요\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mb-1\">\r\n");
      out.write("                <a class=\"nav-link  text-center\" href=\"#\">\r\n");
      out.write("                  만남 그 후\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item mb-5\">\r\n");
      out.write("                <a class=\"nav-link  text-center\" href=\"#\">\r\n");
      out.write("                  자유게시판\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("              <li class=\"nav-item\">\r\n");
      out.write("                <a class=\"nav-link  text-center\" href=\"");
      out.print( request.getContextPath() );
      out.write("/qaBoard/QnA_Main.jsp\">\r\n");
      out.write("                  QnA\r\n");
      out.write("                </a>\r\n");
      out.write("              </li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\t        ");
 if(loginMember != null && loginMember.getMemberId().equals("admin")){ 
      out.write("\r\n");
      out.write("\t\t       \t<li class=\"nav-item\">\r\n");
      out.write("\t                <a class=\"nav-link text-center\" \r\n");
      out.write("\t                href=\"");
      out.print(request.getContextPath());
      out.write("/Management/management_Main\">\r\n");
      out.write("\t               \t\t  관리자페이지\r\n");
      out.write("\t                </a>\r\n");
      out.write("\t            </li>\r\n");
      out.write("\t        ");
 } 
      out.write("        \r\n");
      out.write("              \r\n");
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("\r\n");
      out.write("          </table>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\n");
      out.write("\n");
      out.write(" \n");
      out.write("                  \n");
      out.write("                  <div class=\"container-fluid row-md-10 mr-5 mt-5\" style=\"width: 80%;\">\n");
      out.write("\t                <div id=\"page-content-wrapper mb-5 mt-5\">\n");
      out.write("\t                  <div class=\"card bg-light shadow ml-5 mt-5 mr-5 mb-5\">\n");
      out.write("\t                    <div class=\"card-body\">\n");
      out.write("                          <h5>&nbsp;&nbsp;&nbsp;관리자 페이지</h5>\n");
      out.write("\n");
      out.write("\n");
      out.write("                            <!-- Content -->\n");
      out.write("                            <div class=\"row-md-12\" id=\"manageheader\" style=\"width:100%; height:850px; overflow:auto\">\n");
      out.write("                              <table  class=\"table\">\n");
      out.write("\n");
      out.write("                              <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                  <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Member\">회원관리</a></th>\n");
      out.write("                                  <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board\">게시판관리</a></th>\n");
      out.write("                                  <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_QnA\">QnA등록</a></th>\n");
      out.write("                                  <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Ask\">1:1문의</a></th>\n");
      out.write("                                  <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Report\">신고 관리</a></th>\n");
      out.write("                                </tr>\n");
      out.write("                              </thead>\n");
      out.write("                            \n");
      out.write("                                </table>\n");
      out.write("                            \n");
      out.write("                         <table >\n");
      out.write("                            <thead>\n");
      out.write("                              <tr>\n");
      out.write("                               <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board_Find\">찾아주세요 게시판 </a> &nbsp;|&nbsp;</th>\n");
      out.write("                                <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board_See\">봤어요 게시판 </a> &nbsp;|&nbsp; </th>\n");
      out.write("                                <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board_Adopt\">분양합니다 게시판 </a> &nbsp;|&nbsp;</th>\n");
      out.write("                                <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board_Meeting\">만남 그 후 게시판 </a> &nbsp;|&nbsp; </th>\n");
      out.write("                                <th scope=\"col\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/Management/management_Board_Free\">자유게시판 </a></th>\n");
      out.write("                              </tr>\n");
      out.write("                            </thead>\n");
      out.write("             \t\t\t</table>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t<pre style=\"height: 900px\">\n");
      out.write("\t\t\t\t\t\t\t<!--  내용 입력 가능 -->\n");
      out.write("\t\t\t\t\t\t\t</pre>\n");
      out.write("\t\t\t\t\n");
      out.write("                           \n");
      out.write("                          </div>\n");
      out.write("                        \n");
      out.write("\n");
      out.write("\n");
      out.write("                        \n");
      out.write("                     </div>\n");
      out.write("                     \n");
      out.write("                    </div>\n");
      out.write("                     \n");
      out.write("                    </div>\n");
      out.write("       \n");
      out.write("                </div>              \n");
      out.write("                \n");
      out.write("\n");
      out.write("          </div>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <meta charset=\"UTF-8\">\r\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("  <title> 미씽펫 방문을 환영합니다! </title>\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\"\r\n");
      out.write("    integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\"\r\n");
      out.write("    crossorigin=\"anonymous\">\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/index.css\">\r\n");
      out.write("  \r\n");
      out.write("  <script src=\"https://code.jquery.com/jquery-3.4.1.min.js\"\r\n");
      out.write("    integrity=\"sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=\" \r\n");
      out.write("    crossorigin=\"anonymous\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    \r\n");
      out.write("    <footer id=\"footer\" class=\"\">\r\n");
      out.write("          <div class=\"footer mt-4\">\r\n");
      out.write("            <div class=\"footer text-center \" id=\"footer1\">\r\n");
      out.write("              <span class=\"text-muted\">\r\n");
      out.write("                <b class=\"small\"> | 상호명 : 청포도 | 대표 : 청포도 | 주소 : 서울특별시 광진구 자양로 54 2층 | 사업자 등록번호 : 206-87-05773 | Tel :\r\n");
      out.write("                  010-0000-0000 <br>\r\n");
      out.write("                  Copyright © 2019-2020 Muscat All rights Reserved.\r\n");
      out.write("                </b>\r\n");
      out.write("              </span>\r\n");
      out.write("\r\n");
      out.write("              <span class=\"footer mr-5 small \" id=\"footer2\">\r\n");
      out.write("                <a href=\"#\">이용약관 </a> &nbsp;\r\n");
      out.write("                <a href=\"#\">개인정보처리방침</a>\r\n");
      out.write("              </span>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("    </footer>\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\r\n");
      out.write("    integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\"\r\n");
      out.write("    crossorigin=\"anonymous\"></script>\r\n");
      out.write("  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"\r\n");
      out.write("    integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\"\r\n");
      out.write("    crossorigin=\"anonymous\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}