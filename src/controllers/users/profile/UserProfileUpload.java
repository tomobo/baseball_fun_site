package controllers.users.profile;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/image/upload")
@MultipartConfig(location="C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded",
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 *  1024 *  5,
    maxRequestSize = 1024 *  1024 *  5 *  5)
public class UserProfileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String file_name = this.getFileName(part);
        //part.write(getServletContext().getRealPath("/image/uploaded") + File.separator + file_name);
        //System.out.println(getServletContext().getRealPath("/image/uploaded") + File.separator + file_name);
        part.write("C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded" + File.separator + file_name);
        System.out.println("C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded" + File.separator + file_name);
        request.getSession().setAttribute("profile_image_name", "C:/pleiades/workspace/baseball_fun_site/WebContent/image/uploaded" + File.separator + file_name);
        System.out.println(request.getSession().getAttribute("profile_image_name"));
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/profiles/new_profile.jsp");
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