/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2020-02-01 09:25:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1579838816000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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

      out.write("\r\n");
      out.write("    \r\n");

	boolean save = false; // 아이디 저장 체크박스 값을 수정하기 위한 변수
	String saveId = ""; // 쿠키에 저장된 saveId라는 키가 가지고 있는 값을 저장할 변수
	
	Cookie[] cookies = request.getCookies(); // 전달받은 쿠기 저장
	
	// 서버 첫 시작시 request.getCookies()의 값이 null
	// -> if문으로 처리하지 않는 경우 페이지 로딩 시 NullPointerException이 발생
	if(cookies != null){
		for(Cookie c : cookies){
			// 쿠키 객체에서 name을 얻어와 그 값이 "saveId"와 같은지 비교
			// 		== key, 속성
			if(c.getName().equals("saveId")){
				saveId = c.getValue();
				save = true;
			}
		}
	}
	String memberEmail = (String)request.getAttribute("memberEmail");
	System.out.println(memberEmail);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>로그인 페이지</title>\r\n");
      out.write("<script>\r\n");
      out.write("\t");
 if(memberEmail != null){ 
      out.write("\r\n");
      out.write("\talert(\"회원님의 이메일(");
      out.print( memberEmail );
      out.write(")로 요청을 보냈습니다.\");\r\n");
      out.write("\t");
	session.removeAttribute("memberEmail");
	} 
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/css/login.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div  class=\"col\">\r\n");
      out.write("\r\n");
      out.write("    <div>\r\n");
      out.write("\r\n");
      out.write("      <form class=\"form-signin\" method=\"POST\" action=\"");
      out.print( request.getContextPath() );
      out.write("/member/login\" onsubmit=\"return loginValidate();\">\r\n");
      out.write("        <div class=\"text-center mb-4\">\r\n");
      out.write("\t        <a href=\"");
      out.print( request.getContextPath() );
      out.write("\">\r\n");
      out.write("\t          <img class=\"mb-4 rounded\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/img/MissPet.png\"\r\n");
      out.write("\t            alt=\"\" width=\"170\">\r\n");
      out.write("\t        </a>\r\n");
      out.write("          <!-- <h1 class=\"h3 mb-3 font-weight-normal\">Login</h1> -->\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-label-group\">\r\n");
      out.write("          <input type=\"text\" id=\"memberId\" name=\"memberId\" class=\"form-control\" placeholder=\"아이디\"\r\n");
      out.write("          value=\"");
      out.print( saveId );
      out.write("\" required autofocus>\r\n");
      out.write("          <label for=\"memberId\">아이디</label>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-label-group\">\r\n");
      out.write("          <input type=\"password\" id=\"memberPwd\" name=\"memberPwd\" class=\"form-control\" placeholder=\"비밀번호\" required>\r\n");
      out.write("          <label for=\"memberPwd\">비밀번호</label>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"checkbox mb-3\">\r\n");
      out.write("          <label>\r\n");
      out.write("            <input type=\"checkbox\" id=\"save\" name=\"save\" ");
      out.print( save ? "checked" : "" );
      out.write(" value=\"remember-me\"> 아이디 저장\r\n");
      out.write("          </label>\r\n");
      out.write("        </div>\r\n");
      out.write("        <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">로그인</button>\r\n");
      out.write("      </form>\r\n");
      out.write("      <div id=\"find\" class=\"text-center\">\r\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath() );
      out.write("/member/findPage\">\r\n");
      out.write("        <span class=\"\">아이디 찾기</span>·\r\n");
      out.write("        <span class=\"mr-5 pr-2\">비밀번호 찾기</span> \r\n");
      out.write("        </a>\r\n");
      out.write("        <a href=\"signUpPage\">\r\n");
      out.write("        <span class=\"ml-5\">회원가입</span>\r\n");
      out.write("        </a>\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("    \t");
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
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("  <script>\r\n");
      out.write("\t\t// 로그인 유효성 검사\r\n");
      out.write("\t\tfunction loginValidate(){\r\n");
      out.write("\t\t\t\t\t\t\t\t\t//trim() 문자열의 앞뒤 공백 제거\r\n");
      out.write("\t\t\tif( $(\"#memberId\").val().trim().length == 0 ){\r\n");
      out.write("\t\t\t\talert(\"아이디를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$(\"#memberId\").focus();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\tif( $(\"#memberPwd\").val().trim().length == 0 ){\r\n");
      out.write("\t\t\t\talert(\"비밀번호를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$(\"#memberPwd\").focus();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
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
