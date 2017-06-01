/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www;

import hbo5.it.www.beans.Luchthaven;
import hbo5.it.www.dataaccess.DAHangar;
import hbo5.it.www.dataaccess.DALeasemaatschappij;
import hbo5.it.www.dataaccess.DALuchthaven;
import hbo5.it.www.dataaccess.DALuchtvaartmaatschappij;
import hbo5.it.www.dataaccess.DAPersoon;
import hbo5.it.www.dataaccess.DAVliegtuig;
import hbo5.it.www.dataaccess.DAVlucht;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        } catch (SQLException e) {
        }}
    
    
    RequestDispatcher rd;
    HttpSession session = null;
    
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        session = request.getSession();
        try (PrintWriter out = response.getWriter()) {

              session.setAttribute("lijsthavens",  daLuchthaven.Get_naam_luchtHaven());
              session.setAttribute("lijstmaatschappijen",damaatschappij.Get_Names());
              session.setAttribute("lijstpersonen", dapersoon.get_names());
              session.setAttribute("lijstvliegtuigen",davliegtuig.getList_ids());
            if (request.getParameter("btnWijzig")!= null) {
                rd = request.getRequestDispatcher("LoginPage.jsp");
                    rd.forward(request, response);
            }
            else if (request.getParameter("btnVerwijder") != null) {
                rd = request.getRequestDispatcher("LoginPage.jsp");
                    rd.forward(request, response);
            }
            else{
                                  
                    
                    rd = request.getRequestDispatcher("StartAdmin.jsp");
                    rd.forward(request, response);
            }
            
            


                    
                  
        }
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
        session = request.getSession();
        session.setAttribute("VarLuchthaven", request.getParameter("LstHaven"));
        request.setAttribute("Luchthaven", daLuchthaven.getLuchthaven((String)session.getAttribute("VarLuchthaven")));
          request.getRequestDispatcher("overzichtLuchthavens.jsp").forward(request, response);
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
