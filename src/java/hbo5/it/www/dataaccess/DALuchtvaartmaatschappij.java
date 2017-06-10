/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Luchtvaartmaatschappij;
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
public class DALuchtvaartmaatschappij {
        private Connection connection = null;

public DALuchtvaartmaatschappij (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    
    public ArrayList<Luchtvaartmaatschappij> get_luchtvaartmaatschapijen(){
        ArrayList<Luchtvaartmaatschappij> lijst = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement("Select * from luchtvaartmaatschappij");
            set = statement.executeQuery();
            while (set.next()) {
                Luchtvaartmaatschappij L = new Luchtvaartmaatschappij();
                L.setId(set.getInt("id"));
                L.setNaam(set.getString("naam"));
                lijst.add(L);
            }
        } catch (Exception e) {
        }
        return lijst;
    }
    public Luchtvaartmaatschappij get_luchtvaartmaatschapijen_by_id(int luchtvaartmaatschappij_id){
        Luchtvaartmaatschappij L = new Luchtvaartmaatschappij();
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement("Select * from luchtvaartmaatschappij where luchtvaartmaatschappij.id = ?");
            statement.setInt(1, luchtvaartmaatschappij_id);
            set = statement.executeQuery();
            while (set.next()) {
                L.setId(set.getInt("id"));
                L.setNaam(set.getString("naam"));
                
            }
        } catch (Exception e) {
        }
        return L;
    }
    
    public ArrayList<String> Get_Names(){
        ArrayList<String> lijst = new ArrayList<>();
        ArrayList<Luchtvaartmaatschappij> lijstmaatschappij = get_luchtvaartmaatschapijen();
        for (Luchtvaartmaatschappij luchtvaartmaatschappij : lijstmaatschappij) {
            lijst.add(luchtvaartmaatschappij.getNaam());
        }
        return lijst;
    }
    public Luchtvaartmaatschappij getMaatschappij(String naam){
         PreparedStatement statement = null;
        ResultSet set = null;
        Luchtvaartmaatschappij L = new Luchtvaartmaatschappij();
        try {
            statement = connection.prepareStatement("select * from Luchtvaartmaatschappij where naam = ?");
            statement.setString(1, naam);
            set = statement.executeQuery();
            if (set.next()) {
               L.setId(set.getInt("id"));
               L.setNaam(set.getString("naam")); 
            }
        } catch (Exception e) {
        }
        return L;
    }
    
    
    

}
