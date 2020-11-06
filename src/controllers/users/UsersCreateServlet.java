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
        String _token = (String)request.getParameter("_token"); //リクエストスコープからtokenを取得
        //トークンと現在のセッションIDが等しいことを確認。
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            //フォームで入力された情報をUserクラスのオブジェクトに格納。
            User e = new User();
            e.setBbid(request.getParameter("bbid"));
            e.setUser_name(request.getParameter("user_name"));
            e.setMail_address(request.getParameter("mail_address"));
            e.setPassword(
                    //パスワードのハッシュ化
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")
                            )
                    );

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            //バリデーションチェックを実施(オブジェクト, bbid重複チェック, user_name重複チェック, パスワードが空でないかのチェック)
            List<String> errors = UserValidator.validate(e, true, true, true);
            if(errors.size() > 0) {
                em.close();
                //バリデーションチェックにエラーがある場合は、戻り値のエラーメッセージをリクエストスコープに格納
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                rd.forward(request, response);
            } else {
                //バリデーションチェックがOKの時
                //usersテーブルのレコードを作成
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();

                //usersテーブルのレコードが作成できた時
                //users_profileテーブルのレコードも作成
                EntityManager emp = DBUtil.createEntityManager();
                UserProfile ep = new UserProfile();
                ep.setUser_id(e.getId());
                ep.setBbid(e.getBbid());
                ep.setProfile_image(null);
                ep.setCreated_at(currentTime);
                ep.setUpdated_at(currentTime);
                ep.setDelete_flag(0);
                emp.getTransaction().begin();
                emp.persist(ep);
                emp.getTransaction().commit();
                emp.close();

                //users,users_profileテーブルのレコード作成完了
                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

}