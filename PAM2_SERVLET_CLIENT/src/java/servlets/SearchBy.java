/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import edu.pam.Image;
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
@WebServlet(name = "SearchBy", urlPatterns = {"/SearchBy"})
public class SearchBy extends HttpServlet {

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
            out.println("<title>Servlet SearchByID</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchByID at " + request.getContextPath() + "</h1>");
            
            out.println("Searching by ID<br>");
            edu.pam.Image id_img_search = doSearchById(4);
            out.println("title: "+ id_img_search.getTitle()+"<br>");
            out.println("description: "+ id_img_search.getDescription()+"<br>");
            out.println("keywords: "+ id_img_search.getKeywords()+"<br>");
            out.println("author: "+ id_img_search.getAuthor()+"<br>");
            out.println("creation date: "+ id_img_search.getCreationDate()+"<br>");
            out.println("storage date: "+ id_img_search.getStorageDate()+"<br>");
            out.println("filename: "+ id_img_search.getFilename()+"<br>");
            out.println("<br>");
            
            out.println("Searching by creation date<br>");
            List<edu.pam.Image> creation_list = doSearchByCreaDate("dzis");
        
            for (edu.pam.Image item : creation_list) {
                out.println("title: "+item.getTitle()+"<br>");
                out.println("description: "+item.getDescription()+"<br>");
                out.println("keywords: "+item.getKeywords()+"<br>");
                out.println("author: "+item.getAuthor()+"<br>");
                out.println("creation date: "+item.getCreationDate()+"<br>");
                out.println("storage date: "+item.getStorageDate()+"<br>");
                out.println("filename: "+item.getFilename()+"<br>");
                out.println("<br>");
            }
            
            out.println("Searching by title<br>");
            
            List<edu.pam.Image> title_list = doSearchByTitle("tytul5");
            for (edu.pam.Image item : title_list) {
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

    private static Image doSearchById(int id) {
        edu.pam.SearchImageById_Service service = new edu.pam.SearchImageById_Service();
        edu.pam.SearchImageById port = service.getSearchImageByIdPort();
        return port.doSearchById(id);
    }

    private static java.util.List<edu.pam.Image> doSearchByCreaDate(java.lang.String creaDate) {
        edu.pam.SearchImageByCreaDate_Service service = new edu.pam.SearchImageByCreaDate_Service();
        edu.pam.SearchImageByCreaDate port = service.getSearchImageByCreaDatePort();
        return port.doSearchByCreaDate(creaDate);
    }

    private static java.util.List<edu.pam.Image> doSearchByTitle(java.lang.String title) {
        edu.pam.SearchImageByTitle_Service service = new edu.pam.SearchImageByTitle_Service();
        edu.pam.SearchImageByTitle port = service.getSearchImageByTitlePort();
        return port.doSearchByTitle(title);
    }

}
