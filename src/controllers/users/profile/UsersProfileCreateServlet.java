package controllers.users.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        //EntityManager em = DBUtil.createEntityManager();
        //UserProfile ep = em.find(UserProfile.class, Integer.parseInt(request.getParameter("id")));
        //em.close();
        //リクエストスコープにセット
        //request.setAttribute("user_profile", ep);
        //セッションスコープに"id"をセット
        //request.getSession().setAttribute("id", ep.getId());

        /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        */
       //protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String _token = (String)request.getParameter("_token");
        //if(_token != null && _token.equals(request.getSession().getId())) {
            //EntityManager em = DBUtil.createEntityManager();

            //アプリケーションスコープにセットしたログインした人のuser_id情報を取得
            //User e_user = (User)request.getServletContext().getAttribute("login_user_id");
            //String user_bbid = e_user.getBbid();

            //User e_user = em.find(User.class, Integer.parseInt(request.getParameter("id")));

            //UserProfile ep = new UserProfile();
            //ep.setUser_id(e_user.getId());
            //ep.setBbid(e_user.getBbid());
            //ep.setProfile_image(null);
            //e.setProfile(request.getParameter("profile"));

            //Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            //ep.setCreated_at(currentTime);
            //ep.setUpdated_at(currentTime);
            //ep.setDelete_flag(0);
            //em.getTransaction().begin();
            //em.persist(ep);
            //em.getTransaction().commit();
            //em.close();

            //request.setAttribute("flush", "DBのprofileカラムを作成しました。");


            //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/_form_upload.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/upload.jsp");
        rd.forward(request, response);

    }

}
