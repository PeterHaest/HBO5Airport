<%-- 
    Document   : overzichtLuchthavens
    Created on : 23-mei-2017, 14:19:49
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Luchthaven"%>
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
                                 <h1 class="tour section-wrapper container section-title">overzicht van alle luchthavens</h1>
                                <div class="container ">
                                <form  action="AdminServlet?choice=Luchthaven" method="POST">
                                  
                                 
                                    <label for="LstHaven">kies een luchthaven</label>
                                    <select onchange="this.form.submit()" class="form-control" name="LstHaven" style="width: 50%; margin: 15px">
                                        <option selected="true"></option>
                                         <%ArrayList<Luchthaven> lijst =(ArrayList<Luchthaven>) session.getAttribute("lijsthavens");%>
                                            <%for (Luchthaven item : lijst) {%>
                                            <option value="<%=item.getId()%>" ><%=item.getNaam()%></option>
                                           <%}%>
                                    </select>
                                          
                                           
                                           <%if (request.getAttribute("Luchthaven") != null) {%>
   

                                    </form>
                                          
                                           <form action="AdminServlet" method="get" >
                                         <%Luchthaven L = (Luchthaven) request.getAttribute("Luchthaven");%>
                                         <%session = request.getSession();
                                         session.setAttribute("ChosenHaven", L);%>
                                         
                                         <div class="" >
                                             <table class="table tour ">
                                                 <tr>
                                                     <th><label for="txtId" class="section-subtitle">id</label></th>
                                                     <%session.setAttribute("txtid", L.getId());%>
                                                     <td><input name="txtId" type="text" readonly="true" value="<%=L.getId()%>"/></td>
                                            </tr>
                                            <tr>
                                                <th><label for="txtNaam" class="section-subtitle">Naam</label></th>
                                                <td><input name="txtNaam" type="text" value="<%=L.getNaam()%>"/></td>
                                            </tr>
                                            <tr>
                                                <th> <label for="txtStad" class="section-subtitle">Stad</label></th>
                                                <td><input type="text" name="txtStad" value="<%=L.getStad()%>"/></td>
                                            </tr>
                                            </table>
                                        
                                             <%}%>  
                                             <div class="container ">
                                                 <tr>
                                                     <td>  <button class="btn-default"><a href=AdminServlet?choice=add&kind=Luchthaven>Nieuwe Luchthaven</a></button></td> 
                                                     <td><button class="btn-default"><a href=AdminServlet?choice=update&kind=Luchthaven>Gegevens wijzigen</a></button></td>
                                                     <td> <button class="btn-default"><a href=AdminServlet?choice=delete&kind=Luchthaven>wissen</a></button></td>
                                             </tr>
                                         </div>
                                    </form>
                                 
                                    
                                    
                                    
                                    
         
                                    
                                    
                                    
                                    
                                    
                                    
                            
       </div>
       <footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>
           
       </footer>






    <%session.setAttribute("currentPage", "overzichtLuchthavens.jsp");%>

    </body>
</html>