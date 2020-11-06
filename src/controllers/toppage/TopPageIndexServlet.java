package controllers.toppage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //デバック用
        //if(request.getSession().getAttribute("user_id") != null){
        //    request.setAttribute("user_id", request.getSession().getAttribute("user_id"));
        //    request.getSession().removeAttribute("user_id");
        //} else if(request.getSession().getAttribute("mail_address") != null){
        //    request.setAttribute("user_id", request.getSession().getAttribute("user_id"));
        //    request.getSession().removeAttribute("user_id");
        //} else if(request.getSession().getAttribute("id") != null){
        //    request.setAttribute("id", request.getSession().getAttribute("id"));
        //    request.getSession().removeAttribute("id");
        //}

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}