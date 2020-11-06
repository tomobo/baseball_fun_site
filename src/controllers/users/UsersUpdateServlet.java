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
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersUpdateServlet() {
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

            User e = em.find(User.class, (Integer)(request.getSession().getAttribute("id")));
            //User e = em.find(User.class, (User)(request.getSession().getAttribute("login_user")));

            // 現在の値と異なるBBIDが入力されていたら
            // 重複チェックを行う指定をする
            Boolean code_duplicate_check = true;
            if(e.getBbid().equals(request.getParameter("bbid"))) {
                code_duplicate_check = false;
            } else {
                e.setBbid(request.getParameter("bbid"));
            }

            // 現在の値と異なるユーザーネームが入力されていたら
            // 重複チェックを行う指定をする
            Boolean user_name_duplicate_check = true;
            if(e.getUser_name().equals(request.getParameter("user_name"))) {
                user_name_duplicate_check = false;
            } else {
                e.setUser_name(request.getParameter("user_name"));
            }

            // パスワード欄に入力があったら
            // パスワードの入力値チェックを行う指定をする
            Boolean password_check_flag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                e.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("pepper")
                                )
                        );
            }

            e.setUser_name(request.getParameter("user_name"));
            e.setMail_address(request.getParameter("mail_address"));
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            e.setDelete_flag(0);

            List<String> errors = UserValidator.validate(e, code_duplicate_check, user_name_duplicate_check, password_check_flag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("bbid");

                //response.sendRedirect(request.getContextPath() + "/users/index");
                response.sendRedirect(request.getContextPath() + "/mypage?id=" + request.getSession().getAttribute("id"));
            }
        }
    }

}
