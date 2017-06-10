<%-- 
    HBO5 Programeren 4
    Document   : deleteitem
    Created on : 5-jun-2017, 19:31:34
    Author     : steve
--%>

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
                                    <% session = request.getSession();
                                        if (session.getAttribute("L") != null) {
                                        Leasemaatschappij L =(Leasemaatschappij) session.getAttribute("L");
                                        session.setAttribute("delItem", "Lease"); %>

                                        <h3> bent u zeker dat u <%=L.getNaam()%> wilt wissen?
                                             <table>
                                                <th>id</th>
                                                <th>naam</th>
                                     <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=L.getId()%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" value="<%=L.getNaam()%>" /></td> 
                                    </tr>
                                            <%}%>
                                            <%  if (session.getAttribute("ChosenHaven") != null) {
                                                Luchthaven LH = (Luchthaven) session.getAttribute("ChosenHaven");

                                            session.setAttribute("delItem", "Luchthaven"); %>

                                            <h3> bent u zeker dat u <%=LH.getNaam()%> wilt wissen?
                                             <table>
                                                <th>id</th>
                                                <th>naam</th>
                                     <tr>
                                            <td><input type="text" name="txtid" readonly="true" value="<%=LH.getId()%>"/> </td>
                                            <td><input  type="text" name="txtnaam" id="Naam" value="<%=LH.getNaam()%>" /></td> 
                                    </tr>
                                                
<%}%>
                               
                                  
   
                                    
                                    <input type="submit" name="delete">
                                    <input type="submit" name="home">
                                </table>
                                                                        </form>

       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>








    </body>
</html>