/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Passagier;
import hbo5.it.www.beans.Persoon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author c1040604
 */
public class DAPassagier {
        private Connection connection = null;

public DAPassagier (String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    Connection conn;
    PreparedStatement statement = null;
    ResultSet set = null;
    
    public ArrayList<Passagier> getPassagiers (){
        ArrayList<Passagier> Lijst = new ArrayList<>();
        
        try {
              statement = connection.prepareStatement("select * from passagier");
              set = statement.executeQuery();
                while (set.next()) {            
                    Passagier P = new Passagier();
                    P.setId(set.getInt(1));
                    P.setIngeschreven(set.getInt(2));
                    P.setIngecheckt(set.getInt(3));
                    P.setKlasse_id(set.getInt(4));
                    P.setPlaats(set.getString(5));
                    P.setVlucht_id(set.getInt(6));
                    P.setPersoon_id(set.getInt(7));
                    Lijst.add(P);
                }
        } catch (Exception e) {
        }
      return Lijst;
        
    }
    public ArrayList<Passagier> Passagiers_per_vlucht(int vlucht_id){
         
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
    
    public ArrayList<Passagier> Passagiers_per_luchthaven(int luchthaven_id){
         
        ArrayList<Passagier> Lijst = new ArrayList<>();
        Passagier P = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("SELECT * FROM PASSAGIER inner join persoon on persoon_id = persoon.id inner join vlucht on vlucht_id = vlucht.id WHERE vlucht.aankomstluchthaven_id = ?");
             statement.setInt(1, luchthaven_id);
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
    
    public ArrayList<Passagier> Passagiers_per_dag(String date){
         
        ArrayList<Passagier> Lijst = new ArrayList<>();
        Passagier P = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("SELECT * FROM PASSAGIER inner join persoon on persoon_id = persoon.id inner join vlucht on vlucht_id = vlucht.id WHERE vlucht.vertrektijd >= to_timestamp(?, 'yyyy-mm-dd')");
             statement.setString(1, date);
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
    public ArrayList<Passagier> Passagiers_per_luchtvaartmaatschappij(int luchtvaartmaatschappij_id){
         
        ArrayList<Passagier> Lijst = new ArrayList<>();
        Passagier P = null;
        PreparedStatement statement = null;
        ResultSet set = null;
         
         try {
             statement = connection.prepareStatement("SELECT * FROM PASSAGIER inner join persoon on persoon_id = persoon.id inner join vlucht on vlucht_id = vlucht.id inner join vliegtuig on vliegtuig.id = vlucht.VLIEGTUIG_ID inner join luchtvaartmaatschappij on luchtvaartmaatschappij.id = vliegtuig.LUCHTVAARTMAATSCHAPPIJ_ID WHERE vliegtuig.LUCHTVAARTMAATSCHAPPIJ_ID = ?");
             statement.setInt(1, luchtvaartmaatschappij_id);
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
