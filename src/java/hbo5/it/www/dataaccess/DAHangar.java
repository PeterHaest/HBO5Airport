/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Hangar;
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
public class DAHangar {
        private Connection connection = null;

public DAHangar (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
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
    public ArrayList<Hangar> Get_Hangars(){
        ArrayList<Hangar> lijst = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from Hangar");
            set = statement.executeQuery();
            while(set.next()){
                Hangar H  = new Hangar();
                H.setId(set.getInt(1));
                H.setNaam(set.getString(2));
                lijst.add(H);
            }
        } catch (Exception e) {
        }
        return lijst;
    }
    public ArrayList<String> get_namen(ArrayList<Hangar> lijst){
        ArrayList<String> out = new ArrayList<>();
        for (Hangar hangar : lijst) {
            out.add(hangar.getNaam());
        }
        return out;
    }
    public Hangar get_byName(ArrayList<Hangar> lijst, String Naam){
        Hangar H = new Hangar();
        for (Hangar hangar : lijst) {
            if (Naam.equals(hangar.getNaam())) {
                H = hangar;
            }
        }
        return H;
    }
    

}
