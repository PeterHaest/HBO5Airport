<%-- 
    HBO5 Programeren 4
    Document   : index
    Created on : 23-apr-2017, 17:33:50
    Author     : steve
--%>

<%@page import="hbo5.it.www.beans.Vlucht"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

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
				<a class="navbar-brand" href="index.jsp" title="HOME"><i class="ion-paper-airplane"></i> Java <span>travel</span></a>
			</div> <!-- /.navbar-header -->

	    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">vluchtoverzicht <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="ZoekServlet?Luchthaven=1&Zoeken=inkomend">Inkomende vluchten</a></li>
                                            <li><a href="ZoekServlet?Luchthaven=1&Zoeken=uitgaand">Uitgaande vluchten</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="zoektest.jsp"> Zoeken </a></li>   
                                    <li><a href="LoginPage.jsp"><i class="ion-person"></i>${status}</a></li>
				</ul> <!-- /.nav -->
		    </div><!-- /.navbar-collapse -->
	  	</div><!-- /.container -->
	</nav>
        </div>
         <section class="tour section-wrapper container">
		<h2 class="section-title">
			Find a Tour
		</h2>
		<p class="section-subtitle">
			Where would you like to go?
		</p>
		<div class="row">
			<div class="col-md-3 col-sm-6">
				<form action="ZoekServlet" role="form" class="form-dropdown">
					<div class="form-group">
						<label for="sel1">Select list (select one):</label>
						<select name="optie" class="form-control border-radius" id="sel1">
							<option value="vluchtnr">Vluchtnummer</option>
							<option value="datum">Datum</option>
							<option value="bestemming">Bestemming</option>
							<option value="luchtvaartmaatschappij">Luchtvaartmaatschappij</option>
						</select>
					</div>
			</div>

			<div class="col-md-3 col-sm-6">
				<div class="input-group">
					<input name="input" type="text" class="form-control border-radius border-right"/>
					<span class="input-group-addon border-radius custom-addon">
						<i class="ion-ios-calendar"></i>
					</span>
				</div>
			</div>

			<div class="col-md-3 col-sm-6">
                            <input type="submit" name="Zoeken" value="Zoeken" class="btn btn-default">
			</div>
                                </form>
		</div>
	</section> <!-- /.tour -->
        
<footer>
           <p>Project gemaakt door team 2 (Steve Dekerf, Peter Haest and Tijs Torfs)</p>          
       </footer>
    </body>
</html>

