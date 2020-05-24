/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pam;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author root
 */
@WebService(serviceName = "RegisterImage")
public class RegisterImage {

   // public Connection connection = null;
   // public PreparedStatement statement = null;
    
    /**
     * Web service operation
     * @param img
     * @return 
     */
    @WebMethod(operationName = "doRegisterImg")
    public int doRegisterImg(@WebParam(name = "img") Image img) {
        Connection connection = null;
        PreparedStatement statement = null;
        System.out.println("autor "+img.getAuthor());
        String query = null;
        Integer id = 1;
        int j=0;
        try {
            j = 8;
            System.out.println("Elo, zaczynam");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_soap","pr2","pr2");
            j = 10;

            // CHECKING IF DB ALREADY EXISTS    
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet exists = meta.getTables(null,null,"IMAGE",null);
            if (exists.next()) {    // ADDING ANOTHER ENTRIES TO DB
                /*query = "select max(ID) from image";
                statement = connection.prepareStatement(query);
                ResultSet rs_id = statement.executeQuery();
                rs_id.next();
                id = rs_id.getInt(1) + 1;
                System.out.println("id "+id); */
                System.out.println("table IMAGE exists, adding next entry..." );
                query = "insert into image values(?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2, img.getTitle());
                statement.setString(3, img.getDescription());
                statement.setString(4, img.getKeywords());
                statement.setString(5, img.getAuthor());
                statement.setString(6, img.getCreationDate());
                statement.setString(7, img.getStorageDate());
                statement.setString(8, img.getFilename());   
                statement.executeUpdate();
                //statement.setQueryTimeout(30);  // set timeout to 30 sec.
                //request.setAttribute("id", id+1);
            }
            else { // CREATING NEW TABLE IMAGE */
                System.out.println("creating new table IMAGE, as one does not exist");
                query = "create table image (id int NOT NULL, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
                    + "varchar (256) NOT NULL, author varchar (255) NOT NULL, creation_date varchar (10) NOT NULL, storage_date varchar (10) NOT NULL, filename varchar (512) NOT NULL, "
                    + "primary key (id))";
                statement = connection.prepareStatement(query);
                statement.executeUpdate(); 
                query = "insert into image values(?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2, img.getTitle());
                statement.setString(3, img.getDescription());
                statement.setString(4, img.getKeywords());
                statement.setString(5, img.getAuthor());
                statement.setString(6, img.getCreationDate());
                statement.setString(7, img.getStorageDate());
                statement.setString(8, img.getFilename());   
                statement.executeUpdate();
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("database "+e.getClass());
        } catch (Exception e) {
            System.out.println("exception "+e.getMessage());
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();   
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return j;
    }
    
}
