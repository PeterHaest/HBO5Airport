/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import hbo5.it.www.ZoekServlet;
import hbo5.it.www.beans.Persoon;
import hbo5.it.www.beans.Vlucht;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;


/**
 *
 * @author c1040604
 */
public class DAPersoon {
        private final Connection connection = null;

public DAPersoon(String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn  = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    

    
    
    public void Add_Persoon( int id,
    String voornaam, String achternaam, String Straat, String nmr,String code, String woonplaats,
            String land,Date geboorte, String login, String pas ){
         try {
            statement = conn.prepareStatement
                                ("insert into persoon (id,voornaam,familienaam,straat,huisnr,postcode,woonplaats,land,geboortedatum,login,paswoord) " +
                                "values (?,?,?,?,?,?,?,?,?,?,?); ");
            statement.setInt(1, id);
            statement.setString(2, voornaam);
            statement.setString(3, achternaam);
            statement.setString(4, Straat);
            statement.setString(5, nmr);
            statement.setString(6, code);
            statement.setString(7,woonplaats);
            statement.setString(8, land);
            statement.setDate(9, geboorte);
            statement.setString(10,login);
            statement.setString(11,pas);
                    
            
            
            set = statement.executeQuery(); 
            conn.commit();
        }
  catch (Exception e) {
    }
        
        
       
    }
        Connection conn;
        Persoon P = null;
        Vlucht V = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Integer res ;
        
        
        public int GetTopid(){
              try {
          statement = conn.prepareStatement("select max(ID)  from PERSOON");
          set = statement.executeQuery();
                  if (set.next()) {
                       res =  set.getInt(1)+1;  
                  }
         
        }
  catch (Exception e) {
    }
            return  res;
              
            
        }
        
        
public ArrayList<Persoon> GetPersonen(){
           ArrayList<Persoon> lijst = new ArrayList<>();
try {
     
            statement = conn.prepareStatement
                                ("select * from Persoon");
            set = statement.executeQuery(); 
            while (set.next()) {
                
                P = new Persoon();
                
                P.setFamilienaam(set.getString("FAMILIENAAM"));
                P.setGeboortedatum(set.getDate("GEBOORTEDATUM"));
                P.setHuisnr(set.getString("HUISNR"));
                P.setLand(set.getString("LAND"));
                P.setLogin(set.getString("LOGIN"));
                P.setPaswoord(set.getString("PASWOORD"));
                P.setPostcode(set.getString("postcode"));
                P.setStraat(set.getString("STRAAT"));
                P.setVoornaam(set.getString("VOORNAAM"));
                P.setWoonplaats(set.getString("WOONPLAATS"));
                P.setId(set.getInt("ID"));

                lijst.add(P);


                }
            }
  catch (Exception e) {
    }
                
        
        return lijst;
        
    }
   
        
        
public int CheckLogin(String Login, String Pass){
        try {
            statement = conn.prepareStatement
                                ("select * from Persoon where Login = ?");
            statement.setString(1, Login);
            set = statement.executeQuery(); 
            if (set.next()) {
                if( set.getString("PASWOORD").equals(Pass)){
                    return 1;

                }
            }
        }
  catch (Exception e) {
    }
                
        
        return 0;
        
    }

public Persoon GetPersoon(String Login){
     
        
        try {
            statement = conn.prepareStatement
                                ("select * from Persoon where Login = ?");
            statement.setString(1,Login);
            set = statement.executeQuery(); 
            if (set.next()) {
               
                    P = new Persoon();
                
                P.setFamilienaam(set.getString("FAMILIENAAM"));
                P.setGeboortedatum(set.getDate("GEBOORTEDATUM"));
                P.setHuisnr(set.getString("HUISNR"));
                P.setLand(set.getString("LAND"));
                P.setLogin(set.getString("LOGIN"));
                P.setPaswoord(set.getString("PASWOORD"));
                P.setPostcode(set.getString("postcode"));
                P.setStraat(set.getString("STRAAT"));
                P.setVoornaam(set.getString("VOORNAAM"));
                P.setWoonplaats(set.getString("WOONPLAATS"));
                P.setId(set.getInt("ID"));

                
            }
        }
  catch (Exception e) {
    }
return  P;
    }
public Persoon GetPersoonByname(String name){
     String[] elements = name.split("\\s+");
        
        try {
            statement = conn.prepareStatement
                                ("select * from Persoon where voornaam = ? and familienaam = ?");
            statement.setString(1,elements[1]);
            statement.setString(2, elements[0]);
            set = statement.executeQuery(); 
            if (set.next()) {
               
                    P = new Persoon();
                
                P.setFamilienaam(set.getString("FAMILIENAAM"));
                P.setGeboortedatum(set.getDate("GEBOORTEDATUM"));
                P.setHuisnr(set.getString("HUISNR"));
                P.setLand(set.getString("LAND"));
                P.setLogin(set.getString("LOGIN"));
                P.setPaswoord(set.getString("PASWOORD"));
                P.setPostcode(set.getString("postcode"));
                P.setStraat(set.getString("STRAAT"));
                P.setVoornaam(set.getString("VOORNAAM"));
                P.setWoonplaats(set.getString("WOONPLAATS"));
                P.setId(set.getInt("ID"));

                
            }
        }
  catch (Exception e) {
    }
return  P;
    }

    
 public int CheckIfCrew(Persoon P){
        
        PreparedStatement statement = null;
        ResultSet set = null;
        
        
        try {
          statement = conn.prepareStatement
                                ("select * from Bemanningslid where Persoon_id = ?");
            statement.setInt(1, P.getId());
            set = statement.executeQuery(); 
            if (set.next()) {
                return 1;
            }
            
            
        } catch (Exception e) {
        }
        return 0;
    }


public ArrayList<Vlucht> VluchtenperPassagier(int persoonID){
    
    ArrayList<Vlucht> lijst = new ArrayList<>();
    
    try {
        statement = connection.prepareCall("select vlucht.code, vlucht.vertrekluchthaven_id, vlucht.aankomstLuchthaven_id, "+
                                                                    "vlucht.AANKOMSTTIJD,vlucht.VERTREKTIJD from vlucht"+ 
                                                                    " inner join passagier on passagier.VLUCHT_ID = vlucht.ID where passagier.persoon_id = ? ");
        statement.setInt(1, persoonID);
        set = statement.executeQuery();
        
        while (set.next()) {
            V = new Vlucht();
            V.setCode(set.getString("code"));
            V.setVertrektijd(set.getTimestamp("vertrektijd"));
            V.setAankomsttijd(set.getTimestamp("aankomsttijd"));
            V.setAankomstluchthaven_id(set.getInt("aankomstLuchthaven_id"));
            V.setVertrekluchthaven_id(set.getInt("vertrekluchthaven_id"));
            
            lijst.add(V);
            
        }
        
        
    } catch (Exception e) {
    }
    
    return lijst;
}

public ArrayList<Persoon> PersoonPerVlucht(int vluchtID){
    
    ArrayList<Persoon> personen = new ArrayList<>();
    try 
    {
        statement = connection.prepareCall("select persoon.voornaam, persoon.familienaam, persoon.land, persoon.geboortedatum"+"inner join passagier.VLUCHT_ID = vlucht.ID where passagier.vlucht_id = ?");
        statement.setInt(1, vluchtID);
        set = statement.executeQuery();    
        
        while (set.next()){
            P = new Persoon();
            P.setVoornaam(set.getString("voornaam"));
            P.setFamilienaam(set.getString("familienaam"));
            P.setLand(set.getString("land"));
            P.setGeboortedatum(set.getDate("geboortedatum"));
            
            personen.add(P);
            
        }
        statement.close();
        connection.close();
    }
    catch (Exception e)
    {}
    
    return personen;
    
}
public ArrayList<String> get_names(){
    ArrayList<String> lijst = new ArrayList<>();
    ArrayList<Persoon> lijstpersonen = GetPersonen();
    for (Persoon persoon : lijstpersonen) {
        lijst.add(persoon.getNaam());
    }
    return lijst;
}
public Persoon Get_persoon_by_id(int id){
    Persoon P = new Persoon();
    ArrayList<Persoon> lijst = GetPersonen();
    for (Persoon persoon : lijst) {
        if (persoon.getId() == id) {
            P = persoon;
            
        }
    }
    return P;
}
}
