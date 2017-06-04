<%--    HBO5 Programeren 4
    Document   : index
    Created on : 23-apr-2017, 17:33:50
    Author     : steve
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>


<% request.setAttribute("Login", session.getAttribute("Login")); %>

<template:siteTemplate title="Home" status="${Login}">


    <jsp:attribute name="head">
        
    </jsp:attribute>
    
    <jsp:attribute name="content">
         <section class="tour section-wrapper container">
		<h2 class="section-title">
			Find a Tour
		</h2>
		<p class="section-subtitle">
			Where would you like to go?
		</p>
		<div class="row">
			<div class="col-md-3 col-sm-6">
				<form action="ZoekServlet" role="form" class="form-dropdown">
					<div class="form-group">
						<label for="sel1">Select list (select one):</label>
						<select name="optie" class="form-control border-radius" id="sel1">
							<option value="vluchtnummer">Vluchtnummer</option>
							<option value="datum">Datum</option>
							<option value="bestemming">Bestemming</option>
							<option value="luchtvaartmaatschappij">Luchtvaartmaatschappij</option>
						</select>
					</div>
			</div>

			<div class="col-md-3 col-sm-6">
				<div class="input-group">
					<input name="input" type="text" class="form-control border-radius border-right"/>
					<span class="input-group-addon border-radius custom-addon">
						<i class="ion-ios-calendar"></i>
					</span>
				</div>
			</div>

			<div class="col-md-3 col-sm-6">
                            <input type="submit" name="Zoeken" value="Zoeken" class="btn btn-default">
			</div>
                                </form>
		</div>
	</section> <!-- /.tour -->
    </jsp:attribute>
    

</template:siteTemplate>

