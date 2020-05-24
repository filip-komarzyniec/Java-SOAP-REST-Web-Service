/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "ListImage", urlPatterns = {"/ListImage"})
public class ListImage extends HttpServlet {

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
            out.println("<title>Servlet ListImage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListImage at " + request.getContextPath() + "</h1>");
            
            List<edu.pam.Image> all_img = doListImg();
        
            for (edu.pam.Image item : all_img) {
                out.println("title: "+item.getTitle()+"<br>");
                out.println("description: "+item.getDescription()+"<br>");
                out.println("keywords: "+item.getKeywords()+"<br>");
                out.println("author: "+item.getAuthor()+"<br>");
                out.println("creation date: "+item.getCreationDate()+"<br>");
                out.println("storage date: "+item.getStorageDate()+"<br>");
                out.println("filename: "+item.getFilename()+"<br>");
                out.println("<br>");
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

    private static java.util.List<edu.pam.Image> doListImg() {
        edu.pam.ListImage_Service service = new edu.pam.ListImage_Service();
        edu.pam.ListImage port = service.getListImagePort();
        return port.doListImg();
    }

}
