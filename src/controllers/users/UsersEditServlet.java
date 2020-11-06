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
 * Servlet implementation class EmployeesEditServlet
 */
@WebServlet("/users/edit")
public class UsersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        User es = em.find(User.class, Integer.parseInt(request.getParameter("id"))); //getパラメータのIDのオブジェクト情報を取得
        em.close();

        request.setAttribute("_token", request.getSession().getId()); //リクエストスコープにセッションIDをセット
        request.setAttribute("user", es); //リクエストスコープにオブジェクトをセット
        request.getSession().setAttribute("id", es.getId()); //セッションスコープに"id"をセット
        request.getSession().setAttribute("user_id", es.getBbid()); //セッションスコープに"bbid"をセット
        Integer es_id = es.getId();

        //アプリケーションスコープにセットしたログインした人のuser_id情報を取得
        User e_asc = (User)request.getServletContext().getAttribute("login_user_id");
        Integer easc_id = e_asc.getId();

        //セッションスコープのユーザーIDとアプリケーションスコープのユーザーIDを比較して、一致するなら更新画面を表示
        if(es_id == easc_id){
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error/error_edit.jsp");
            rd.forward(request, response);
        }
    }

}
