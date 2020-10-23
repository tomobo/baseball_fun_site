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
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            //System.out.println(request.getServletContext().getAttribute("login_user_id"));

            //User e_asc = (User)request.getServletContext().getAttribute("login_user_id");
            //User e = em.find(User.class, e_asc);
            //User e = em.find(User.class, (Integer)(request.getSession().getAttribute("bbid")));
            //User e = em.find(User.class, (Integer)(request.getServletContext().getAttribute("login_user_id")));
            User e_asc = (User)request.getServletContext().getAttribute("login_user_id");
            Integer easc_id = e_asc.getId();
            System.out.println(easc_id);
            User e = em.find(User.class, easc_id);
            e.setDelete_flag(1);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().removeAttribute("login_user");
            request.getSession().setAttribute("flush", "削除が完了しました。");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}