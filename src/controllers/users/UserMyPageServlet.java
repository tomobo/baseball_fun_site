package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UserMyPageServlet
 */
@WebServlet("/mypage")
public class UserMyPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /// フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        EntityManager em = DBUtil.createEntityManager();

        User es = em.find(User.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        //セッションスコープにセット
        request.getSession().setAttribute("user", es);

        //セッションスコープに"bbid"をセット
        //request.getSession().setAttribute("user_id", es.getBbid());
        //セッションスコープに"user_name"セット
        request.getSession().setAttribute("user_name", es.getUser_name());


        //アプリケーションスコープにセットしたログインした人のuser_id情報を取得
        //User e_asc = (User)request.getServletContext().getAttribute("login_user_id");
        //Integer easc_id = e_asc.getId();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/mypage.jsp");
        rd.forward(request, response);

    }

}
