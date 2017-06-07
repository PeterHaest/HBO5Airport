<%-- 
    HBO5 Programeren 4
    Document   : newitem
    Created on : 5-jun-2017, 13:45:19
    Author     : steve
--%>

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
                                    
                                <table>
                                    <th>id</th>
                                    <th>naam</th>
                                    <%if ("lease".equals(request.getParameter("kind"))) {%>
                                    <%session.setAttribute("newItem", "Lease");%>
                                    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" /></td> 
                                    </tr>
                                    <%}%>
                                 
   

                                    <% if ("haven".equals(request.getParameter("kind"))){ 
                                        session.setAttribute("newItem", "Haven");
                                    %>
                                            
                                            <th>stad</th>
                                            <tr>
                                                <td><input type="text" name="txtid" readonly="true" value="<%=request.getAttribute("topId")%>"/> </td>
                                                <td><input  type="text" name="txtnaam" value="" /></td>
                                                <td><input type="text" name="txtstad" value=""/></td>
                                            </tr>
                                       <%}%>     
                                            
                                       <%if ("maatschappij".equals(request.getParameter("kind"))) {
                                           session.setAttribute("newItem", "maatschappij");%>
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
    
    <tr>
        <td><input type="text" readonly="true" name="txtid" value="<%=request.getAttribute("topId")%>"> </td>
        <td><input type="text" name="txtCode"></td>
        <td><input type="date" name="txtVertrek"  ></td>
        <td><input type="date" name="txtAankomst"></td>
        <td>
             <select name="LstType">
                                                    <% ArrayList<Vliegtuigtype> vlieglijst = (ArrayList<Vliegtuigtype>) session.getAttribute("lijstTypes");
                                                    for (Vliegtuigtype item : vlieglijst) {
                                                    %>
                                                    <option value="<%=item.getId()%>"> <%=item.getNaam()%> </option>
                                                    <%}%>
 
                                                </select>
        </td>
           <td>
                                                <select name="LstVertrek">
                                        <option ></option>
                                        <%ArrayList<Luchthaven> luchtlijst =(ArrayList<Luchthaven>) session.getAttribute("lijsthavens");%>
                                        <%for (Luchthaven item : luchtlijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                            </td>
                                               <td>
                                                <select name="LstAankomst">
                                        <option></option>
                                        
                                        <%for (Luchthaven item : luchtlijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                            </td>
    </tr>
    
</table>
    
    
    
<%}%>






                                </table>
                                
                                        <input type="submit" name="nieuw">
                                                                        </form>


       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>








    </body>
</html>
