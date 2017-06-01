/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import com.sun.xml.internal.ws.policy.subject.WsdlBindingSubject;
import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Persoon;
import hbo5.it.www.beans.Vlucht;
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




public class DALuchthaven {
       private Connection connection = null;

       
      
       
public DALuchthaven (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    
    public ArrayList<String> Get_naam_luchtHaven(){      
        ArrayList<String> Lijst= new ArrayList<>();
 ArrayList<Luchthaven> lijstLuchthavens = getLuchthavens();
        
 
        for (Luchthaven haven : lijstLuchthavens) {
            Lijst.add(haven.getNaam());
        }
 
            return  Lijst;
        }
    
    
    
    
    public ArrayList<Luchthaven> getLuchthavens(){
          PreparedStatement statement1 = null;
        ResultSet set1 = null;
        ArrayList<Luchthaven> lijstLuchthavens= new ArrayList<>();
        try {
            
              statement1 = connection.prepareStatement("Select * from luchthaven");
             set1 = statement1.executeQuery();
             while (set1.next()) {
                 Luchthaven L = new Luchthaven();
                 L.setId(set1.getInt("id"));
                 L.setNaam(set1.getString("naam"));
                 L.setStad(set1.getString("stad"));
                 
                 
                 
                 lijstLuchthavens.add(L);
             }
            
            
        } catch (Exception e) {
            
           String str = " fd";
            
            
        }
        return  lijstLuchthavens;
    }
        
       public Luchthaven getLuchthaven(String naam ){
        PreparedStatement statement1 = null;
        ResultSet set1 = null;
        Luchthaven L = new Luchthaven();
        try {
            
             statement1 = connection.prepareStatement("Select * from luchthaven where naam = ?");
             statement1.setString(1,naam); 
             set1 = statement1.executeQuery();
             if (set1.next()) {
                 
                 L.setId(set1.getInt("id"));
                 L.setNaam(set1.getString("naam"));
                 L.setStad(set1.getString("stad"));
             }
        } catch (Exception e) {
           String str = " fd";
        }
        return L;
       } 
        
        
        
        
    }
    
    
    
    
    
    
    
    
 

