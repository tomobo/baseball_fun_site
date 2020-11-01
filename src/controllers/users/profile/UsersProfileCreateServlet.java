package controllers.users.profile;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String _token = (String)request.getParameter("_token");
        //if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //アプリケーションスコープにセットしたログインした人のuser_id情報を取得
            //User e_user = (User)request.getServletContext().getAttribute("login_user_id");
            //String user_bbid = e_user.getBbid();

            User e_user = em.find(User.class, Integer.parseInt(request.getParameter("id")));

            UserProfile e_profile = new UserProfile();
            e_profile.setUser_id(e_user.getId());
            e_profile.setBbid(e_user.getBbid());
            e_profile.setProfile_image(null);
            //e.setProfile(request.getParameter("profile"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e_profile.setCreated_at(currentTime);
            e_profile.setUpdated_at(currentTime);
            e_profile.setDelete_flag(0);

          //  List<String> errors = UserProfileValidator.validateProfile(e, true, true);
           // if(errors.size() > 0) {
           //     em.close();

           //     request.setAttribute("_token", request.getSession().getId());
           //     request.setAttribute("user_profile", e);
           //     request.setAttribute("errors", errors);

           //     RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/mypage.jsp");
           //     rd.forward(request, response);
           // } else {
                em.getTransaction().begin();
                em.persist(e_profile);
                em.getTransaction().commit();
                em.close();
                //request.getSession().setAttribute("flush", "DBのprofileカラムを作成しました。");

                request.setAttribute("flush", "DBのprofileカラムを作成しました。");


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/_form_upload.jsp");
                rd.forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/");
           // }
        //}
    }

}
