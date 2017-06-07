<%-- 
    Document   : overzichtvliegtuigen
    Created on : 23-mei-2017, 14:19:49
    Author     : steve
--%>


<%@page import="hbo5.it.www.beans.Vliegtuig"%>
<%@page import="hbo5.it.www.beans.Vliegtuig_extend"%>
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
                                <form  action="AdminServlet?choice=Vliegtuig" method="POST">
                                <div class="form-group"> 
                                    <label for="LstVliegtuigen">kies een vliegtuig</label>
                                    <select onchange="this.form.submit()" class="form-control" name="LstVliegtuigen" style="width: 50%; margin: 15px">
                                        <option selected="true"></option>
                                         <%ArrayList<Vliegtuig> lijst =(ArrayList<Vliegtuig>) session.getAttribute("lijstvliegtuigen");%>
                                            <%for (Vliegtuig item : lijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getId()%></option>
                                           <%}%>
                                    </select>
                                           
                                </div>           
                                           <%if (request.getAttribute("Vliegtuig") != null) {%>
   

                                    </form>
                                           <%Vliegtuig_extend V = (Vliegtuig_extend) request.getAttribute("Vliegtuig");
                                           
                                           %>
                                           
                                           
                                    <form >
                                        <div>
                                            <input  name="txtvliegtuigid" hidden="true" value="<%=V.getVliegtuigtype_id() %>"/>
                                             <input  name="txtLeaseid" hidden="true" value="<%=V.getLeasemaatschappij_id()%>"/>
                                              <input  name="txtmaatschappijid" hidden="true" value="<%=V.getLuchtvaartmaatschappij_id()%>"/>
                                              <%session.setAttribute("currentId", V.getId());%>
                                            <label for="txtType">VliegtuigType</label>
                                            <input name="txtType" type="text" value="<%=V.getType_naam()%>"/>
                                            <label for="txtMaatschappij">Vliegtuig Maatschappij</label>
                                            <input name="txtMaatschappij" type="text" value="<%=V.getMaatschappij_naam()%>"/>
                                             <label for="chkLeased">Leased?</label>
                                             <% if (V.isLeased()){%>
                                             <input name="chkLeased" type="checkbox" checked/>                  
                                             <input name="txtMaatschappij" type="text" value="<%=V.getLease_maatschappij_naam()%>"/>
                                             <label for="chkLeased">Leased?</label>
                                             
                                             <%} else{%>
                                              <input name="chkLeased" type="checkbox" />
                                              <%}%>
                                             <%}%>    
                                             <td><a href=AdminServlet?choice=add&kind=vliegtuig>Nieuw vliegtuig</a></td>
                                            <td><a href=AdminServlet?choice=update&kind=vliegtuig>Gegevens wijzigen</td>
                                            <td><a href=AdminServlet?choice=delete&kind=vliegtuig>wissen</td>
                                        </div>
                                    </form>
                              
                                    
                                    
                                    
                                    
         
                                    
                                    
                                    
                                    
                                    
                                    
                                </div>
       
       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>






                                <%session.setAttribute("currentPage", "overzichtvliegtuigen.jsp");%>

    </body>
</html>