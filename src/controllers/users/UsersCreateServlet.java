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
            //e.setProfile(request.getParameter("profile"));
            //e.setProfile_image(request.getParameter("profile_image"));
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

                //プロフィールクラスのテーブルを作成
                //EntityManager ep = DBUtil.createEntityManager();

                //UserProfile p = new UserProfile();

                //p.setBbid(bbid);
                //p.setProfile_image(null);
                //p.setProfile(null);

                //Timestamp currentTime_P = new Timestamp(System.currentTimeMillis());
                //p.setCreated_at(currentTime_P);
                //p.setUpdated_at(currentTime_P);
                //p.setDelete_flag(0);

                //ep.getTransaction().begin();
                //ep.persist(p);
                //ep.getTransaction().commit();
                //ep.close();

                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

}