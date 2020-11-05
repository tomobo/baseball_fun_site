package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserProfile;
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

        User eu =null;

        EntityManager em = DBUtil.createEntityManager();

        eu = em.find(User.class, Integer.parseInt(request.getParameter("id")));
        Integer user_id = eu.getId(); //user_idを取得
        em.close();

        UserProfile ep = null; //UserProfileクラスの変数を作成
        EntityManager emp = DBUtil.createEntityManager();
        try {
        ep = emp.createNamedQuery("getEntity", UserProfile.class) //NamedQueryを利用して、テーブルusers_profileからuser_idが一致する情報を変数に格納
                .setParameter("user_id", user_id)
                .getSingleResult();
        } catch(NoResultException ex) {}

        String image = ep.getProfile_image();
        if (image != null){
          //セッションスコープにセット
            request.getSession().setAttribute("image", image);
        }
        emp.close();

        //セッションスコープにセット
        request.getSession().setAttribute("user", eu);

        //セッションスコープに"bbid"をセット
        //request.getSession().setAttribute("user_id", es.getBbid());
        //セッションスコープに"user_name"セット
        request.getSession().setAttribute("user_name", eu.getUser_name());


        //アプリケーションスコープにセットしたログインした人のuser_id情報を取得
        //User e_asc = (User)request.getServletContext().getAttribute("login_user_id");
        //Integer easc_id = e_asc.getId();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/mypage.jsp");
        rd.forward(request, response);

    }

}
