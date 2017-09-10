package cn.com.weiwang.cms.backend.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���HTTPSession�е��������ݣ�����HTTPSession����
		request.getSession().invalidate();
		//ת���¼ҳ��
		response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
	}

}











