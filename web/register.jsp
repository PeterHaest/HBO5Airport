<%-- 
    Document   : register.jsp
    Created on : 2-mei-2017, 9:18:21
    Author     : steve
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>


<%request.setAttribute("id",session.getAttribute("id"));%>
<%request.setAttribute("naam", session.getAttribute("naam"));%>
<%request.setAttribute("familienaam",  session.getAttribute("familienaam"));%>
<%request.setAttribute("straat",  session.getAttribute("straat"));%>
<%request.setAttribute("huisnummer",  session.getAttribute("huisnummer"));%>
<%request.setAttribute("postcode",  session.getAttribute("postcode"));%>
<%request.setAttribute("woonplaats",  session.getAttribute("woonplaats"));%>
<%request.setAttribute("land",  session.getAttribute("land"));%>
<%request.setAttribute("geboorte",  session.getAttribute("geboorte"));%>
<%request.setAttribute("username",  session.getAttribute("username"));%>
<%request.setAttribute("paswoord",  session.getAttribute("paswoord"));%>
<template:siteTemplate title="Home" status="Login">
    <jsp:attribute name="head">
        
    </jsp:attribute>
    <jsp:attribute name="content">
      
       
        <div class="container">
             <form action="RegisterServlet" method="post">
            <table style="margin:25%">
                
                <tr>
                    <td><p>id</p></td>
                    <td><input type="text" name="id" value="${id}"  readonly="true"/></td>
                </tr>
                 <tr>
                    <td><p>Voornaam</p></td>
                    <td><input type="text" name="voornaam" value="${naam}"/></td>
                </tr>
                 <tr>
                    <td><p>Familienaam</p></td>
                    <td><input type="text" name="familienaam" value="${familienaam}"/></td>
                </tr>
                 <tr>
                    <td><p>Straat</p></td>
                    <td><input type="text" name="straat" value="${straat}"/></td>
                </tr> <tr>
                    <td><p>huisnummer</p></td>
                    <td><input type="text" name="huisnummer" value="${huisnummer}"/></td>
                </tr>
                 <tr>
                    <td><p>postcode</p></td>
                    <td><input type="text" name="postcode" value="${postcode}"/></td>
                </tr>
                 <tr>
                    <td><p>woonplaats</p></td>
                    <td><input type="text" name="woonplaats" value="${woonplaats}"/></td>
                </tr>
                 <tr>
                    <td><p>land</p></td>
                    <td><input type="text" name="land" value="${land}"/></td>
                </tr>
                 <tr>
                    <td><p>geboorte</p></td>
                    <td><input type="date" name="geboorte" value="${geboorte}" data-date-format="DD MM YYYY"/></td>
                </tr>
                <tr>
                    <td><p>Username</p></td>
                    <td><input type="text" name="Username" value="${username}"/></td>
                </tr>
                <tr>
                    <td><p>Wachtwoord</p></td>
                    <td><input type="text" name="Paswoord" value="${paswoord} "/></td>
                </tr>
           
           
            <tr>
                <td colspan="2" style=""><input type="submit" name="registreer" value="registreer"/></td> 
            </tr>
         
            
            
            
        </table>
                            </form>

        </div>
        
        
        
        
    </jsp:attribute>
    <jsp:attribute  name="footer">
    </jsp:attribute>
</template:siteTemplate>
