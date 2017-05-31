/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Vlucht;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.ForEach;


public class DAVlucht {
        private Connection connection = null;

public DAVlucht (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    
    public ArrayList<Vlucht> InkomendeVluchten(int LuchthavenID){
              
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
                
        try {
            statement = connection.prepareStatement("select * from vlucht \n" +
                                "inner join luchthaven on vlucht.aankomstluchthaven_ID = luchthaven.ID where aankomstluchthaven_ID = ?");
            statement.setInt(1, LuchthavenID);
            set = statement.executeQuery();
            
             while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Luchthaven lh = new Luchthaven();
                lh.setId(set.getInt("aankomstluchthaven_id"));
                lh.setNaam(set.getString("naam"));
                V.setLuchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh.setId(set.getInt("vertrekluchthaven_id"));
                lh.setNaam(set.getString("naam"));
                V.setLuchthaven(lh);
                Lijst.add(V);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return Lijst;
    }
     public ArrayList<Vlucht> UitgaandeVluchten(int LuchthavenID){
              
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
                
        try {
            statement = connection.prepareStatement("select * from vlucht \n" +
                                "inner join luchthaven on vlucht.vertrekluchthaven_id = luchthaven.ID where VERTREKLUCHTHAVEN_ID = ? and vlucht.vertrektijd >= current_date");
            statement.setInt(1, LuchthavenID);
            set = statement.executeQuery();
          
             while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Lijst.add(V);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return Lijst;
    }
    
     
    public ArrayList<Vlucht> VluchtOpCode(String code){
              
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        
         try {
                statement = connection.prepareStatement("select * from vlucht where id = ?");
               statement.setString(1,code);
               set = statement.executeQuery();
               
               while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Lijst.add(V);
               }
         } catch (Exception e) {
             System.out.println(e);
         }
         return Lijst;
     }
   
     public ArrayList<Vlucht> VluchtOpDatum(Timestamp date){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try{
             statement = connection.prepareStatement("Select * from vlucht where vertrektijd = ?");
             statement.setTimestamp(1, date);
             set = statement.executeQuery();
              while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Lijst.add(V);
               }
             
         }catch (Exception e){
             
         }
         return Lijst;
         
     }
     
     public ArrayList<Vlucht> VluchtOpBestemming(int aankomstluchthaven_id){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("select * from vlucht where aankomstLuchthaven_id = ?");
             statement.setInt(1, aankomstluchthaven_id);
             set = statement.executeQuery();
             while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Lijst.add(V);
               }
             
             
             
         } catch (Exception e) {
         }
         
         
         return  Lijst;
         
     }
     
     public ArrayList<Vlucht> VluchtOpLuchtvaartmaatschappij(int maatschappij_id){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
            try {
                statement = connection.prepareStatement("select * from vlucht join VLIEGTUIG on "
                                    +"vlucht.VLIEGTUIG_ID = VLIEGTUIG.id where VLIEGTUIG.LUCHTVAARTMAATSCHAPPIJ_ID = ?");
                
               statement.setInt(1, maatschappij_id);
               set = statement.executeQuery();
                while(set.next()) {
                V = new Vlucht();
                V.setId(set.getInt("id"));
                V.setCode(set.getString("code"));
                V.setVertrektijd(set.getTimestamp("vertrektijd"));
                V.setAankomsttijd(set.getTimestamp("AANKOMSTTIJD"));
                V.setVliegtuig_id(set.getInt("vliegtuig_id"));
                V.setVertrekluchthaven_id(set.getInt("vertrekLuchthaven_id"));
                V.setAankomstluchthaven_id(set.getInt("aankomstluchthaven_id"));
                Lijst.add(V);
               } 
         } catch (Exception e) {
         }
         return Lijst;
     }
    

}

