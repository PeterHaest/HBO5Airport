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
			Vind een vlucht !
		</h2>
		<p class="section-subtitle">
			Waar zou jij graag naartoe willen gaan?
		</p>
		<div class="row col-md-offset-3">
			<div class="col-md-3 col-sm-6">
				<form action="ZoekServlet" role="form" class="form-dropdown">
					<div class="form-group">
						<label for="Opt">Kies zoekwijze:</label>
						<select name="optie" class="form-control border-radius" id="option">
							<option value="vluchtnummer">Vluchtnummer</option>
							<option value="datum">Datum</option>
							<option value="bestemming">Bestemming</option>
							<option value="luchtvaartmaatschappij">Luchtvaartmaatschappij</option>
						</select>
					</div>
			</div>
                    <script>
                        var select = document.getElementById("option");
                            select.onchange=function(){
                                if(select.value=="datum"){
                                   document.getElementById("date").style.display="inline";
                                   document.getElementById("input").style.display="none";
                                }else{
                                   document.getElementById("date").style.display="none";
                                   document.getElementById("input").style.display="inline";
                                }

                            }
                    </script>
			<div id="input" class="col-md-3 col-sm-6">
				<div class="input-group">
					<input name="input" type="text" class="form-control border-radius border-right"/>
					<span class="input-group-addon border-radius custom-addon">
						<i class="ion-ios-calendar"></i>
					</span>
				</div>
			</div>
                    
                        <div id="date" class="col-md-3 col-sm-6 date">
				<div class="input-group">
					<input name="date" type="date" class="form-control border-radius border-right"/>
					<span class="input-group-addon border-radius custom-addon">
						<i class="ion-ios-calendar"></i>
					</span>
				</div>
			</div>

			<div class="col-md-3 col-sm-6">
                            <input type="submit" name="Zoeken" value="Zoeken" class="btn btn-default btn-lg">
			</div>
                                </form>
		</div>
	</section> <!-- /.tour -->
        
    </jsp:attribute>
    

</template:siteTemplate>

