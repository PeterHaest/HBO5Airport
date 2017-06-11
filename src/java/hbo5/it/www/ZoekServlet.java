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
import hbo5.it.www.dataaccess.DALeasemaatschappij;
import hbo5.it.www.dataaccess.DALuchthaven;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVlucht;
import hbo5.it.www.dataaccess.DALuchtvaartmaatschappij;
import hbo5.it.www.dataaccess.DAPassagier;
import hbo5.it.www.dataaccess.DAVliegtuig;
import hbo5.it.www.dataaccess.DAVliegtuigklasse;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
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
    private DAVliegtuigklasse davliegtuigklasse = null;
   private DALeasemaatschappij dalease = null;
   private DAVliegtuig davliegtuig = null;
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
            if(davliegtuigklasse == null){
                davliegtuigklasse = new DAVliegtuigklasse(url, login, password, driver);
            }
             if (dalease == null) {
                dalease = new DALeasemaatschappij(url, login, password, driver);
            }
                if (davliegtuig == null) {
                davliegtuig = new DAVliegtuig(url, login, password, driver);
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
            if (davliegtuigklasse != null) {
                davliegtuigklasse.close();
            }
              if (dalease != null) {
                dalease.close();
            }
               if (davliegtuig != null) {
                davliegtuig.close();
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
            case "Booking":
                    session.setAttribute("Search", "Booking");
                    break;
            default:
                break;
        }
        
     
        Map<String,Object> nMap = new HashMap<>();
  
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
        else if (session.getAttribute("Search") == "Booking") {
            nMap.clear();
                            nMap.put("1",dalease.getTopId("passagier"));
                            nMap.put("2",1);
                            nMap.put("3", 0);
                            nMap.put("4", request.getParameter("LstKlasse"));
                            nMap.put("5",request.getParameter("txtStoel"));
                            nMap.put("6",session.getAttribute("gekozenvlucht"));
                            nMap.put("7",session.getAttribute("id") );
                            
                            
                            davliegtuig.Add_Row(nMap, "Passagier");
                            
                            
                       
                       
                       request.setAttribute("toekomst", "0");
                       session.setAttribute("Search", "geboekt");

            request.getRequestDispatcher("ZoekServlet?choice=huidigeVluchten&Zoeken=fuck").forward(request, response);
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
                String returnoptie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpCode(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.setAttribute("returnoptie", returnoptie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("datum".equals(request.getParameter("optie"))){
                String input = request.getParameter("date") + " 00:00:00";
                String optie = "vanaf " + request.getParameter("optie");
                String returnoptie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpDatum(input);
                request.setAttribute("vluchten", vluchten);
                String date  = request.getParameter("date");
                request.setAttribute("input", date);
                request.setAttribute("optie", optie);
                request.setAttribute("returnoptie", returnoptie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("bestemming".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie ="met " + request.getParameter("optie");
                String returnoptie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpBestemming(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.setAttribute("returnoptie", returnoptie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
            else if ("luchtvaartmaatschappij".equals(request.getParameter("optie"))){
                String input = request.getParameter("input");
                String optie = "met " + request.getParameter("optie");
                String returnoptie = request.getParameter("optie");
                ArrayList<Vlucht> vluchten = davlucht.VluchtOpLuchtvaartmaatschappij(input);
                request.setAttribute("vluchten", vluchten);
                request.setAttribute("input", input);
                request.setAttribute("optie", optie);
                request.setAttribute("returnoptie", returnoptie);
                request.getRequestDispatcher("zoekresult.jsp").forward(request, response);
            }
        }
        
        else if (session.getAttribute("Search") == "details"){
            Vlucht v = davlucht.ZoekDetails(Integer.parseInt(request.getParameter("id")));
            ArrayList<Passagier> passagiers = dapassagier.Passagiers_per_vlucht(Integer.parseInt(request.getParameter("id")));
            String optie = request.getParameter("optie");
            String input = request.getParameter("input");
            String hide = request.getParameter("hide");
            request.setAttribute("vlucht", v);
            request.setAttribute("optie", optie);
            request.setAttribute("input", input);
            request.setAttribute("hide", hide);
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
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(birthday);
                        int year = cal.get(Calendar.YEAR);
                        totaleleeftijd += today.getYear() - year;
                        teller++;
                    }
                    if (passagiers.isEmpty()){
                        totaleleeftijd = 0;
                    }
                    else {
                    totaleleeftijd = totaleleeftijd/teller;
                    }
                    Luchthaven l = daluchthaven.getLuchthaven("1");
                    Luchthaven l2 = daluchthaven.getLuchthaven(request.getParameter("Gemiddelde"));
                    String optie2 = " met bestemming "+ l2.getNaam();
                    String optie = " bij vluchten met aankomstluchthaven " + l.getNaam();
                    request.setAttribute("passagiers", passagiers);
                    request.setAttribute("optie", optie);
                    request.setAttribute("optie2", optie2);
                    request.setAttribute("totaleleeftijd", totaleleeftijd);
            }
            request.getRequestDispatcher("Statistieken.jsp").forward(request, response);
        }
                  if (request.getParameter("choice").equals("huidigeVluchten")) {
                  
                      request.setAttribute("toekomst", "0");
            session.setAttribute("huidigeVluchten", davlucht.vluchtenperpersoon((Integer)session.getAttribute("id")));
            request.getRequestDispatcher("huidigeVluchten.jsp").forward(request, response);}
          
                  else if (request.getParameter("choice").equals("toekomstigeVluchten")) {
               request.setAttribute("toekomst", "1");
            session.setAttribute("huidigeVluchten", davlucht.vluchtvanafnu());
            request.getRequestDispatcher("huidigeVluchten.jsp").forward(request, response);
        }
        
                  else if (request.getParameter("choice").equals("Book")) {
            session.setAttribute("gekozenvlucht", request.getParameter("vluchtid"));
            session.setAttribute("vliegtuigklasses", davliegtuigklasse.getvliegtuigklasse());
            request.getRequestDispatcher("booking.jsp").forward(request, response);
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
        String s = null;
        
        
        
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