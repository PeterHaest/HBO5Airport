/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Vliegtuigtype;
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
public class DAVliegtuigtype {
        private Connection connection = null;

public DAVliegtuigtype (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
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
    Vliegtuigtype T;
    
    
    
    public ArrayList<Vliegtuigtype> Get_types(){
        ArrayList<Vliegtuigtype> lijst  = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        b.append("select * from vliegtuigtype ");
        
        try {
            statement = connection.prepareStatement(b.toString());
            set = statement.executeQuery();
            while (set.next()) {
                T = new Vliegtuigtype();
                T.setId(set.getInt(1));
                T.setNaam(set.getString(2));
                lijst.add(T);
            }
            
        } catch (Exception e) {
        }
        return lijst;
    }

}
