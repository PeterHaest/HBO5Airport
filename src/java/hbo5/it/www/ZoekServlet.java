/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Vlucht;
import hbo5.it.www.dataaccess.DALuchthaven;
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
import javax.servlet.http.HttpSession;


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
    private DALuchthaven daluchthaven = null;
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
            if(daluchthaven == null){
                daluchthaven = new DALuchthaven(url, login, password, driver);
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
            if (daluchthaven!= null) {
                daluchthaven.close();
            }
        } catch (SQLException e) {
        }
    }
    HttpSession session;
    RequestDispatcher rd;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        
        if (session.getAttribute("lijsthavens")== null) {
            session.setAttribute("lijsthavens", daluchthaven.Get_naam_luchtHaven());
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String luchthavenid = request.getParameter("Luchthaven");
        ArrayList<Vlucht> vluchten = davlucht.InkomendeVluchten(Integer.parseInt(luchthavenid));
        
        request.setAttribute("vluchten", vluchten);
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
        PrintWriter out = response.getWriter();
        session = request.getSession();
               
        if ("inkomend".equals(request.getParameter("Zoeken"))){
            session.setAttribute("Search", 0);
        }
        else if ("uitgaand".equals(request.getParameter("Zoeken"))){
            session.setAttribute("Search", 1);
        }
        else if ("Zoeken".equals(request.getParameter("Zoeken"))){
            session.setAttribute("Search", 2);
        }
        else if ("Details".equals(request.getParameter("Zoeken"))){
            session.setAttribute("Search", 3);
        }
        
        if ((Integer)session.getAttribute("Search") == 0) {
            if (request.getParameter("Luchthaven") != null){
                String luchthavenid = request.getParameter("Luchthaven");
                ArrayList<Vlucht> vluchten = davlucht.InkomendeVluchten(Integer.parseInt(luchthavenid));
                request.setAttribute("vluchten", vluchten);
                }
            else {
                 if (session.getAttribute("lijsthavens")== null) {
                session.setAttribute("lijsthavens", daluchthaven.Get_naam_luchtHaven());
            }
                ArrayList<Vlucht> vluchten;
                vluchten = davlucht.InkomendeVluchten(0);
                request.setAttribute("vluchten", vluchten);
            }
             // Will be available as ${vluchten} in JSP
            request.getRequestDispatcher("inkomend.jsp").forward(request, response);
        }
        
        else if ((Integer)session.getAttribute("Search") == 1) {
            if (request.getParameter("Luchthaven") != null){
                String luchthavenid = request.getParameter("Luchthaven");
                ArrayList<Vlucht> vluchten = davlucht.UitgaandeVluchten(Integer.parseInt(luchthavenid));
                request.setAttribute("vluchten", vluchten);
                }
            else {
                 if (session.getAttribute("lijsthavens")== null) {
                session.setAttribute("lijsthavens", daluchthaven.Get_naam_luchtHaven());
            }
                ArrayList<Vlucht> vluchten;
                vluchten = davlucht.UitgaandeVluchten(0);
                request.setAttribute("vluchten", vluchten);
            }
             // Will be available as ${vluchten} in JSP
            request.getRequestDispatcher("uitgaand.jsp").forward(request, response);
        }
        
        else if ((Integer)session.getAttribute("Search") == 2){
            if  ("vluchtnummer".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpCode(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("datum".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("bestemming".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpBestemming(Integer.parseInt(input));
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("luchtvaartmaatschappij".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpLuchtvaartmaatschappij(Integer.parseInt(input));
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
        }
        else if ((Integer)session.getAttribute("Search") == 3){
            Vlucht v = davlucht.ZoekDetails(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("vlucht", v);
            request.getRequestDispatcher("details.jsp").forward(request, response);
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