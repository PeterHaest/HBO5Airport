/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Bemanningslid;
import hbo5.it.www.beans.Persoon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author c1040604
 */
public class DABemanningslid {
        private Connection connection = null;

public DABemanningslid (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    Connection conn;
    
    PreparedStatement statement;
    ResultSet set;
    
    
    public Map<Integer,ArrayList<String>> getAll(){
        Map<Integer,ArrayList<String>> Nmap = new HashMap<>();
        
        StringBuilder b = new StringBuilder();
        b.append("select b.id , lm.NAAM, p.VOORNAAM, p.FAMILIENAAM, f.NAAM, f.OMSCHRIJVING from BEMANNINGSLID b " );
        b.append("inner join LUCHTVAARTMAATSCHAPPIJ lm on lm.ID = b.LUCHTVAARTMAATSCHAPPIJ_ID ");
        b.append("inner join PERSOON p on p.id = b.PERSOON_ID ");
        b.append("inner join functie f on b.FUNCTIE_ID = f.ID");

        try {
            statement = conn.prepareStatement(b.toString());
            set = statement.executeQuery();
            while (set.next()) {
                        ArrayList<String> lijst = new ArrayList<>();
                 lijst.add(set.getString(2));
                 lijst.add(set.getString(3));
                 lijst.add(set.getString(4));
                 lijst.add(set.getString(5));
                 lijst.add(set.getString(6));
                 Nmap.put(set.getInt(1), lijst);
            }
        } catch (Exception e) {
        }
        return Nmap;
    }
    public Bemanningslid getById(Integer id){
         Bemanningslid b = new Bemanningslid();
         
         StringBuilder sb = new StringBuilder();
         sb.append("select * from bemanningslid where id = ");
         sb.append(id);
         
         
         
        try {
            statement = conn.prepareStatement(sb.toString());

            set = statement.executeQuery();
            if (set.next()) {
               
                b.setId(id);
                b.setPersoon_id(set.getInt(3));
                b.setLuchtvaartmaatschappij_id(set.getInt(2));
                b.setFunctie_id(set.getInt(4));
            }
        } catch (Exception e) {
        }
        return b;
    }
    public String getFunctiebyid(Integer id){
        String res = "";
         try {
            statement = conn.prepareStatement("select naam from functie where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();
            if (set.next()) {
               res = set.getString(1);
            }
        } catch (Exception e) {
        }
         return res;
    }
   
}
