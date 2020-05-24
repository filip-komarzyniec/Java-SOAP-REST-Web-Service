/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of server.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();

        return "<h1>Lab Session 3: REST Web Services</h1>"
             + "<h4>Filip Komarzyniec, Milan Ścliślewski</h4>";
    }
    
    /**
    * GET method to list images 
    * @return
    */
    @Path("list/")
    @GET    
    @Produces(MediaType.TEXT_HTML)
    public String listImages () {        
        Connection connection = null;
        //List<Image> all_img = new ArrayList<>();
        // document beginning
        String response = "<html><body><h1>List of all images</h1><ul>";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            String query = "select * from image order by ID ASC";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                response += "<li>" +rs.getString("title") + "</li>";
                response += "<li>" +rs.getString("description")+ "</li>";
                response += "<li>" +rs.getString("keywords")+ "</li>";
                response += "<li>" +rs.getString("author")+ "</li>";
                response += "<li>" +rs.getString("creation_date")+ "</li><br>";
            }
            // document end 
            response += "</ul></body></html>";
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        } finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return response;
    }  
    /**
    * GET method to search images 
    * @param id
    * @return
    */
    @Path("searchID/")
    @GET    
    @Produces(MediaType.TEXT_HTML)
    public String searchByID (@QueryParam("id") int id) {     
        Connection connection = null;
        String query, response="";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            query = "select * from image where ID ="+String.valueOf(id);
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while  (rs.next()) {
                response += "<li>" +rs.getString("title") + "</li>";
                response += "<li>" +rs.getString("description")+ "</li>";
                response += "<li>" +rs.getString("keywords")+ "</li>";
                response += "<li>" +rs.getString("author")+ "</li>";
                response += "<li>" +rs.getString("creation_date")+ "</li>";
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        } finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "<html><body><h2>Image searched by ID</h2>"
             + "<ul>"+response+"</ul></body></html>";
    }
     /**
    * GET method to search images 
    * @param title    
    * @return
    */
    @Path("searchTitle/")
    @GET    
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle (@QueryParam("title") String title) {     
        Connection connection = null;
        String query, response="";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            query = "select * from image where title = '"+title+"'";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while  (rs.next()) {
                response += "<li>" +rs.getString("title") + "</li>";
                response += "<li>" +rs.getString("description")+ "</li>";
                response += "<li>" +rs.getString("keywords")+ "</li>";
                response += "<li>" +rs.getString("author")+ "</li>";
                response += "<li>" +rs.getString("creation_date")+ "</li>";
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        } finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "<html><body><h2>Image searched by TITLE</h2>"
             + "<ul>"+response+"</ul></body></html>";
    } 
    /**
    * GET method to search images by creation date
     * @param date
    * @return
    */
    @Path("searchCreationDate/")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate (@QueryParam("date") String date) {
        Connection connection = null;
        String query, response="";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            query = "select * from image where creation_date='"+date+"'";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while  (rs.next()) {
                response += "<li>" +rs.getString("title") + "</li>";
                response += "<li>" +rs.getString("description")+ "</li>";
                response += "<li>" +rs.getString("keywords")+ "</li>";
                response += "<li>" +rs.getString("author")+ "</li>";
                response += "<li>" +rs.getString("creation_date")+ "</li>";
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        } finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "<html><body><h2>Image searched by CREATION DATE</h2>"
             + "<ul>"+response+"</ul></body></html>";
        
    }

    /**
     *
     * @param criteria
     * @return
     */
    @Path("searchMixed/")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchMixed (@Context UriInfo ui) { 
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        Connection connection = null;
        String query, response ="";
        String scndCriteria = (String) queryParams.keySet().toArray()[0];
        //return "<h2>"+queParams+"</h2>";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            if (queryParams.get("logic").get(0).equals("AND")) {
                query = "select * from image where title = '"+queryParams.get("title").get(0)+"' and "+
                      scndCriteria + "='" + queryParams.get(scndCriteria).get(0)+ "'";
            } else {
                query = "select * from image where title = '" + queryParams.get("title").get(0)+ "' or "+
                      scndCriteria + "='" + queryParams.get(scndCriteria).get(0)+ "'";
            }
            //query = "select * from image where title='"+
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while  (rs.next()) {
                response += "<li>" +rs.getString("title") + "</li>";
                response += "<li>" +rs.getString("description")+ "</li>";
                response += "<li>" +rs.getString("keywords")+ "</li>";
                response += "<li>" +rs.getString("author")+ "</li>";
                response += "<li>" +rs.getString("creation_date")+ "</li><br>";
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        } finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "<html><body><h2>Image searched by MIXED CRITERIA</h2>"
             + "<ul>"+response+"</ul>"+query+"</body></html>";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords     
    * @param author
    * @param creationDate 
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage (@FormParam("title") String title,
        @FormParam("description") String description,
        @FormParam("keywords") String keywords,
        @FormParam("author") String author,
        @FormParam("creation") String creationDate) {       
        
        Connection connection = null;
        PreparedStatement statement = null;
        String query, response = "";
        Integer id = 1;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_rest","pr2","pr2");
            
            // CHECKING IF DB ALREADY EXISTS    
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet exists = meta.getTables(null,null,"IMAGE",null);
            if (exists.next()) {    // ADDING ANOTHER ENTRIES TO DB
                query = "select max(ID) from image";
                statement = connection.prepareStatement(query);
                ResultSet rs_id = statement.executeQuery();
                rs_id.next();
                id = rs_id.getInt(1) + 1;
                response += "table IMAGE exists, adding next entry..." ;
                query = "insert into image values(?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2, title);
                statement.setString(3, description);
                statement.setString(4, keywords);
                statement.setString(5, author);
                statement.setString(6, creationDate);
                statement.executeUpdate();
            } else {
                // CREATING NEW TABLE IMAGE */
                response += "creating new table IMAGE, as one does not exist";
                query = "create table image (id int NOT NULL, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
                    + "varchar (256) NOT NULL, author varchar (256) NOT NULL, creation_date varchar (10) NOT NULL, "
                    + "primary key (id))";
                statement = connection.prepareStatement(query);
                statement.executeUpdate(); 
                query = "insert into image values(?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2, title);
                statement.setString(3, description);
                statement.setString(4, keywords);
                statement.setString(5, author);
                statement.setString(6, creationDate);
                statement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            return "<h2>ERROR OCCURED:</h2>"
                 + "<p>"+e.getMessage() +"</p>";
        }finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return "<html><body> <h2>REGISTERING IMAGE</h2> "
                + "<a class=\"anchor\" href=\"/RestPam\">Go back to menu</a>"
                + "</body><p>"+response+"</p></html>";
    }               
}
