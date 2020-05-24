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
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author root
 */
@WebService(serviceName = "SearchImageById")
public class SearchImageById {

    private Connection connection = null;
    private PreparedStatement statement = null;
  

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "doSearchById")
    public Image doSearchById(@WebParam(name = "id") int id) {
        String query = null;
        Image img = null;
        try {
            // CONNECTING TO DB
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_soap","pr2","pr2");
            
            query = "select * from image where ID ="+String.valueOf(id);
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                img = new Image(rs.getString("title"), rs.getString("description"), 
                        rs.getString("keywords"),rs.getString("author"),rs.getString("creation_date"), 
                        rs.getString("storage_date"), rs.getString("filename"));
            }
            
            return img;
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
