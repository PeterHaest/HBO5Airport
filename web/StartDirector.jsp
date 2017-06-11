<%-- 
    Document   : StartDirector
    Created on : 10-mei-2017, 11:21:40
    Author     : c1040604
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<template:siteTemplate title="Passagier" status="Uitloggen">
    <jsp:attribute name="head">
        
    </jsp:attribute>
  
    <jsp:attribute name="content">
      <div class="row">
        <div class="col-sm-2">
            <nav class="nav-sidebar">
                <ul class="nav">
                    <li class="active"><a href="ZoekServlet?choice=huidigeVluchten">Overzicht geboekte vluchten</a></li>
                    <li class="active"><a href="ZoekServlet?choice=toekomstigeVluchten">Vluchten boeken</a></li>
                    <li class="active"><a href="javascript:;">Geboekte vluchten annuleren</a></li>
                </ul>
            </nav>
        </div>
    </div>  
      
    </jsp:attribute>

</template:siteTemplate>