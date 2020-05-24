/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "RegisterImg", urlPatterns = {"/RegisterImg"})
public class RegisterImg extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletClient at " + request.getContextPath() + "</h1>");
            
            try {
                Scanner input = new Scanner(System.in);
                edu.pam.Image img = new edu.pam.Image();

                String title = request.getParameter("title");
                img.setTitle(title);
                String description = request.getParameter("description");
                img.setDescription(description);
                String author = request.getParameter("author");
                img.setAuthor(author);
                String keywords = request.getParameter("keywords");
                img.setKeywords(keywords);
                String creationDate = request.getParameter("creationDate");
                img.setCreationDate(creationDate);
                String storageDate = request.getParameter("storageDate");
                img.setStorageDate(storageDate);
                String filename = request.getParameter("filename");
                img.setFilename(filename);
                
                out.println(doRegisterImg(img));
        
                
                
            } catch (Exception e) {
                out.println("Problem occured: "+e.getMessage());
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static int doRegisterImg(edu.pam.Image img) {
        edu.pam.RegisterImage_Service service = new edu.pam.RegisterImage_Service();
        edu.pam.RegisterImage port = service.getRegisterImagePort();
        return port.doRegisterImg(img);
    }

}
