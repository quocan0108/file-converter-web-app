package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import model.bean.PDF2WORD;
import model.bean.WORD2PDF;
import model.bo.Data2;

//https://openplanning.net/11069/upload-va-download-file-luu-tru-tren-o-cung-voi-java-servlet
@WebServlet(urlPatterns = { "/pdf-to-word" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50) // 50MB 
public class PDFtoWord extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("index");
            return;
        }
        
        String urlTarget = "/pdftoword.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(urlTarget);
		rd.forward(request, response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        try {
            request.setCharacterEncoding("UTF-8");
            Part part = request.getPart("file");
            if (part == null)
                throw new Exception("Something went wrong.");

            // If file is invaild name, return.
            if (part.getName() == null || part.getName().isEmpty())
                throw new Exception("File upload is invaild name.");

            // Create new item.
            PDF2WORD word = new PDF2WORD();
            // User
            word.setUser(request.getSession().getAttribute("user").toString());
            // File original name
            word.setSourceName(Paths.get(part.getSubmittedFileName()).getFileName().toString());
            String fileNameTemp = model.bo.Tools.GenerateString(32);
            // File random source name
            word.setSourcePath(config.Config.dirTemp + "\\" + fileNameTemp + ".pdf");
            // File random name after convert
            word.setTargetPath(config.Config.dirTemp + "\\" + fileNameTemp + ".docx");
            // Set date start
            word.setDateStart(new Timestamp(System.currentTimeMillis()));
            // Set status to 0 - pending
            word.setResult(0);

            // Write to server disk
            part.write(word.getSourcePath());
            // New record to database
            Data2 data2 = new Data2();
            data2.addStatus(word);

            controller.BackgroundWork2.addToQueue(word);

            // Return to dashboard
            response.sendRedirect("dashboard");
        }
        catch (Exception ex) {
            System.out.println(ex);
            response.sendRedirect("pdf-to-word");
        }
    }
}
