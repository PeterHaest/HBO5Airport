/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Persoon;
import hbo5.it.www.dataaccess.DAPassagier;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVliegtuigklasse;
import hbo5.it.www.dataaccess.DAVlucht;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author c1040604
 */
@WebServlet(name = "ManageServlet", urlPatterns = {"/ManageServlet"}, initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@ti-oracledb06.thomasmore.be:1521:XE"),
    @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver"),
    @WebInitParam(name = "login", value = "c1035462"),
    @WebInitParam(name = "password", value = "7086")})

public class ManageServlet extends HttpServlet {
  private DAPersoon dapersoon = null;
  private DAVlucht davlucht = null;
  private DAPassagier dapassagier = null;
  private DAVliegtuigklasse davliegtuigklasse = null;

    @Override
    public void init() throws ServletException {
        try {
            String url = getInitParameter("url");
            String password = getInitParameter("password");
            String login = getInitParameter("login");
            String driver = getInitParameter("driver");
            if (dapersoon== null) {
                dapersoon = new DAPersoon(url, login, password, driver);
            }
            if (davlucht== null){
               davlucht = new DAVlucht(url, login, password, driver);
            }
            if (dapassagier==null)
            {dapassagier = new DAPassagier(url,login,password,driver);}
            if (davliegtuigklasse==null)
            {davliegtuigklasse = new DAVliegtuigklasse(url,login,password,driver);}
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }





    @Override
    public void destroy() {
        try {
            if (dapersoon!= null) {
                dapersoon.close();
            }
            if(davlucht!= null){
                davlucht.close();
            }
            if(dapassagier!=null){
            dapassagier.close();
            }
            if(davliegtuigklasse!=null){
            davliegtuigklasse.close();
            }
            
                
        } catch (SQLException e) {
   
         
        }
    }
    
   RequestDispatcher rd;
  

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Persoon> Personen = dapersoon.PersoonPerVlucht(0);
        request.setAttribute("Persoon", Personen);
        
        
        
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

