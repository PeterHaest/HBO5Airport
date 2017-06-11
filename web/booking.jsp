<%-- 
    HBO5 Programeren 4
    Document   : booking
    Created on : 11-jun-2017, 14:51:04
    Author     : steve
--%>


<%@page import="hbo5.it.www.beans.Vliegtuigklasse"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
		<!-- meta -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
	
        <title>${title}</title>

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
		<!-- Brand and toggle get grouped for better mobile display -->
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
			</div> <!-- /.navbar-header -->

	    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
                                    <%if("Director".equals(session.getAttribute("paswoord"))){%>
                                        <li><a href="ZoekServlet?Zoeken=statistieken&Search=Luchthaven">Statistieken</a></li>
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
				</ul> <!-- /.nav -->
		    </div><!-- /.navbar-collapse -->
	  	</div><!-- /.container -->
	</nav>
       </div> 
     
                                
                                
                                    <%ArrayList<Vliegtuigklasse> lijst =(ArrayList<Vliegtuigklasse>) session.getAttribute("vliegtuigklasses");%>
                                  
                                
                                    <div class="container">
                                        <form action="ZoekServlet?choice=geboekt">
                                        <table class="table table-responsive">
                                        <tr>
                                            <td>vlucht</td>
                                            <td><%=session.getAttribute("gekozenvlucht")%></td>
                                        </tr>
                                        <tr>
                                            <td>naam</td>
                                            <td><%=session.getAttribute("naam")%> <%=session.getAttribute("familienaam")%></td>
                                        </tr>
                                        <tr>
                                            <td>klasse</td>
                                            <td>
                                        <select name="LstKlasse">
                                            <%for (Vliegtuigklasse elem : lijst) {%>
                                            <option value="<%=elem.getId()%>"><%=elem.getNaam()%></option>
<%}%>
                                        </select>
                                        </td>
                                        </tr>
                                        <tr>
                                            <td>voorkeur stoel</td>
                                            <td> <input type="text" name="txtStoel" class="form-control"/></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="submit" name="Zoeken" value="Booking" class="btn-lg ">
                                            </td>
                                            
                                        </tr>
                                    </table>
                                </div>
</form>
                       
       
       <footer>
           <div class="container">
			<div class="row">
				<div class="col-xs-4">
					<div class="text-left">
						&copy; Copyright Java Travels
					</div>
				</div>
				<div class="col-xs-4">
					Project gemaakt door team 2 (Steve Dekerf, Tijs Torfs en Peter Haest)
				</div>
			</div>
		</div>	
       </footer>








    </body>
</html>
