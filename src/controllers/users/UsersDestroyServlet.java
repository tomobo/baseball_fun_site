package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesDestroyServlet
 */
@WebServlet("/users/destroy")
public class UsersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token"); //リクエストスコープからトークンを取得
        if(_token != null && _token.equals(request.getSession().getId())) { //トークンがセッションIDと同じ場合
            EntityManager em = DBUtil.createEntityManager();
            User e_asc = (User)request.getServletContext().getAttribute("login_user_id"); //ログインユーザーのオブジェクトを取得
            Integer easc_id = e_asc.getId();

            User e = em.find(User.class, easc_id); //ログインユーザーのオブジェクトをDBより取得
            e.setDelete_flag(1);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().removeAttribute("login_user"); //セッションスコープからログインユーザーの情報を削除
            request.getSession().setAttribute("flush", "削除が完了しました。");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}