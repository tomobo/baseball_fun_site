package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtil;

/**
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/indentification")
public class IdentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8"); //文字化け対策
        String user_id = (String)request.getParameter("user_id");
        String mail_address = (String)request.getParameter("mail_address");

        //ユーザーIDとメールアドレスがnullでない、または空でない時
        if(user_id != null && mail_address != null) {
            //EntityManager em = DBUtil.createEntityManager();
            //System.out.print(user_id);
            //System.out.print(mail_address );

            //セッションスコープに保持。→リクエストスコープに修正する必要があるかも。
            request.getSession().setAttribute("user_id", user_id);
            request.getSession().setAttribute("mail_address",  mail_address);
            Integer id = checkIdentification(user_id, mail_address);

            //response.sendRedirect(request.getContextPath() + "/");

            if (id == (-1)){
                request.getSession().setAttribute("flush", "[user_id] または [mail_address]が間違っています。");
                response.sendRedirect(request.getContextPath() + "/");

            } else {
                //セッションスコープに保存
                request.getSession().setAttribute("id", id);
                //response.sendRedirect(request.getContextPath() + "/");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/password_reset.jsp");
                rd.forward(request, response);
            }
        }
    }


    // ユーザーIDとメールアドレスが一致するidを取得する
    private static Integer checkIdentification(String user_id, String mail_address) {
        EntityManager em = DBUtil.createEntityManager();
        Integer get_id;
        try {
                get_id = (Integer)em.createNamedQuery("checkIdentificationGetId", Integer.class)
                    .setParameter("user_id", user_id)
                    .setParameter("mail_address", mail_address)
                    .getSingleResult();
            em.close();
        } catch (NoResultException e) {
            return -1;
        } catch (NonUniqueResultException e){
            return -1;
        }
        return get_id;

    }
}
