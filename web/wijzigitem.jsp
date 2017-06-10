<%-- 
    HBO5 Programeren 4
    Document   : wijzigitem
    Created on : 5-jun-2017, 15:16:38
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Vliegtuig"%>
<%@page import="hbo5.it.www.beans.Luchtvaartmaatschappij"%>
<%@page import="hbo5.it.www.beans.Vliegtuigtype"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hbo5.it.www.beans.Luchthaven"%>
<%@page import="java.lang.String"%>
<%@page import="hbo5.it.www.beans.Leasemaatschappij"%>
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
  <%session = request.getSession();
                            String url= "";
                                if ("Admin".equals(session.getAttribute("paswoord"))) {
                                   url = "StartAdmin.jsp";}
                                else if("Director".equals(session.getAttribute("paswoord"))){
                                   url = "StartDirector.jsp";}
                                else{
                                    url = "index.jsp";}%>

                                    <a class="navbar-brand" href="<%=url%>" title="HOME"><i class="ion-paper-airplane"></i> Java <span>travel</span></a>                        </div> 
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
                                    <% session = request.getSession();
                                        if (session.getAttribute("L") != null) {
                                        Leasemaatschappij L =(Leasemaatschappij) session.getAttribute("L");%>

                                <table>
                                    <th>id</th>
                                    <th>naam</th>
                                    <%if ("lease".equals(request.getParameter("kind"))) {%>
                                    <% session.setAttribute("newItem", "Lease");%>
                                    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=L.getId()%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" value="<%=L.getNaam()%>" /></td> 
                                            
                                    </tr>
                                    <%}%>
                                    <%}%>
                                    <% if(session.getAttribute("ChosenHaven") != null) {
                                        Luchthaven LH = (Luchthaven) session.getAttribute("ChosenHaven");

                                        session.setAttribute("newItem", "Luchthaven"); %>

<table>
                                    <th>id</th>
                                    <th>naam</th>
                                    
                                    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=LH.getId()%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" value="<%=LH.getNaam()%>" /></td> 
                                            <td><input  type="text" name="txtstad" id="Naam" value="<%=LH.getStad()%>" /></td> 
                                    </tr>
<%}%>
                                     <%if (session.getAttribute("ChosenPlane") != null) {
                                         Vliegtuig V = (Vliegtuig) session.getAttribute("ChosenPlane");
                                         session.setAttribute("newItem", "vliegtuig");
                                     String select = "";%>
                                     
    <th>Leasemaatschappij</th>    
    <th>Luchtvaartmaatschappij</th>
    <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=V.getId()%>"/> </td>
                                            <td>
                                                <select name="LstType">
                                                    <% ArrayList<Vliegtuigtype> vlieglijst = (ArrayList<Vliegtuigtype>) session.getAttribute("lijstTypes");
                                                    for (Vliegtuigtype item : vlieglijst) {
                                                    %>
                                                    <% if (item.getId() == V.getVliegtuigtype_id()){select = "selected";}else{ select = "";}%>
                                                    <option value="<%=item.getId()%>" <%=select%> > <%=item.getNaam()%> </option>
                                                    <%}%>
 
                                                </select>
                                            </td> 
                                            <td>
                                                <select name="LstLease">
                                                    <% ArrayList<Leasemaatschappij> lijst = (ArrayList<Leasemaatschappij>) session.getAttribute("maatschappijen");
                                                    for (Leasemaatschappij item : lijst) {%>
                                                     <% if (item.getId() == V.getLeasemaatschappij_id()){select = "selected";}else{ select = "";}%>
                                                    <option value="<%=item.getId()%>" <%=select%>> <%=item.getNaam()%> </option>

<%}%>
                                                </select>
                                                
                                                <select name="LstMaatschappij">
                                                    <% ArrayList<Luchtvaartmaatschappij> luchtvaartlijst = (ArrayList<Luchtvaartmaatschappij>) session.getAttribute("lijstmaatschappijen");
                                                    for (Luchtvaartmaatschappij item : luchtvaartlijst) {%>
                                                      <% if (item.getId() == V.getLuchtvaartmaatschappij_id()){select = "selected";}else{ select = "";}%>
                                                    <option value="<%=item.getId()%>" <%=select%>> <%=item.getNaam()%> </option>
                                                    <%}%>
                                                </select>
                                            </td>
                                         
                                    </tr>
<%}%>







                                    <input type="submit" name="update">
                                </table>
                                                                        </form>

       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>








    </body>
</html>