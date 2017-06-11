/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Vliegtuigklasse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author c1040604
 */
public class DAVliegtuigklasse {
        private Connection connection = null;

public DAVliegtuigklasse (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    PreparedStatement statement;
    ResultSet set;
    
    
    
    
    
    public ArrayList< Vliegtuigklasse> getvliegtuigklasse(){
        ArrayList< Vliegtuigklasse> lijst = new ArrayList< Vliegtuigklasse>();
        try {
               statement = connection.prepareStatement("select * from vliegtuigklasse");
        set = statement.executeQuery();
            while (set.next()) {                
                Vliegtuigklasse V = new Vliegtuigklasse();
                V.setId(set.getInt(1));
                V.setNaam(set.getString(2));
                lijst.add(V);
            }
        } catch (Exception e) {
        }
        return lijst;
    }

}
