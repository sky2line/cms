package cn.com.weiwang.cms.backend.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.weiwang.cms.backend.entity.Article;

public class SearchArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchArticlesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ�����б�
		List<Article> articles = new ArrayList<Article>();
		for(int i = 0 ; i < 10 ; i++){
			Article a = new Article();
			a.setTitle("���ǲ�������" + i);
			a.setContent("���������ǣ�" + i);
			articles.add(a);
		}
		request.setAttribute("articles",articles);
		//forward��article_list.jsp
		request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
	}

}
