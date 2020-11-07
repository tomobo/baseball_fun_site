package controllers.users.profile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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
import models.validators.UserProfileValidator;
import utils.DBUtil;

@WebServlet("/image/upload")
@MultipartConfig(location="C:/pleiades/workspace/baseball_fun_site/WebContent", //locationの値は絶対パスのみ(コンテキスパスまでの)で指定。
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 *  1024 *  5,
    maxRequestSize = 1024 *  1024 *  5 *  5)
public class UserProfileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String _token = (String)request.getParameter("_token"); //リクエストスコープからトークンを取得
        //if(_token != null && _token.equals(request.getSession().getId())) { //トークンがセッションIDと同じ場合
            String full_pass = "C:/pleiades/workspace/baseball_fun_site/WebContent";
            Part part = request.getPart("file");
            String file_name = this.getFileName(part); //ファイル名を取得
            part.write(full_pass + "/image/uploaded" + File.separator + file_name); //書き込み先を指定
            request.getSession().setAttribute("profile_image_name", "/image/uploaded" + File.separator + file_name); //JSPでで<c:url>タグを利用するため、コンテキストパス以降のパスを指定
            System.out.println(full_pass + "/image/uploaded" + File.separator + file_name);
            System.out.println(request.getSession().getAttribute("profile_image_name"));

            //ログインユーザーのUserクラスのEntityを取得
            User e = (User)request.getSession().getAttribute("login_user");
            Integer user_id = e.getId(); //user_idを取得
            UserProfile ep = null; //UserProfileクラスの変数を作成
            EntityManager em = DBUtil.createEntityManager();
            try {
                ep = em.createNamedQuery("getEntity", UserProfile.class) //NamedQueryを利用して、テーブルusers_profileからuser_idが一致する情報を変数に格納
                        .setParameter("user_id", user_id)
                        .getSingleResult();
            } catch(NoResultException ex) {}

            String p_bbid = ep.getBbid();
            System.out.println(p_bbid);

            ep.setProfile_image((String)(request.getSession().getAttribute("profile_image_name")));
            ep.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            //バリデーションチェックを実施(オブジェクト, ファイル名重複チェック)
            Boolean profile_image_duplicate_check = true;
            List<String> errors = UserProfileValidator.validateProfile(ep, profile_image_duplicate_check);
            if(errors.size() > 0) {
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user_profile", ep);
                request.setAttribute("errors", errors);
                em.close();
                //response.sendRedirect(request.getContextPath() + "/profile/create");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/upload.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "ファイルパスをDBに登録しました。");//セッションスコープ
                response.sendRedirect(request.getContextPath() + "/mypage?id=" + user_id);
            }


            //em.getTransaction().begin();
            //em.getTransaction().commit();
            //em.close();
            //request.getSession().setAttribute("flush", "ファイルパスをDBに登録しました。");//セッションスコープ
            //response.sendRedirect(request.getContextPath() + "/mypage?id=" + user_id);
        //}
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