package controllers.users.profile;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.User;
import models.UserProfile;
import utils.DBUtil;

@WebServlet("/image/upload")
@MultipartConfig(location="C:/pleiades/workspace/baseball_fun_site/WebContent", //locationの値は絶対パスのみ(コンテキスパスまでの)で指定。
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 *  1024 *  5,
    maxRequestSize = 1024 *  1024 *  5 *  5)
public class UserProfileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String full_pass = "C:/pleiades/workspace/baseball_fun_site/WebContent";
        Part part = request.getPart("file");
        String file_name = this.getFileName(part); //ファイル名を取得
        //part.write(request.getContextPath() + "/image/uploaded" + File.separator + file_name); //書き込み先を指定
        //part.write("/image/uploaded" + File.separator + file_name); //書き込み先を指定
        part.write(full_pass + "/image/uploaded" + File.separator + file_name); //書き込み先を指定
        request.getSession().setAttribute("profile_image_name", "/image/uploaded" + File.separator + file_name); //JSPでで<c:url>タグを利用するため、コンテキストパス以降のパスを指定
        //System.out.println(request.getContextPath() + "/image/uploaded" + File.separator + file_name);
        System.out.println(full_pass + "/image/uploaded" + File.separator + file_name);
        System.out.println(request.getSession().getAttribute("profile_image_name"));

        User e = (User)request.getSession().getAttribute("login_user");
        Integer user_id = e.getId();
        String bbid = e.getBbid();
        System.out.println(user_id);
        System.out.println(bbid);

        UserProfile ep = null;
        EntityManager em = DBUtil.createEntityManager();
        try {
        ep = em.createNamedQuery("getEntity", UserProfile.class)
                .setParameter("user_id", user_id)
                .getSingleResult();
        } catch(NoResultException ex) {}

        //UserProfile ep = em.find(UserProfile.class,(Integer)(request.getSession().getAttribute("id")));
        //UserProfile ep = em.find(UserProfile.class, user_id);
        String p_bbid = ep.getBbid();
        System.out.println(p_bbid);
        em.close();

        //EntityManager em = DBUtil.createEntityManager();
        //System.out.println("1");
        //UserProfile ep = em.find(UserProfile.class, user_id);
        //System.out.println("2");
        //String p_bbid = ep.getBbid();
        //System.out.println(p_bbid);
        //em.close();
        //System.out.println(ep.getId());


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/mypage.jsp");
        rd.forward(request, response);
    }


    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}