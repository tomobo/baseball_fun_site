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
        /// フラッシュメッセージがセッションスコープにセットされていたら、リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        User eu =null; //Userクラスのオブジェクトを作成
        EntityManager em = DBUtil.createEntityManager();
        eu = em.find(User.class, Integer.parseInt(request.getParameter("id"))); //getパラメータのidのオブジェクトを取得
        Integer user_id = eu.getId(); //user_idを取得
        em.close();

        UserProfile ep = null; //UserProfileクラスの変数を作成
        EntityManager emp = DBUtil.createEntityManager();
        try {
            //NamedQueryを利用して、テーブルusers_profileからuser_idが一致する情報を変数に格納
            ep = emp.createNamedQuery("getEntity", UserProfile.class)
                    .setParameter("user_id", user_id)
                    .getSingleResult();
        } catch(NoResultException ex) {}

        String image = ep.getProfile_image(); //プロフィール画像のパスを取得
        if (image != null){
            request.getSession().setAttribute("image", image); //セッションスコープにセット
        }
        emp.close();
        request.getSession().setAttribute("user", eu); //セッションスコープにセット
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/mypage.jsp");
        rd.forward(request, response);

    }

}
