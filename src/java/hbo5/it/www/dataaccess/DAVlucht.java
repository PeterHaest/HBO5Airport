/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Crew;
import hbo5.it.www.beans.Passagier;
import hbo5.it.www.beans.Persoon;
import hbo5.it.www.beans.Vlucht;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



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
    
    public ArrayList<String> Vlucht_ids(){
        ArrayList<String> Lijst = new ArrayList<>();
         PreparedStatement statement = null;
        ResultSet set = null;
        
        try {
            statement = connection.prepareStatement("select code from vlucht");
            set = statement.executeQuery();
            while (set.next()) {                
                Lijst.add(set.getString(1));
            }
        } catch (Exception e) {
        }
        return Lijst;
        
        
        
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
    
     
     public Vlucht VluchtOpCode(String code){
              
        
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        
         try {
                statement = connection.prepareStatement("select * from vlucht where code = ?");
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
           
               }
               connection.close();
               statement.close();
         } catch (Exception e) {
         }
         
         return V;
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
    public ArrayList<Crew> Crew_per_vlucht(String code){
         PreparedStatement statement = null;
        ResultSet set = null;
        
        ArrayList<Crew> Lijst = new ArrayList<>();
        try {
            StringBuilder SQL = new StringBuilder();
            SQL.append("select v.code, vb.taak, p.VOORNAAM, p.FAMILIENAAM, lv.NAAM from vlucht v ");
            SQL.append("inner join vluchtbemanning vb on v.id = vb.VLUCHT_ID ");
            SQL.append("inner join bemanningslid b  on vb.BEMANNINGSLID_ID = b.id ");
            SQL.append("inner join persoon p on p.id = b.PERSOON_ID ");
            SQL.append("inner join LUCHTVAARTMAATSCHAPPIJ lv on lv.ID = b.LUCHTVAARTMAATSCHAPPIJ_ID ");
            SQL.append("where v.code = ?");
            
            
            statement = connection.prepareStatement(SQL.toString());
            statement.setString(1, code);
            set = statement.executeQuery();
            while (set.next()) {                
                Crew C = new Crew();
                C.setCode(set.getString(1));
                C.setFunctie(set.getString(2));
                C.setNaam(set.getString(3));
                C.setFamilienaam(set.getString(4));
                C.setMaatschappij(set.getString(5));
                Lijst.add(C);
            }
        } catch (Exception e) {
        }
        return Lijst;
    }
 public ArrayList<Vlucht> Vluchten(){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try{
             statement = connection.prepareStatement("Select * from vlucht");
           
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
 
 public Map<Integer,Persoon> pervlucht(Integer code, ArrayList<Passagier> lijst, DAPersoon persoon){
        
        Map<Integer,Persoon> mMap = new HashMap<>();
        for (Passagier item : lijst ) {
            if (item.getVlucht_id() == code) {
                Persoon p = persoon.Get_persoon_by_id(item.getPersoon_id());
                mMap.put(item.getId(), p );
            }
        }
        return mMap;
    }
     public Integer vluchtID(String code, ArrayList<Vlucht> lijst){
        Integer out= null;
        for (Vlucht vlucht : lijst) {
            if (code.equals(vlucht.getCode())) {
                out = vlucht.getId();
            }
        }
        return out;
        
    }
     
     
     
     
 
}

