/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
   
     public ArrayList<Vlucht> VluchtOpDatum(Timestamp date){
         
        ArrayList<Vlucht> Lijst = new ArrayList<>();
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try{
             statement = connection.prepareStatement("Select * from vlucht inner join luchthaven on vlucht.vertrekLuchthaven_id = luchthaven.ID inner join luchthaven lh2 on vlucht.aankomstluchthaven_id = lh2.ID inner join vliegtuig on vlucht.VLIEGTUIG_ID = vliegtuig.ID inner join vliegtuigtype on vliegtuig.VLIEGTUIGTYPE_ID = vliegtuigtype.ID where vertrektijd = ?");
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
        
    public ArrayList<Passagier> DetailsPassagiers(int vlucht_id){
         
        ArrayList<Passagier> Lijst = new ArrayList<>();
        Passagier P = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("SELECT * FROM PASSAGIER inner join persoon on persoon_id = persoon.id WHERE vlucht_id = ?");
             statement.setInt(1, vlucht_id);
             set = statement.executeQuery();
             while(set.next()) {
                P = new Passagier();
                P.setId(set.getInt("id"));
                P.setIngeschreven(set.getInt("ingeschreven"));
                P.setIngecheckt(set.getInt("ingecheckt"));
                P.setKlasse_id(set.getInt("klasse_id"));
                P.setPlaats(set.getString("plaats"));
                P.setVlucht_id(set.getInt("vlucht_id"));
                P.setPersoon_id(set.getInt("persoon_id"));
                Persoon pers = new Persoon();
                pers.setId(set.getInt("persoon_id"));
                pers.setVoornaam(set.getString("voornaam"));
                pers.setFamilienaam(set.getString("familienaam"));
                pers.setStraat(set.getString("straat"));
                pers.setHuisnr(set.getString("huisnr"));
                pers.setPostcode(set.getString("postcode"));
                pers.setWoonplaats(set.getString("woonplaats"));
                pers.setLand(set.getString("land"));
                pers.setGeboortedatum(set.getDate("geboortedatum"));
                pers.setLogin("");
                pers.setPaswoord("");
                P.setPersoon(pers);              
                Lijst.add(P);
               }
             
             
             
         } catch (Exception e) {
         }
         
         
         return  Lijst;
         
     }

}

