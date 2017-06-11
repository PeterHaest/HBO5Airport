/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.dataaccess;

import hbo5.it.www.beans.Leasemaatschappij;
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
public class DALeasemaatschappij {
        private Connection connection = null;

public DALeasemaatschappij(String url, String login, String password, String driver)   throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }  
    }
    
    
      PreparedStatement statement = null;
        ResultSet set = null;
        Leasemaatschappij L;
    
    public ArrayList<Leasemaatschappij> get_Leasemaatschappij(){          
            ArrayList<Leasemaatschappij> lijst = new ArrayList<>();
        try {
            
            statement = connection.prepareStatement("select * from leasemaatschappij");
            set = statement.executeQuery();

            
            
            while (set.next()) {
                L = new  Leasemaatschappij();
                L.setId(set.getInt("id"));
                L.setNaam(set.getString("naam"));
                lijst.add(L);
            }
            statement.close();
            set.close();
        } catch (Exception e) {
        }
 return lijst;
    }
    
    public ArrayList<String> get_leaseNamen(){
        ArrayList<Leasemaatschappij> lijstLeasemaatschappij = get_Leasemaatschappij();
        ArrayList<String> lijst = new ArrayList<>();
        for (Leasemaatschappij leasemaatschappij : lijstLeasemaatschappij) {
            lijst.add(leasemaatschappij.getNaam());
        }
        return lijst;
    }
    
    public Leasemaatschappij get_maatschappij(String naam){
        Leasemaatschappij L = new Leasemaatschappij();
        ArrayList<Leasemaatschappij> lijst = get_Leasemaatschappij();
        for (Leasemaatschappij leasemaatschappij : lijst) {
            if (leasemaatschappij.getNaam().equals(naam)) {
                L = leasemaatschappij;
            }
        }
        return L;
    }
    public Integer getTopId( String Table){
        Integer I = null;
        StringBuilder builder = new StringBuilder();
        builder.append("Select id from ");
        builder.append(Table);
        builder.append(" where rownum = 1 ");
        builder.append("order by id desc ");
        try {
        statement = connection.prepareStatement(builder.toString());
        set = statement.executeQuery();
        if (set.next()) {
            I = set.getInt(1) +1;
        }
              } catch (Exception e) {
        }
        return I;
    }
    public void DeleteItem(String Table, Integer id){
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ");
        builder.append(Table);
        builder.append(" where id = ");
        builder.append(id);
        try{
            statement = connection.prepareStatement(builder.toString());
            statement.executeUpdate();
            connection.commit();
        }
        catch(Exception e){
            
        }
                
    }
    public void deletePas(String Vluchtid, Integer persid){
              StringBuilder builder = new StringBuilder();
        builder.append("delete from passagier ");
        builder.append(" where  vlucht_id = ");
        builder.append(Vluchtid);
          builder.append(" and  persoon_id = ");
           builder.append(persid);
            builder.append(" and rownum = 1");
        try{
            statement = connection.prepareStatement(builder.toString());
            statement.executeUpdate();
            connection.commit();
        }
        catch(Exception e){
            
        }
    }
    
          
    public void Add_maatschappij(Integer id, String Naam, String Table){
  StringBuilder builder = new StringBuilder();
        builder.append("insert into ");
        builder.append(Table);
        builder.append(" values (  ");
        builder.append(id);
        builder.append(" , '");
        builder.append(Naam);
        builder.append("')");
        
        try {
               statement = connection.prepareStatement(builder.toString());
            
               statement.executeUpdate();
               connection.commit();
               
        } catch (Exception e) {
        }
    }
    public void Update_maatschappij(Integer id, String Naam){
         StringBuilder builder = new StringBuilder();
         builder.append("update leasemaatschappij ");
         builder.append("set NAAM = '");
         builder.append(Naam);
         builder.append("' where id = ");
         builder.append(id);
         try {
            statement = connection.prepareStatement(builder.toString());
            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();
            
             
        } catch (Exception e) {
        }
         
         
         
         
    }
    
    

}
