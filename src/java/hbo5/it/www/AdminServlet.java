/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;



import hbo5.it.www.beans.Bemanningslid;

import hbo5.it.www.beans.Crew;
import hbo5.it.www.beans.Hangar;
import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.beans.Passagier;
import hbo5.it.www.beans.Persoon;
import hbo5.it.www.beans.Vlucht;
import hbo5.it.www.dataaccess.DABemanningslid;
import hbo5.it.www.dataaccess.DAHangar;
import hbo5.it.www.dataaccess.DALeasemaatschappij;
import hbo5.it.www.dataaccess.DALuchthaven;
import hbo5.it.www.dataaccess.DALuchtvaartmaatschappij;
import hbo5.it.www.dataaccess.DAPassagier;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVliegtuig;
import hbo5.it.www.dataaccess.DAVliegtuigtype;
import hbo5.it.www.dataaccess.DAVlucht;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author c1040604
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"}, initParams = {
    @WebInitParam(name = "url", value = "jdbc:oracle:thin:@ti-oracledb06.thomasmore.be:1521:XE"),
    @WebInitParam(name = "driver", value = "oracle.jdbc.driver.OracleDriver"),
    @WebInitParam(name = "login", value = "c1035462"),
    @WebInitParam(name = "password", value = "7086")})
public class AdminServlet extends HttpServlet {

    
        private DALuchthaven daLuchthaven = null;
        private DALuchtvaartmaatschappij damaatschappij = null;
        private DAPersoon dapersoon = null;
        private DAVliegtuig davliegtuig = null;
        private DAHangar dahangar = null;
        private DALeasemaatschappij dalease = null;
        private DAVlucht davlucht = null;
        private DAPassagier dapassagier = null;
        private DAVliegtuigtype datype = null;

        private DABemanningslid dabemanning = null;

        
        
        
        
    @Override
    public void init() throws ServletException {
        try {
            String url = getInitParameter("url");
            String password = getInitParameter("password");
            String login = getInitParameter("login");
            String driver = getInitParameter("driver");
            if (daLuchthaven == null) {
                daLuchthaven = new DALuchthaven(url, login, password, driver);
            }
            if (damaatschappij == null) {
                damaatschappij = new DALuchtvaartmaatschappij(url, login, password, driver);
            }
            if (dapersoon == null) {
                dapersoon = new DAPersoon(url, login, password, driver);
            }
            if (davliegtuig == null) {
                davliegtuig = new DAVliegtuig(url, login, password, driver);
            }
            if (dahangar == null) {
                dahangar = new DAHangar(url, login, password, driver);
            }
            if (dalease == null) {
                dalease = new DALeasemaatschappij(url, login, password, driver);
            }
            if (davlucht == null){
                davlucht = new DAVlucht(url, login, password, driver);
            }
            if(dapassagier == null){
                dapassagier = new DAPassagier(url, login, password, driver);
            }
            if (datype == null) {
                datype = new DAVliegtuigtype(url, login, password, driver);
            }

            if (dabemanning == null) {
                dabemanning = new DABemanningslid(url, login, password, driver);
            }

        }catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            if ( daLuchthaven != null) {
                daLuchthaven.close();
            }
            if (damaatschappij != null) {
                damaatschappij.close();
            }
            if (dapersoon != null){
                dapersoon.close();
            }
            if (davliegtuig != null) {
                davliegtuig.close();
            }
            if (dahangar != null) {
                dahangar.close();
            }
            if (dalease != null) {
                dalease.close();
            }
            if(davlucht != null){
                davlucht.close();
            }
            if (dapassagier != null) {
                dapassagier.close();
            }
            if (datype != null) {
                datype.close();
            }

            if(dabemanning != null){
                dabemanning.close();
            }

        } catch (SQLException e) {
        }}
    
    
    RequestDispatcher rd;
    HttpSession session = null;
    
    
    
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
        }
         rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
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
              session = request.getSession();
              session.setAttribute("maatschappijen", dalease.get_Leasemaatschappij());
              session.setAttribute("lijstTypes", datype.Get_types());
              session.setAttribute("lijstVluchten", davlucht.Vluchten());
              session.setAttribute("lijstvluchten", davlucht.Vlucht_ids());
              session.setAttribute("lijsthavens",  daLuchthaven.getLuchthavens());
              session.setAttribute("lijstmaatschappijen",damaatschappij.get_luchtvaartmaatschapijen());
              session.setAttribute("lijstpersonen", dapersoon.get_names());

              session.setAttribute("lijstLease",dalease.get_leaseNamen());

            
   
        if (request.getParameter("btnWijzig")!= null) {
                url="LoginPage.jsp";
            }

            else if (request.getParameter("btnVerwijder") != null) {
                
                
                
                url="LoginPage.jsp";
            }
            else if("luchthavens".equals( request.getParameter("page"))){
                url= "overzichtLuchthavens.jsp";
            }
            else if ("bemanning".equals(request.getParameter("page"))){
                url="overzichtBemanning.jsp";
            }
            else if ("passagiers".equals(request.getParameter("page"))){
                session.setAttribute("lijstPassagiers",dapassagier.getPassagiers());
                url="overzichtPassagiers.jsp";
            }
             else if ("Leasemaatschappij".equals(request.getParameter("page"))){
                url="overzichtLease.jsp";
            }

             else if ("luchtvaartmaatschappij".equals(request.getParameter("page"))){

                url="overzichtMaatschappijen.jsp";
            }
              else if ("vliegtuig".equals(request.getParameter("page"))){
                 session.setAttribute("lijstvliegtuigen",davliegtuig.getVliegtuigLijst());
                url="overzichtvliegtuigen.jsp";
            }
             else if ("persoon".equals(request.getParameter("page"))){
                url="overzichtPersonen.jsp";
            }
            
            else if("hangars".equals(request.getParameter("page"))){
                session.setAttribute("lijstHangars", dahangar.Get_Hangars());
                session.setAttribute("lijsthangarnamen", dahangar.get_namen((ArrayList<Hangar>)session.getAttribute("lijstHangars")));
                url = "overzichtHangars.jsp";
            }
            else if ("vlucht".equals(request.getParameter("page"))){

                session.setAttribute("lijstBemanning", dabemanning.getAll());

                request.setAttribute("topId", dalease.getTopId("vlucht"));   
                url = "newitem.jsp?kind=vlucht";
            }
            
             else  if ("add".equals(request.getParameter("choice"))){
             if ("lease".equals(request.getParameter("kind"))) {
                 request.setAttribute("topId", dalease.getTopId("Leasemaatschappij"));   
                 request.setAttribute("kind", "lease");
            }

             else if ("Luchthaven".equals(request.getParameter("kind"))) {
                request.setAttribute("topId", dalease.getTopId("Luchthaven"));
                request.setAttribute("kind", "Luchthaven");
            }
             else if ("luchtvaartmaatschappij".equals(request.getParameter("kind"))) {
                request.setAttribute("topId", dalease.getTopId("Luchtvaartmaatschappij"));
                request.setAttribute("kind", "luchtvaartmaatschappij");

            }
             else if ("vliegtuig".equals(request.getParameter("kind"))) {
              
                request.setAttribute("topId", dalease.getTopId("vliegtuig"));
                request.setAttribute("kind", "vliegtuig");
            }
              
             
             
             
             url="newitem.jsp";
         }
             else if ("update".equals(request.getParameter("choice"))) {

                 if (session.getAttribute("txtid") != null) {

                
            
                 session.setAttribute("maatschappijen", dalease.get_Leasemaatschappij());
                  session.setAttribute("lijstmaatschappijen",damaatschappij.get_luchtvaartmaatschapijen());
                  if ("lease".equals(request.getParameter("kind"))) {
                      session.setAttribute("ChosenHaven", null);
                       session.setAttribute("ChosenPlane", null);
                
            }

                  if ("Luchthaven".equals(request.getParameter("kind"))) {

                       session.setAttribute("L", null);
                       session.setAttribute("ChosenPlane", null);
                
            }
                  if("vliegtuig".equals(request.getParameter("kind"))){
                       session.setAttribute("ChosenHaven", null);
                       Integer id =(Integer) session.getAttribute("currentId");
                       session.setAttribute("ChosenPlane", davliegtuig.get_by_id(id));
                       session.setAttribute("L", null);
                  }
                  url="wijzigitem.jsp";
                 }
                 else{
                     url = (String) session.getAttribute("currentPage");
                 }
        }
              else if ("delete".equals(request.getParameter("choice"))) {
                  if ("lease".equals(request.getParameter("kind"))) {
                      session.setAttribute("ChosenHaven", null);
                url="deleteitem.jsp";
            }

                  if ("Luchthaven".equals(request.getParameter("kind"))) {
                      session.setAttribute("L", null);
                  url="deleteitem.jsp";

            } 
        }
             else if (request.getParameter("nieuw") != null) {
                 Integer id = Integer.parseInt( request.getParameter("txtid"));
                 Map<String,Object> nMap = new HashMap<>();
                   if ("Lease".equals(session.getAttribute("newItem"))) {
            dalease.Add_maatschappij( id, request.getParameter("txtnaam"),"leasemaatschappij");
                   }

                   else   if ("Luchthaven".equals(session.getAttribute("newItem"))) {
                       nMap.put("1", request.getParameter("txtid"));
                       nMap.put("2", request.getParameter("txtnaam"));
                       nMap.put("3", request.getParameter("txtstad"));
                       
                       
                        
                       url="AdminServlet?page=luchthavens" ;
                   }
                   else if ("luchtvaartmaatschappij".equals(session.getAttribute("newItem"))) {
                       nMap.clear();
                       nMap.put("1", request.getParameter("txtid"));
                       nMap.put("2", request.getParameter("txtnaam"));
                       
           
                url="AdminServlet?page=luchtvaartmaatschappij";
            }
                   else if ("vliegtuig".equals(session.getAttribute("newItem"))) {
                       nMap.clear();
                       nMap.put("1", request.getParameter("txtid"));
                       nMap.put("2", request.getParameter("LstType"));
                       nMap.put("3", request.getParameter("LstLease"));
                       nMap.put("4",request.getParameter("LstMaatschappij") );
                      

           
                url= "AdminServlet?page=vliegtuig";
            }
                   else if("vlucht".equals(session.getAttribute("newItem"))){
                       SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                       SimpleDateFormat dtc = new SimpleDateFormat("dd/mm/yy");
                       Date vertrek = null;
                       Date aankomst = null;
                     try {
                         vertrek = dt.parse(request.getParameter("txtVertrek"));
                         aankomst = dt.parse(request.getParameter("txtAankomst"));
                         
                     } catch (ParseException ex) {
                         Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                     }
                       nMap.clear();
                       nMap.put("1", id);
                       nMap.put("2", request.getParameter("txtCode"));
                       nMap.put("3", dtc.format(vertrek));
                       nMap.put("4", dtc.format(aankomst));
                       nMap.put("5", request.getParameter("LstType"));
                       nMap.put("6", request.getParameter("LstVertrek"));
                       nMap.put("7", request.getParameter("LstAankomst"));
                       
 
                       
                    Integer teller = 1;
                    List<Integer> lijst = new LinkedList<>();
                       while (teller <= dabemanning.getAll().keySet().size()) {                           
                          
                           if (request.getParameter(teller.toString()) != null) {
                              lijst.add(teller);
                           }
                         
                            teller ++;
                       }
                       davliegtuig.Add_Row( nMap, (String) session.getAttribute("newItem"));
                       for (Integer integer : lijst) {
                           Bemanningslid b = dabemanning.getById(integer.intValue());
                           nMap.clear();
                           nMap.put("1",dalease.getTopId("vluchtbemanning"));
                           nMap.put("2",dabemanning.getFunctiebyid(b.getFunctie_id()));
                           nMap.put("3", b.getId());
                           nMap.put("4", id );
                          davliegtuig.Add_Row(nMap, "vluchtbemanning");
                       }
                
                       
                       
                       
                       
                 url="AdminServlet?page=vlucht";
             }
                   
  

                     davliegtuig.Add_Row( nMap, (String) session.getAttribute("newItem"));

                   
                   
       //     url = "StartAdmin.jsp";
        }
             else if (request.getParameter("update") != null) {
                 Map<String,Object> nMap = new HashMap<>();
                  Integer id = Integer.parseInt(request.getParameter("txtid"));
                 if (id != null) {
                 String item = "";
                   if ("Lease".equals(session.getAttribute("newItem"))) {
                       nMap.put("naam",request.getParameter("txtnaam") );
                       item = "Leasemaatschappij";
            }

                    else   if ("Luchthaven".equals(session.getAttribute("newItem"))) {

                       
                        nMap.put("naam",  request.getParameter("txtnaam"));
                        nMap.put("stad", request.getParameter("txtstad"));
                        item = "luchthaven";
                       
                    }
                    else if("vliegtuig".equals(session.getAttribute("newItem"))){
                       nMap.put("vliegtuigtype_id", Integer.parseInt( request.getParameter("LstType")));
                       nMap.put("leasemaatschappij_id", Integer.parseInt(request.getParameter("LstLease")));
                       nMap.put("luchtvaartmaatschappij_id", Integer.parseInt(request.getParameter("LstMaatschappij")));
                       item = "vliegtuig";
                    }
                    else if("Persoon".equals(session.getAttribute("newItem"))){
                        Persoon p = (Persoon) session.getAttribute("ChosenPerson");
                        
                        nMap.put("voornaam", p.getVoornaam());
                        nMap.put("familienaam", p.getFamilienaam());
                        
                        
                        
                        
                        
                        
                        
                        item = "persoon";
                    }
                   
                    davliegtuig.UpdateVliegtuig( id,item , nMap);
                 }
                   
                   
                   
            url = "StartAdmin.jsp";
        }
             else if(request.getParameter("delete") != null){
                 String obj="";
                  if ("Lease".equals(session.getAttribute("delItem"))) {
                      obj = "Leasemaatschappij";
                  }
 url = "StartAdmin.jsp";

                  else   if ("Luchthaven".equals(session.getAttribute("delItem"))) {
                      obj = "luchthaven";
                       url="AdminServlet?page=luchthavens";
                  }
                  dalease.DeleteItem(obj,Integer.parseInt( request.getParameter("txtid")) );

             }
             
            else{
                  url="StartAdmin.jsp";
            }
   rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
       // request.getRequestDispatcher(url).forward(request, response);
    }
   String url = null;
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
        session = request.getSession();
         session.setAttribute("VarVlucht", request.getParameter("LstVluchten"));
     Integer id;
        if ("BemVlucht".equals(request.getParameter("choice"))) {
           
            ArrayList<Crew> crewLijst;
            crewLijst = davlucht.Crew_per_vlucht((String)session.getAttribute("VarVlucht"));
            ArrayList<Persoon> persoonlijst;
            persoonlijst = dapersoon.GetPersonen();
            id = davlucht.vluchtID((String) session.getAttribute("VarVlucht"),(ArrayList<Vlucht>)session.getAttribute("lijstVluchten"));
            if (crewLijst.size() > 0) {
                request.setAttribute("Bemanning", crewLijst);
            }
            else{
                request.setAttribute("Bemanning", null );
            }
            
            url = "overzichtBemanning.jsp";
        }
        else if("PasVlucht".equals(request.getParameter("choice"))){
                id = davlucht.vluchtID((String) session.getAttribute("VarVlucht"),(ArrayList<Vlucht>)session.getAttribute("lijstVluchten"));
                ArrayList<Passagier> pasLijst;
                pasLijst = (ArrayList<Passagier>) session.getAttribute("lijstPassagiers");
                        if (pasLijst.size() > 0) {
                          request.setAttribute("Passagiers",davlucht.pervlucht(id,pasLijst,dapersoon));
                        }
                        else{
                            request.setAttribute("Passagiers", null);
                        }
                  url = "overzichtPassagiers.jsp";
        }
        else if ("Hangar".equals(request.getParameter("choice"))){
            request.setAttribute("VarHangar", request.getParameter("LstHangar"));
            request.setAttribute("Hangar", dahangar.get_byName((ArrayList<Hangar>) session.getAttribute("lijstHangars"),(String) request.getAttribute("VarHangar")));
            request.setAttribute("InhoudHangar", davliegtuig.Get_by_Hangar((String) request.getAttribute("VarHangar")));
            url="overzichtHangars.jsp";
        }

        else if ("Luchthaven".equals(request.getParameter("choice"))){

        session.setAttribute("VarLuchthaven", request.getParameter("LstHaven"));
        request.setAttribute("Luchthaven", daLuchthaven.getLuchthaven((String)session.getAttribute("VarLuchthaven")));
        url = "overzichtLuchthavens.jsp";
        }

        else if ("luchtvaartmaatschappij".equals(request.getParameter("choice"))){

        session.setAttribute("Varmaatschappij",request.getParameter("LstMaatschappij"));
        request.setAttribute("Maatschappij",damaatschappij.getMaatschappij((String) session.getAttribute("Varmaatschappij")) );
        url="overzichtMaatschappijen.jsp";
        }
         else if ("Leasemaatschappij".equals(request.getParameter("choice"))){
          session = request.getSession();
       // session.setAttribute("VarLease",request.getParameter("LstLease"));
        request.setAttribute("VarLeasemaatschappij", dalease.get_maatschappij(request.getParameter("LstLease")));
        url="overzichtLease.jsp";
        }
         else if ("Vliegtuig".equals(request.getParameter("choice"))){
          session = request.getSession();
          session.setAttribute("Varvliegtuig",request.getParameter("LstVliegtuigen"));
          request.setAttribute("Vliegtuig",davliegtuig.Getvliegtuiginfo((String)session.getAttribute("Varvliegtuig")));
          url="overzichtvliegtuigen.jsp";
        }
        else if ("Persoon".equals(request.getParameter("choice"))){
        session.setAttribute("Varpersoon",request.getParameter("LstPersonen"));
        request.setAttribute("Persoon",dapersoon.GetPersoonByname((String) session.getAttribute("Varpersoon")) );
        url="overzichtPersonen.jsp";
        }
  
        
        
      
          request.getRequestDispatcher(url).forward(request, response);
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
