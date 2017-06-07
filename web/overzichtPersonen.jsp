<%-- 
    Document   : overzichtPersonen
    Created on : 23-mei-2017, 14:19:49
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Persoon"%>
<%@page import="java.util.ArrayList"%>
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
                                <form  action="AdminServlet?choice=Persoon" method="POST">
                                <div class="form-group"> 
                                    <label for="LstPersonen">kies een persoon</label>
                                    <select onchange="this.form.submit()" class="form-control" name="LstPersonen" style="width: 50%; margin: 15px">
                                        <option selected="true"></option>
                                         <%ArrayList<String> lijst =(ArrayList<String>) session.getAttribute("lijstpersonen");%>
                                            <%for (String item : lijst) {%>
                                            <option value="<%=item%>" ><%=item%></option>
                                           <%}%>
                                    </select>
                                           
                                </div>           
                                           <%if (request.getAttribute("Persoon") != null) {%>
   

                                    </form>
                                           <%Persoon P = (Persoon) request.getAttribute("Persoon");
                                            session = request.getSession();
session.setAttribute("ChosenPerson", request.getAttribute("Persoon")); %>
                                    <form >
                                        <div>
                                            <label for="txtId">id</label>
                                            <input name="txtId" type="text" readonly="true" value="<%=P.getId()%>"/>
                                             <label for="txtVoornaam">Voornaam</label>
                                            <input name="txtVoornaam" type="text" value="<%=P.getVoornaam()%>"/>
                                            <label for="txtNaam">Naam</label>
                                            <input name="txtNaam" type="text" value="<%=P.getFamilienaam()%>"/>
                                            <label for="txtStraat">Straat</label>
                                            <input name="txtStraat" type="text" value="<%=P.getStraat()%>"/>
                                            <label for="txtHuisnr">huisnummer</label>
                                            <input name="txtHuisnr" type="text" value="<%=P.getHuisnr()%>"/>
                                             <label for="txtPostcode">postcode</label>
                                            <input name="txtPostcode" type="text" readonly="true" value="<%=P.getPostcode()%>"/>
                                             <label for="txtWoonplaats">woonplaats</label>
                                            <input name="txtWoonplaats" type="text" readonly="true" value="<%=P.getWoonplaats()%>"/>
                                             <label for="txtLand">land</label>
                                            <input name="txtLand" type="text" readonly="true" value="<%=P.getLand()%>"/>
                                            <label for="txtdatum">geboortedatum</label>
                                            <input name="txtdatum" type="date" value="<%=P.getGeboortedatum()%>"/>
                                             <label for="txtUser">Username</label>
                                            <input name="txtUser" type="text" readonly="true" value="<%=P.getLogin()%>"/>
                                             <label for="txtPas">Paswoord</label>
                                             <input name="txtPas" type="password" readonly="true" value="<%=P.getId()%>"/>
                                            <input type="submit" name="btnWijzig" value="Wijzig"/>
                                            <input type="submit" name="btnVerwijder" value="Verwijder"/>
                                        </div>
                                    </form>
     <%}%>                               
                                    
                                    
                                    
                                    
         
                                    
                                    
                                    
                                    
                                    
                                    
                                </div>
       
       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
                                           <%session.setAttribute("currentPage", "overzichtPersonen.jsp");%>

       </footer>








    </body>
</html>