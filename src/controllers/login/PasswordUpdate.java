package controllers.login;

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
import utils.EncryptUtil;

/**
 * Servlet implementation class PasswordUpdate
 */
@WebServlet("/passwordupdate")
public class PasswordUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8"); //文字化け対策
        EntityManager em = DBUtil.createEntityManager();

        //フォームでリクエストスコープにセットした値を格納する
        String new_password = (String)request.getParameter("password");
        //更新したい対象のデータを取得する
        User e = em.find(User.class, (request.getSession().getAttribute("id"))); //セッションスコープから取得
        request.getSession().removeAttribute("id"); //セッションスコープの値を削除
        //System.out.println(request.getSession().getAttribute("id")); //セッションスコープにセットされている"ID"の値を表示
        //System.out.println(request.getParameter("password")); //リクエストスコープにセットされている"password"の値を表示

        if(new_password != null){
          //新しいパスワードをハッシュ化し、Userオブジェクトのパスワードにセットする（EncryptUtil.javaに定義してるよ）
            e.setPassword(EncryptUtil.getPasswordEncrypt(new_password, (String)this.getServletContext().getAttribute("pepper")));
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setUpdated_at(currentTime);
            //トランザクションの開始とコミットを実施
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "パスワードの変更が完了しました。");
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            em.close();
            request.getSession().setAttribute("flush", "パスワードの更新に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
