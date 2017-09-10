package cn.com.weiwang.cms.backend.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.weiwang.cms.utils.DBUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkcode = request.getParameter("checkcode");
		
		String sessionCodes = (String)request.getSession().getAttribute("codes");
		//if(!sessionCodes.equals(checkcode)){
		
//		if(sessionCodes.isEmpty()){
//			//重定向到登录页面
//			request.setAttribute("error", "验证码为空");
//			request.setAttribute("username",username);
//			request.setAttribute("password",password);
//			//response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
//			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
//			return;
//		}
//		
//		if(checkcode.isEmpty()){
//			//重定向到登录页面
//			request.setAttribute("error", "验证码为空");
//			//request.setAttribute("username",username);
//			//在login.jsp里面用param.username可以访问到变量
//			//request.setAttribute("password",password);
//			//response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
//			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
//			return;
//		}
//		
//		if(!sessionCodes.equalsIgnoreCase(checkcode)){
//			//重定向到登录页面
//			request.setAttribute("error", "验证码错误");
//			request.setAttribute("username",username);
//			request.setAttribute("password",password);
//			//response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
//			request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
//			return;
//		}
		String sql = "select * from t_admin where username = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String pwd = rs.getString("password");
				if(!password.equals(pwd)){
					//密码不正确
					request.setAttribute("error", "用户【" + username +"】密码不正确,请重试");
					request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
					return;
				}
			}else{//用户不存在
				request.setAttribute("error", "用户【" + username +"】不存在,请联系管理员");
				request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
				return;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		//判断用户是否登录，还可以判断用户登录时长
		request.getSession().setAttribute("LOGIN_ADMIN",username);
		
		request.getRequestDispatcher("/backend/main.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/backend/main.jsp");
	}

}











