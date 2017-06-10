<%-- 
    HBO5 Programeren 4
    Document   : newitem
    Created on : 5-jun-2017, 13:45:19
    Author     : steve
--%>


<%@page import="java.util.AbstractList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="hbo5.it.www.beans.Bemanningslid"%>

<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.util.Calendar"%>

<%@page import="oracle.sql.Datum"%>
<%@page import="java.time.Instant"%>
<%@page import="hbo5.it.www.beans.Luchtvaartmaatschappij"%>
<%@page import="hbo5.it.www.beans.Vliegtuigtype"%>
<%@page import="hbo5.it.www.beans.Vliegtuig"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hbo5.it.www.beans.Leasemaatschappij"%>
<%@page import="hbo5.it.www.beans.Luchthaven"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .footer{
            position:absolute;
    	width:100%;
        bottom:0;
    	height:60px;
        }
    </style>
    <head>
		<!-- meta -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/ionicons.min.css">
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/owl.theme.css">
	<link rel="stylesheet" href="css/flexslider.css" type="text/css">
        <link rel="stylesheet" href="css/main.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      
</head>
    <body>
        
        <div>
        <nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
                        <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
	<a class="navbar-brand" href="index.jsp" title="HOME"><i class="ion-paper-airplane"></i> Java <span>travel</span></a>
                        </div> 
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">vluchtoverzicht <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Inkomende vluchten</a></li>
                                            <li><a href="#">Uitgaande vluchten</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#"> Zoeken </a></li>   
                                    <li><a href="LoginPage.jsp"><i class="ion-person"></i>${status}</a></li>
				</ul> 
		    </div>
	  	</div>
	</nav>
 
  
                                </div>
                                <form action="AdminServlet?choice=submit" method="get" >

                                   <div class="container">
     <table class="table table-responsive table-striped ">
         <thead class="thead-inverse">
             <tr>

                                    <th>id</th>
                                    <th>naam</th>
                                    <%if ("lease".equals(request.getParameter("kind"))) {%>
                                    <%session.setAttribute("newItem", "Lease");%>

             </tr>
         </thead>

                                    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" /></td> 
                                    </tr>
                                    <%}%>
                                 
   


                                    <% if ("Luchthaven".equals(request.getParameter("kind"))){ 
                                        session.setAttribute("newItem", "Luchthaven");

                                    %>
                                            
                                            <th>stad</th>
                                            <tr>
                                                <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                                <td><input  type="text" name="txtnaam" value="" /></td>
                                                <td><input type="text" name="txtstad" value=""/></td>
                                            </tr>
                                       <%}%>     
                                            

                                       <%if ("luchtvaartmaatschappij".equals(request.getParameter("kind"))) {
                                           session.setAttribute("newItem", "luchtvaartmaatschappij");%>

<tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" /></td> 
                                    </tr>
<%}%>
    <%if ("vliegtuig".equals(request.getParameter("kind"))) {
    session.setAttribute("newItem", "vliegtuig");%>
    <th>Leasemaatschappij</th>    
    <th>Luchtvaartmaatschappij</th>
    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                            <td>
                                                <select name="LstType">
                                                    <% ArrayList<Vliegtuigtype> vlieglijst = (ArrayList<Vliegtuigtype>) session.getAttribute("lijstTypes");
                                                    for (Vliegtuigtype item : vlieglijst) {%>
                                                    <option value="<%=item.getId()%>"> <%=item.getNaam()%> </option>
                                                    <%}%>
 
                                                </select>
                                            </td> 
                                            <td>
                                                <select name="LstLease">

                                                    <option value="null"> niet in lease</option>

                                                    <% ArrayList<Leasemaatschappij> lijst = (ArrayList<Leasemaatschappij>) session.getAttribute("maatschappijen");
                                                    for (Leasemaatschappij item : lijst) {%>
                                                    <option value="<%=item.getId()%>"> <%=item.getNaam()%> </option>

<%}%>
                                                </select>
                                                
                                                <select name="LstMaatschappij">
                                                    <% ArrayList<Luchtvaartmaatschappij> luchtvaartlijst = (ArrayList<Luchtvaartmaatschappij>) session.getAttribute("lijstmaatschappijen");
                                                    for (Luchtvaartmaatschappij item : luchtvaartlijst) {%>
                                                    <option value="<%=item.getId()%>"> <%=item.getNaam()%> </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                    </tr>
<%}%>
<%if("vlucht".equals(request.getParameter("kind"))){%>
     <%session.setAttribute("newItem", "vlucht");%>

<table>
    <th>id</th>
    <th>code</th>

    <th>vertrekdatum</th>
    <th>Aankomst</th>
    <th>Vliegtuigtype</th>
    <th>vertrekluchthaven</th>
    <th>aankomstluchthaven</th>

    </tr>
</thead>
<tbody>
    <tr>
<div>       
        <td ><input class="text-capitalize text-center form-control" type="text" readonly="true" name="txtid" value="<%=request.getAttribute("topId")%>"> </td>
        <td><input type="text" name="txtCode" class="form-control"></td>

        <td><input type="date" name="txtVertrek" class="form-control text-center text-uppercase" ></td>
        </div>
        <td><input type="date" name="txtAankomst" class="form-control text-center text-uppercase"></td>
        <td>
             <select name="LstType" class="form-control  text-center text-uppercase">

                                                    <% ArrayList<Vliegtuigtype> vlieglijst = (ArrayList<Vliegtuigtype>) session.getAttribute("lijstTypes");
                                                    for (Vliegtuigtype item : vlieglijst) {
                                                    %>
                                                    <option value="<%=item.getId()%>"> <%=item.getNaam()%> </option>
                                                    <%}%>
 
                                                </select>
        </td>
           <td>
               <select name="LstVertrek" class="form-control ">

                                        <option ></option>
                                        <%ArrayList<Luchthaven> luchtlijst =(ArrayList<Luchthaven>) session.getAttribute("lijsthavens");%>
                                        <%for (Luchthaven item : luchtlijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                            </td>
                                               <td>

                                                <select name="LstAankomst" class="form-control ">

                                        <option></option>
                                        
                                        <%for (Luchthaven item : luchtlijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                            </td>

                                    <!--       <td>
                                                <%ArrayList<Luchtvaartmaatschappij> lijstmaatschappijen = (ArrayList < Luchtvaartmaatschappij >)session.getAttribute("lijstmaatschappijen");%>
                                                <select name="maatschappij">
                                                    <option></option>   
                                                    <%for (Luchtvaartmaatschappij item : lijstmaatschappijen) {%>
                                                         <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
<%}%> -->
                                                </select>
                                            </td>
    </tr>
    </tbody>
</table>

                                                
                                              
                                                <table class="table">   
                                       <%Map<Integer,ArrayList<String>> nMap = (Map<Integer,ArrayList<String>>) session.getAttribute("lijstBemanning");%>
                                      
                                       <%for (Map.Entry<Integer,ArrayList<String>> entry : nMap.entrySet()) {%>
                                       <%ArrayList<String> list = entry.getValue();%>
                                       <tr>
                                           <td><%=entry.getKey()%></td>
                                         
                                           <%for (String elem : list) {%>
                                           <td><%=elem%></td>
                                           <%}%>
                                           
                                           <td><input type="checkbox" name="<%=entry.getKey()%>" id="Onflight"  ></td>
                                       </tr>    
<%}%>
    </table>   

                                                      
    
    
    
               <%}%>                     
               <div class="container text-center"> 
                   <input type="submit" name="nieuw" class="btn-lg ">
                                        </div>   
                                                                        </form>
 </div>   


       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>








    </body>
</html>
