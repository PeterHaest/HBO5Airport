<%-- 
    HBO5 Programeren 4
    Document   : overzichtHangars
    Created on : 4-jun-2017, 23:43:13
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Hangar"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="hbo5.it.www.beans.Passagier"%>
<%@page import="hbo5.it.www.beans.Crew"%>
<%@page import="hbo5.it.www.beans.Vlucht"%>
<%@page import="hbo5.it.www.beans.Persoon"%>
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
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <<%session = request.getSession();
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
    
    <form  action="AdminServlet?choice=Hangar" method="Post">
        <h1 class="tour section-wrapper container section-title">Overzicht van alle hangaren.</h1>
        <div class="form-group col-md-offset-2">
            <label for="LstHangar">kies een Hangaar</label>
            <select onchange="this.form.submit()" class="form-control select" name="LstHangar">
                <option selected="true"></option>

                <%ArrayList<String> lijst =(ArrayList<String>) session.getAttribute("lijsthangarnamen");%>
                <%for (String item : lijst) {%>

                <option value="<%=item%>" ><%=item%></option>

                <%}%>

            </select>
        </div>           
    </form>



 <%if (request.getAttribute("InhoudHangar") != null) {%>
 <%Map<Integer,ArrayList<String>> inhoud = (Map<Integer,ArrayList<String>>) request.getAttribute("InhoudHangar");%>
    <%Set set = inhoud.entrySet();%>
 <% Iterator iterator = set.iterator();%>
 <div class="form-group col-md-offset-2">
    <table>
        <th>id</th>
        <th>Type</th>
        <th>Reden</th>
        <th>van</th>
        <th>tot</th>
        <% while (iterator.hasNext()){%>
            <tr>

                <%Map.Entry mentry = (Map.Entry) iterator.next();
                Integer id =(Integer) mentry.getKey(); %>

                    <td><%=id%></td>

                <%ArrayList<String> stringlijst = (ArrayList<String>) mentry.getValue();%>
                <%for (String item : stringlijst) {%>

                    <td><%=item%></td>
                <%}%>
                    <td>wijzig</td>
                    <td> verwijder</td>
            </tr>
        <%}%>
    </table>

<%}%>
 </div>
<div class="form-group col-md-offset-2">
    <input type="submit" name="btnWijzig" value="Wijzig"/>
    <input type="submit" name="btnVerwijder" value="Verwijder"/>                                  
</div>
       
    <footer>
        <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>


    <%session.setAttribute("currentPage", "overzichtHangars.jsp");%>

    </footer>

    </body>
</html>
