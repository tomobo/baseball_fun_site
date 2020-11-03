package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.UserProfile;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/users/create")
public class UsersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersCreateServlet() {
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

            User e = new User();
            e.setBbid(request.getParameter("bbid"));
            e.setUser_name(request.getParameter("user_name"));
            e.setMail_address(request.getParameter("mail_address"));
            e.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")
                            )
                    );

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            List<String> errors = UserValidator.validate(e, true, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                rd.forward(request, response);
            } else {
                //ユーザークラスのテーブルを作成
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();

                //User e_user = em.find(User.class, e.getId());

                EntityManager emp = DBUtil.createEntityManager();
                UserProfile ep = new UserProfile();
                ep.setUser_id(e.getId());
                ep.setBbid(e.getBbid());
                ep.setProfile_image(null);

                //Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                ep.setCreated_at(currentTime);
                ep.setUpdated_at(currentTime);
                ep.setDelete_flag(0);
                emp.getTransaction().begin();
                emp.persist(ep);
                emp.getTransaction().commit();
                emp.close();

                //request.setAttribute("flush", "DBのprofileカラムを作成しました。");
                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

}