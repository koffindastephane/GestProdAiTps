package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managedBeans.MB_Global;


public class FichiersPhotosServlet extends HttpServlet {

    @Inject
    MB_Global mbG;
    
    protected void myDoFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String dossierAInclureDansPath = mbG.getDossierExterne();
        String filename;
        File file;
        
        filename = request.getPathInfo().substring(1);
        file = new File(dossierAInclureDansPath, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        myDoFunction(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        myDoFunction(request, response);
    }
}
