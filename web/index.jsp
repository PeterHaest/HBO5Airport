

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>




<template:siteTemplate title="Home" status="${Login}">


    <jsp:attribute name="head">
        
    </jsp:attribute>
    
    <jsp:attribute name="content">
    <div class="row">
        <div class="col-sm-2">
            <nav class="nav-sidebar">
                <ul class="nav">
                    <li class="active"><a href="javascript:;">Overzicht geboekte vluchten</a></li>
                    <li class="active"><a href="javascript:;">Vluchten boeken</a></li>
                    <li class="active"><a href="javascript:;">Geboekte vluchten annuleren</a></li>
                </ul>
            </nav>
        </div>
    </div>    
        <p>${Login}</p>
        
        
        
     
       

    </jsp:attribute>
    

</template:siteTemplate>

