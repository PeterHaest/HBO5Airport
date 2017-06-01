
<%-- 
    Document   : StartAdmin
    Created on : 10-mei-2017, 11:21:24
    Author     : c1040604
--%>


<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<% request.setAttribute("Login", session.getAttribute("username")); %>


<template:siteTemplate title="Passagier" status="${Login}">
    <jsp:attribute name="head">
        
    </jsp:attribute>
 
    <jsp:attribute name="content">
      <div class="row">
        <div class="col-sm-2">
            <nav class="nav-sidebar">
                
                 
                                     
                                        <ul class="nav">
                                            <li class="active"><a href="overzichtLuchthavens.jsp" id="Luchthavens">luchthavens</a></li>
                                            <li class="active"><a href="overzichtMaatschappijen.jsp" id="Maatschappijen">luchtvaartmaatschappijen</a></li>
                                            <li class="active"><a href="overzichtLease">eigenaarsgegevens (leasemaatschappij)</a></li>
                                            <li class="active"><a href="overzichtvliegtuigen.jsp">vliegtuigen</a></li>
                                            <li class="active"><a href="overzichtPersonen.jsp">personen</a></li>
                                            <li class="active"><a href="#">bemanning en vluchtbemanning</a></li>
                                            <li class="active"><a href="#">passagiers</a></li>
                                            <li class="active"><a href="#">hangars en vliegtuigen in hangars</a></li>
                                        </ul>
                                        
            </nav>
            
            
         
            
            
        </div>
    </div>  
        <div> 
        
      
      
      
      
      </div>  
    </jsp:attribute>
</template:siteTemplate>