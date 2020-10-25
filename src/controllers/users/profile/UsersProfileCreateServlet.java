package controllers.users.profile;

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

import models.UserProfile;
import models.validators.UserProfileValidator;
import utils.DBUtil;

/**
 * Servlet implementation class UsersProfileCreateServlet
 */
@WebServlet("/profile/create")
public class UsersProfileCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersProfileCreateServlet() {
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

            UserProfile e = new UserProfile();

            e.setProfile_image(request.getParameter("profile_image"));
            e.setProfile(request.getParameter("profile"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            List<String> errors = UserProfileValidator.validateProfile(e, true, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user_profile", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/new_profile.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }

}
