package controllers.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeesEditServlet
 */
@WebServlet("/passwordreset")
public class PasswordResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    // パスワードリセット画面を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        //"forward"を使う場合は、RequestDispatcherインターフェースのオブジェクトを作成する必要がある
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/identification.jsp");
        rd.forward(request, response);  //"forward"メソッドは、呼び出し元のサーブレットの"doGet"や"doPost"が呼び出された時に引数に指定されている値をそのまま渡す。
    }

}