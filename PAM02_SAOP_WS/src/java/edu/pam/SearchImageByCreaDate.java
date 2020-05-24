/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author root
 */
@WebService(serviceName = "SearchImageByCreaDate")
public class SearchImageByCreaDate {

    private Connection connection = null;
    private PreparedStatement statement = null;
  

    /**
     * Web service operation
     * @param creaDate
     * @return 
     */
    @WebMethod(operationName = "doSearchByCreaDate")
    public List<Image> doSearchByCreaDate(@WebParam(name = "creaDate") String creaDate) {
        
        List<Image> all_img = new ArrayList<Image>();
        String query = null;
        try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2_soap","pr2","pr2");
        
        query = "select * from image where creation_date = '"+creaDate+"'";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        while(rs.next()) {
            all_img.add(new Image(rs.getString("title"), rs.getString("description"), 
                        rs.getString("keywords"),rs.getString("author"),rs.getString("creation_date"), 
                        rs.getString("storage_date"), rs.getString("filename"))
                );
        }
        return all_img;
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
