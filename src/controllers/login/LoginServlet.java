package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    // ログイン画面を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId()); //セッションIDをリクエストスコープに格納
        request.setAttribute("hasError", false); //認証エラーのdefault値をfalseでセット
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    // ログイン処理を実行
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean check_result = false; //認証OKのdefault値をfalseでセット
        // ログインした人の情報をString型の変数に格納
        String bbid = request.getParameter("bbid");
        String plain_pass = request.getParameter("password");
        User e = null; //Userクラスの変数を定義
        //bbidがnull/空ではないかつ、passwordがnull/空ではない場合。
        if(bbid != null && !bbid.equals("") && plain_pass != null && !plain_pass.equals("")) {
            EntityManager em = DBUtil.createEntityManager();
            String password = EncryptUtil.getPasswordEncrypt(
                    plain_pass,
                    (String)this.getServletContext().getAttribute("pepper")
                    );
            // ユーザーIDとパスワードが正しいかチェックする
            try {
                e = em.createNamedQuery("checkLoginCodeAndPassword", User.class)
                      .setParameter("bbid", bbid)
                      .setParameter("pass", password)
                      .getSingleResult();
            } catch(NoResultException ex) {}
            em.close();

            //bbidとパスワードが一致するレコードがある場合はログインOK
            if(e != null) {
                check_result = true; //認証OKの値をtrueでセット
            }
        }

        if(!check_result) {
            // 認証できなかったらログイン画面に戻る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true); //認証エラーの値をtrueでセット
            request.setAttribute("bbid", bbid);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        } else {
            // 認証できたらログイン状態にしてトップページへリダイレクト
            //セッションスコープの場合、ブラウザを終了する or sessionの有効期限が切れるまでデータを利用できる
            request.getSession().setAttribute("login_user", e);
            request.getSession().setAttribute("flush", "ログインしました。");

            //アプリケーションスコープの場合、Webアプリケーション全体の設定値を格納するため、Tomcatを再起動しない限りデータが残る(利用できる)
            request.getServletContext().setAttribute("login_user_id", e);
            //request.getServletContext().setAttribute("flush", "ログインしました。");

            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}