
<%-- 
    Document   : overzichtMaatschappijen
    Created on : 23-mei-2017, 14:19:49
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Luchtvaartmaatschappij"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

                                    <a class="navbar-brand" href="<%=url%>" title="HOME"><i class="ion-paper-airplane"></i> Java <span>travel</span></a>
                        </div> 
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <%if("Director".equals(session.getAttribute("paswoord"))){%>
                                        <li><a href="ZoekServlet?Zoeken=statistieken">Statistieken</a></li>
                                            <%}%>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">vluchtoverzicht <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="ZoekServlet?Zoeken=inkomend">Inkomende vluchten</a></li>
                                            <li><a href="ZoekServlet?Zoeken=uitgaand">Uitgaande vluchten</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="zoektest.jsp"> Zoeken </a></li>      
                                    <li><a href="LoginPage.jsp"><i class="ion-person"></i>${status}</a></li>
				</ul> 
		    </div>
	  	</div>
	</nav>
 
  
                                </div>



                                <form  action="AdminServlet?choice=luchtvaartmaatschappij" method="POST">

                                <div class="form-group"> 

                                    <h1 class=""></h1>
                                <div class="form-group ">
                                    <h1 class="tour section-wrapper container section-title">overzicht van alle luchtvaartmaatschappijen</h1>

                                    <label for="LstMaatschappij">kies een maatschappij</label>
                                    <select onchange="this.form.submit()" class="form-control select" name="LstMaatschappij">
                                        <option selected="true"></option>
                                         <%ArrayList<Luchtvaartmaatschappij> lijst =(ArrayList<Luchtvaartmaatschappij>) session.getAttribute("lijstmaatschappijen");%>
                                            <%for (Luchtvaartmaatschappij item : lijst) {%>
                                            <option value="<%=item.getNaam()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                           
                                </div>           
                                           <%if (request.getAttribute("Maatschappij") != null) {%>
   

                                    </form>
                                           <%Luchtvaartmaatschappij L = (Luchtvaartmaatschappij) request.getAttribute("Maatschappij");%>
                                           <form action="AdminServlet" >
                                        <div>
                                            <label for="txtId">id</label>
                                            <input name="txtId" type="text" readonly="true" value="<%=L.getId()%>"/>


                                            <%session.setAttribute("txtid", L.getId());%>


                                            <label for="txtNaam">Naam</label>
                                            <input name="txtNaam" type="text" value="<%=L.getNaam()%>"/>
                                         <%}%> 
                                        </div>


                                            <td><a href=AdminServlet?choice=add&kind=luchtvaartmaatschappij>Nieuwe Luchthavenmaatschappij</a></td>
                                            <td><a href=AdminServlet?choice=update&kind=luchtvaartmaatschappij>Gegevens wijzigen</td>
                                            <td><a href=AdminServlet?choice=delete&kind=luchtvaartmaatschappij>wissen</td>


                                    </form>
                                  
                                    
                                    
                                    
                                    
         
                                    
                                    
                                    
                                    
                                    
                                    
                                </div>
       
       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>






    <%session.setAttribute("currentPage", "overzichtMaatschappijen.jsp");%>


    </body>
