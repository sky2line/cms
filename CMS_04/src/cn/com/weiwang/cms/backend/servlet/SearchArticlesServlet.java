package cn.com.weiwang.cms.backend.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.weiwang.cms.backend.entity.Article;
import cn.com.weiwang.cms.utils.DBUtil;

public class SearchArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchArticlesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询文章列表
		List<Article> articles = new ArrayList<Article>();
		
		String sql = "select * from t_article";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				Article a = new Article();
				
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date createTime = rs.getTimestamp("createtime");
				
				a.setTitle(title);
				a.setContent(content);
				a.setCreateTime(createTime);
				
				articles.add(a);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		request.setAttribute("articles",articles);
		//forward到article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}
}









