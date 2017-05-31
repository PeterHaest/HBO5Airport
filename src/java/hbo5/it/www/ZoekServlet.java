/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Vlucht;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVlucht;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebInitParam;


/**
 *
 * @author c1040604
 */

@WebServlet(name = "ZoekServlet", urlPatterns = {"/ZoekServlet"}, 
    initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@ti-oracledb06.thomasmore.be:1521:XE"),
    @WebInitParam(name = "login", value = "c1035462"),
    @WebInitParam(name = "password", value = "7086"),
    @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver")})




public class ZoekServlet extends HttpServlet {

    private DAVlucht davlucht = null;
    @Override
    public void init() throws ServletException {
        try {
            String url = getInitParameter("url");
            String password = getInitParameter("password");
            String login = getInitParameter("login");
            String driver = getInitParameter("driver");
            if (davlucht == null) {
                davlucht = new DAVlucht(url, login, password, driver);
            }
        }catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            if ( davlucht!= null) {
                davlucht.close();
            }
        } catch (SQLException e) {
        }
    }
   
    RequestDispatcher rd;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        if ("inkomend".equals(request.getParameter("Zoeken"))){
            String luchthavenid = request.getParameter("Luchthaven");
            ArrayList<Vlucht> vluchten = davlucht.InkomendeVluchten(Integer.parseInt(luchthavenid));
            request.setAttribute("vluchten", vluchten);
        }
        
        else if ("uitgaand".equals(request.getParameter("Zoeken"))){
            String luchthavenid = request.getParameter("Luchthaven");
            ArrayList<Vlucht> vluchten = davlucht.UitgaandeVluchten(Integer.parseInt(luchthavenid));
            request.setAttribute("vluchten", vluchten);
        }
        
         // Will be available as ${vluchten} in JSP
        request.getRequestDispatcher("inkomend.jsp").forward(request, response);
        
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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        if ("inkomend".equals(request.getParameter("Zoeken"))){
            String luchthavenid = request.getParameter("Luchthaven");
            ArrayList<Vlucht> vluchten = davlucht.InkomendeVluchten(Integer.parseInt(luchthavenid));
            request.setAttribute("vluchten", vluchten);
            request.getRequestDispatcher("inkomend.jsp").forward(request, response);
        }
        
        else if ("uitgaand".equals(request.getParameter("Zoeken"))){
            String luchthavenid = request.getParameter("Luchthaven");
            ArrayList<Vlucht> vluchten = davlucht.UitgaandeVluchten(Integer.parseInt(luchthavenid));
            request.setAttribute("vluchten", vluchten);
            request.getRequestDispatcher("uitgaand.jsp").forward(request, response);
        }
        else if ("Zoeken".equals(request.getParameter("Zoeken"))){
            if  ("vluchtnr".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpCode(input);
                request.setAttribute("vluchten", vluchten);
                request.getRequestDispatcher("Zoekresult.jsp").forward(request, response);
            }
            else if ("datum".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                request.getRequestDispatcher("Zoekresult.jsp").forward(request, response);
            }
            else if ("bestemming".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpBestemming(Integer.parseInt(input));
                request.setAttribute("vluchten", vluchten);
                request.getRequestDispatcher("Zoekresult.jsp").forward(request, response);
            }
            else if ("luchtvaartmaatschappij".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpLuchtvaartmaatschappij(Integer.parseInt(input));
                request.setAttribute("vluchten", vluchten);
                request.getRequestDispatcher("Zoekresult.jsp").forward(request, response);
            }
        }
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
//        if (request.getParameter("Login") != null) {
//            CheckLogin();
//        }
        
        
        
        
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
    }
    }// </editor-fold>