/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Leasemaatschappij;
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
public class DALeasemaatschappij {
        private Connection connection = null;

public DALeasemaatschappij(String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    
    
      PreparedStatement statement = null;
        ResultSet set = null;
        Leasemaatschappij L;
    
    public ArrayList<Leasemaatschappij> get_Leasemaatschappij(){          
            ArrayList<Leasemaatschappij> lijst = new ArrayList<>();
        try {
            
            statement = connection.prepareStatement("select * from leasemaatschappij");
            set = statement.executeQuery();

            
            
            while (set.next()) {
                L = new  Leasemaatschappij();
                L.setId(set.getInt("id"));
                L.setNaam(set.getString("naam"));
                lijst.add(L);
            }
        } catch (Exception e) {
        }
 return lijst;
    }
    
    public ArrayList<String> get_leaseNamen(){
        ArrayList<Leasemaatschappij> lijstLeasemaatschappij = new ArrayList<>();
        ArrayList<String> lijst = new ArrayList<>();
        for (Leasemaatschappij leasemaatschappij : lijstLeasemaatschappij) {
            lijst.add(leasemaatschappij.getNaam());
        }
        return lijst;
    }
    
    
    

}
