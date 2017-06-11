/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Crew;
import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Luchtvaartmaatschappij;
import hbo5.it.www.beans.Passagier;
import hbo5.it.www.beans.Persoon;
import hbo5.it.www.beans.Vliegtuig;
import hbo5.it.www.beans.Vliegtuigtype;
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
            statement = connection.prepareStatement("select * from vlucht inner join luchthaven on vlucht.aankomstluchthaven_ID = luchthaven.ID inner join luchthaven lh2 on vlucht.VERTREKLUCHTHAVEN_ID = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where aankomstluchthaven_ID = ?");
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
                lh.setNaam(set.getNString(9));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(12));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
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
                                "inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where vertrekLuchthaven_id = ? and vlucht.vertrektijd <= current_date");
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
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
                statement = connection.prepareStatement("select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where lower(code) like ?");
               statement.setString(1, "%"+ code.toLowerCase() +"%");
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
                Lijst.add(V);
               }
         } catch (Exception e) {
         }
         return Lijst;
     }
   
     public ArrayList<Vlucht> VluchtOpDatum(String date){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try{
             statement = connection.prepareStatement("Select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where vertrektijd >= to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss')");
             statement.setString(1, date);
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
                Lijst.add(V);
               }
             
         }catch (Exception e){
             
         }
         return Lijst;
         
     }
     
     public ArrayList<Vlucht> VluchtOpBestemming(String aankomstluchthaven){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where lower(lh2.naam) like ?");
             statement.setString(1, "%"+aankomstluchthaven.toLowerCase()+"%");
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
                Lijst.add(V);
               }
             
             
             
         } catch (Exception e) {
         }
         
         
         return  Lijst;
         
     }
     
     public ArrayList<Vlucht> VluchtOpLuchtvaartmaatschappij(String maatschappij){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
            try {
                statement = connection.prepareStatement("select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID inner join luchtvaartmaatschappij on VLIEGTUIG.LUCHTVAARTMAATSCHAPPIJ_ID = luchtvaartmaatschappij.id where lower(luchtvaartmaatschappij.naam) like ?");
                
               statement.setString(1, "%" + maatschappij.toLowerCase() + "%");
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype); 
                Lijst.add(V);
               } 
         } catch (Exception e) {
         }
         return Lijst;
     }
     
    public Vlucht ZoekDetails(int id){
              
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        
         try {
                statement = connection.prepareStatement("select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID inner join luchtvaartmaatschappij on vliegtuig.LUCHTVAARTMAATSCHAPPIJ_ID = luchtvaartmaatschappij.ID where vlucht.id = ?");
               statement.setInt(1,id);
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
                lh.setNaam(set.getNString(12));
                V.setAankomstluchthaven(lh);
                Luchthaven lh2 = new Luchthaven();
                lh2.setId(set.getInt("vertrekLuchthaven_id"));
                lh2.setNaam(set.getNString(9));
                V.setVertrekluchthaven(lh2);
                Vliegtuig vt = new Vliegtuig();
                vt.setId(set.getInt("vliegtuig_id"));
                vt.setVliegtuigtype_id(set.getInt("vliegtuigtype_id"));
                vt.setLuchtvaartmaatschappij_id(set.getInt("luchtvaartmaatschappij_id"));
                V.setVliegtuig(vt);
                Vliegtuigtype vliegtype = new Vliegtuigtype();
                vliegtype.setId(set.getInt("vliegtuigtype_id"));
                vliegtype.setNaam(set.getNString(19));
                V.setVliegtype(vliegtype);
                Luchtvaartmaatschappij lvm = new Luchtvaartmaatschappij();
                lvm.setId(set.getInt("luchtvaartmaatschappij_id"));
                lvm.setNaam(set.getNString(21));
                V.setLuchtvaarmaatschappij(lvm);   
               }
         } catch (Exception e) {
         }
         return V;
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

