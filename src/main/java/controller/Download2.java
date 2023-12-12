package controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.bean.PDF2WORD;

@WebServlet(urlPatterns = { "/download2" })
public class Download2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        try {
            // Load status from id
            Integer fileID = Integer.parseInt(request.getParameter("id"));
            model.bo.Data2 boData2 = new model.bo.Data2();
            PDF2WORD word = boData2.getStatusByID(fileID);

            // If owner and logged in account is incorrent, return back to login
            Object user = request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("index");
                return;
            }
            String user2 = user.toString().toLowerCase();
            String userWord = word.getUser();
            if (userWord == null || !userWord.toLowerCase().equals(user2)) {
                response.sendRedirect("index");
                return;
            }

            // If not successful, deny download
            if (word.getResult() != 2) {
                response.sendRedirect("dashboard");
                return; 
            }
            

            // https://stackjava.com/jsp-servlet/jsp-servlet-download-file.html
            Path path = Paths.get(word.getTargetPath());
            byte[] data2 = Files.readAllBytes(path);
            response.setContentType("application/msword; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(word.getSourceName(), "UTF-8") + ".docx\"");
            response.setContentLength(data2.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data2));

            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
