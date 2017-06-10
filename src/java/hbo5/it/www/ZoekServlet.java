/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Luchtvaartmaatschappij;
import hbo5.it.www.beans.Passagier;
import hbo5.it.www.beans.Vlucht;
import hbo5.it.www.dataaccess.DALuchthaven;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVlucht;
import hbo5.it.www.dataaccess.DALuchtvaartmaatschappij;
import hbo5.it.www.dataaccess.DAPassagier;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
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
    private DALuchtvaartmaatschappij daluchtvaartmaatschappij = null;
    private DAPassagier dapassagier = null;
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
            
            if(daluchtvaartmaatschappij == null){
                daluchtvaartmaatschappij = new DALuchtvaartmaatschappij(url, login, password, driver);
            }
            if(daluchtvaartmaatschappij == null){
                daluchtvaartmaatschappij = new DALuchtvaartmaatschappij(url, login, password, driver);
            }
            if(dapassagier == null){
                dapassagier = new DAPassagier(url, login, password, driver);
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
            if (daluchtvaartmaatschappij!= null) {
                daluchtvaartmaatschappij.close();
            }
            if (dapassagier!= null) {
                dapassagier.close();
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
        if (session.getAttribute("lijstvluchten")== null) {
            session.setAttribute("lijstvluchten", davlucht.Vluchten());
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
               
        if (null != request.getParameter("Zoeken"))switch (request.getParameter("Zoeken")) {
            case "inkomend":
                session.setAttribute("Search", "inkomend");
                break;
            case "uitgaand":
                session.setAttribute("Search", "uitgaand");
                break;
            case "Zoeken":
                session.setAttribute("Search", "zoeken");
                break;
            case "Details":
                session.setAttribute("Search", "details");
                break;
            case "statistieken":
                session.setAttribute("Search", "statistieken");
                break;
            default:
                break;
        }
        
        if (session.getAttribute("Search") == "inkomend") {
            if (request.getParameter("Luchthaven") != null){
                String luchthavenid = request.getParameter("Luchthaven");
                ArrayList<Vlucht> vluchten = davlucht.InkomendeVluchten(Integer.parseInt(luchthavenid));
                request.setAttribute("vluchten", vluchten);
                }
            else {
                 if (session.getAttribute("lijsthavens")== null) {
                session.setAttribute("lijsthavens",  daluchthaven.getLuchthavens());
            }
                ArrayList<Vlucht> vluchten;
                vluchten = davlucht.InkomendeVluchten(0);
                request.setAttribute("vluchten", vluchten);
            }
             // Will be available as ${vluchten} in JSP
            request.getRequestDispatcher("inkomend.jsp").forward(request, response);
        }
        
        else if (session.getAttribute("Search") == "uitgaand") {
            if (request.getParameter("Luchthaven") != null){
                String luchthavenid = request.getParameter("Luchthaven");
                ArrayList<Vlucht> vluchten = davlucht.UitgaandeVluchten(Integer.parseInt(luchthavenid));
                request.setAttribute("vluchten", vluchten);
                }
            else {
                 if (session.getAttribute("lijsthavens")== null) {
                session.setAttribute("lijsthavens",  daluchthaven.getLuchthavens());
            }
                ArrayList<Vlucht> vluchten;
                vluchten = davlucht.UitgaandeVluchten(0);
                request.setAttribute("vluchten", vluchten);
            }
             // Will be available as ${vluchten} in JSP
            request.getRequestDispatcher("uitgaand.jsp").forward(request, response);
        }
        
        else if (session.getAttribute("Search") == "zoeken"){
            if  ("vluchtnummer".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = "met " + request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpCode(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("datum".equals(request.getParameter("optie"))){
                String input = request.getParameter("date") + " 00:00:00";
                String optie = "vanaf " + request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpDatum(input);
                request.setAttribute("vluchten", vluchten);
                String date  = request.getParameter("date");
                request.setAttribute("input", date);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("bestemming".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie ="met " + request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpBestemming(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("luchtvaartmaatschappij".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = "met " + request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpLuchtvaartmaatschappij(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
        }
        else if (session.getAttribute("Search") == "details"){
            Vlucht v = davlucht.ZoekDetails(Integer.parseInt(request.getParameter("id")));
            ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_vlucht(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("vlucht", v);
            request.setAttribute("passagiers", passagiers);
            request.getRequestDispatcher("details.jsp").forward(request, response);
        }
        else if (session.getAttribute("Search") == "statistieken"){
            if (session.getAttribute("lijstvluchten")== null) {
            session.setAttribute("lijstvluchten", davlucht.Vluchten());
            }
            if (session.getAttribute("lijsthavens")== null) {
                session.setAttribute("lijsthavens",  daluchthaven.getLuchthavens());
            }
            if (session.getAttribute("lijstluchtvaartmaatschappijen")== null){
                session.setAttribute("lijstluchtvaartmaatschappijen", daluchtvaartmaatschappij.get_luchtvaartmaatschapijen());
            }
            if ("Luchthaven".equals(request.getParameter("Search"))){
                if (request.getParameter("Luchthaven") != null){
                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_luchthaven(Integer.parseInt(request.getParameter("Luchthaven")));
                    Luchthaven l = daluchthaven.getLuchthaven(request.getParameter("Luchthaven"));
                    String optie = " bij vluchten met aankomstluchthaven " + l.getNaam();
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                    }
                else {

                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_luchthaven(1);
                    Luchthaven l = daluchthaven.getLuchthaven("1");
                    String optie = " bij vluchten met aankomstluchthaven " + l.getNaam();
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                }
            }
            else if ("Vlucht".equals(request.getParameter("Search"))){
                if (request.getParameter("Vlucht") != null){
                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_vlucht(Integer.parseInt(request.getParameter("Vlucht")));
                    Vlucht v = davlucht.ZoekDetails(Integer.parseInt(request.getParameter("Vlucht")));
                    String optie = " bij vluchten met vluchtcode " + v.getCode();
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                    }
                else {
                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_vlucht(1);
                    Vlucht v = davlucht.ZoekDetails(1);
                    String optie = " bij vluchten met vluchtcode " + v.getCode();
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                }
            }
            else if ("Dag".equals(request.getParameter("Search"))){
                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_dag(request.getParameter("date"));
                    String optie = " bij vluchten met Datum " + request.getParameter("date");
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                    
                        }
            else if ("Luchtvaartmaatschappij".equals(request.getParameter("Search"))){                
                ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_luchtvaartmaatschappij(Integer.parseInt(request.getParameter("luchtvaartmaatschappij")));
                Luchtvaartmaatschappij lvm = daluchtvaartmaatschappij.get_luchtvaartmaatschapijen_by_id(Integer.parseInt(request.getParameter("luchtvaartmaatschappij")));
                
                String optie = " bij vluchten met Luchtvaartmaatschappij " + lvm.getNaam();
                request.setAttribute("passagiers", passagiers);
                request.setAttribute("optie", optie);
            }
            else if ("Gemiddelde".equals(request.getParameter("Search"))){          
                    ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_luchthaven(Integer.parseInt(request.getParameter("Gemiddelde")));
                    int totaleleeftijd = 0;
                    int teller = 0;
                    LocalDate today = LocalDate.now();
                    for (Passagier p : passagiers) {
                        Date birthday = p.getPersoon().getGeboortedatum();
                        totaleleeftijd += (Integer)today.getYear();
                        teller++;
                    }
                    totaleleeftijd = totaleleeftijd/teller;
                    request.setAttribute("totaleleeftijd", totaleleeftijd);
            }
            request.getRequestDispatcher("Statistieken.jsp").forward(request, response);
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