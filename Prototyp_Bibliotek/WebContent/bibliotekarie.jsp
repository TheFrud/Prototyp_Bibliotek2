<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList" import="se.prototyp.services.GetLiteratureService"
    import="java.text.SimpleDateFormat" import="java.util.Date"
    import="java.util.Collections" import="se.prototyp.services.GetLoansService"%>
    
<!DOCTYPE html>
<html>	
<head>

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/mystyles.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>	
    <script src="js/myscripts.js"></script>
	<meta http-equiv="Content-Type" name="viewport" content="width=device-width">
	<title>Bibliotek Informatika</title>

	<link rel="shortcut icon" href="http://www.odontologi.gu.se/kirurgi/img/gu_logga.png">
</head>
<body>

<%
// Vi spar ner användarinformationen från sessionen i variabler som vi kan använda på sidan.
String sparatAnvandarnamn = (String) session.getAttribute("sparatAnvandarnamn");
String sparatLosenord = (String) session.getAttribute("sparatLosenord");
String sparatPersonnummer = (String) session.getAttribute("sparatPersonnummer");
String sparatFornamn = (String) session.getAttribute("sparatFornamn");
String sparatEfternamn = (String) session.getAttribute("sparatEfternamn");
String sparadGatuadress = (String) session.getAttribute("sparadGatuadress");
String sparadStad = (String) session.getAttribute("sparadStad");
String sparadTelefon = (String) session.getAttribute("sparadTelefon");
String sparadEpost = (String) session.getAttribute("sparadEpost");
String sparadRoll = (String) session.getAttribute("sparadRoll");

%>

<% 
//Tittar om anvÃ¤ndaren redan har en session igÃ¥ng och skickar personen till rÃ¤tt sida beroende pÃ¥ roll.
if (session.getAttribute("sparadRoll") == "Administratör") {
    response.sendRedirect("administrator.jsp"); // Not logged in, redirect to login page.
}


%>

<div class="page-header">
<h1>  <a href="bibliotekarie.jsp"> <span class="glyphicon glyphicon-book"></span> Bibliotek Informatika</a> <small>Bibliotekarie</small> </h1>
</button>
</div>
	
<nav class="navbar navbar-default" role="navigation">	
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-search"></span> Sök<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="listaAlltKnapp"><a href="#">Listning av alla dokument<span class="badge">
            <%
            GetLiteratureService gts = new GetLiteratureService();
  			int amount = gts.getNumberOfTitles()+1;
            %>
            <%=amount %>
            </span></a></li>
            <li id="listaEnskildTitelKnapp"><a href="#">Sök på enskilt dokument</a></li>
          </ul>
          
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dokument<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="xxx"><a href="#">xxx</a></li>
            <li id="xxx2"><a href="#">xxx</a></li>
          </ul>
          
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lager<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="xxx3"><a href="#">xxx</a></li>
            <li id="xxx4"><a href="#">xxx</a></li>
          </ul>
          
        </li>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lån<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="xxx5"><a href="#">xxx</a></li>
            <li id="xxx6"><a href="#">xxx</a></li>
          </ul>
          
        </li>
        
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      
      <p class="navbar-text navbar-right"><a href="#" class="navbar-link"></a></p>
      
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Mina sidor<b class="caret"></b></a>
          <ul class="dropdown-menu">
                    	<li id="installningarKnapp"><a href="#">Inställningar</a></li>
          	<li id="minaLanKnapp"><a href="#">Mina lån</a></li>
          	<li id="minaReservationerKnapp"><a href="#">Mina reservationer</a></li>
            <li class="divider"></li>
            <form action="logout" method="get">
            <li><input type="submit" value="Logga ut" class="btn btn-danger btn-sm"></li>
            </form>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div id="listaAllt">
	<div id="listTitlesFunction">
	<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	%>
	<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span> Hämtat: <%=sdf.format(date) %> </div> 
	<form>
	</button>
	<%
	GetLiteratureService getLiteratureService = new GetLiteratureService();
	ArrayList<String> list = getLiteratureService.getTitles();
	int lineCount = 0;
	for(String book: list){
		lineCount++;
	%>
	<!--

	-->
  <ul class="list-group">
	<li class="list-group-item list-group-item-info" name="bokListning<%=lineCount%>"><input type="checkbox" class="taBortInput"> Bok: <%=book %> </li>
	<%} %>
	</ul>
	</form>

	</div>
</div>
<div id="listaEnskildTitel">
      <form class="navbar-form navbar-left" role="Sök" action="getLiterature" method="post" >
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Titel..." name="soktTitel">
        </div>
        <button type="submit" class="btn btn-default" id="search">Sök</button>
      </form>	
</div>

<div id="valkomsttext">
<div class="media">
  <a class="pull-left" href="#">
    <span class="glyphicon glyphicon-share-alt"></span>
  </a>
  <div class="media-body">
    <h4 class="media-heading">Välkommen <%=sparatFornamn%> <%=sparatEfternamn%>!</h4>
    <br>
    <ul>
    <li>[Se lån som har gått ut?]</li>
    <li>[Se lån som håller på att gå ut?]</li>
    <li>[Nyinköpta dokument?]</li>
    </ul>
  </div>
</div>
</div>
 
	
	</p>
</div>


	<!-- Svar ifrån backend. ------------------------------------------ -->
	
	<% 
	if(request.getAttribute("svar") != null){
		String svar = (String) request.getAttribute("svar");
		%>
		<script>
		setTimeout(svaret, 20);
		function svaret(){
			alert("<%=svar%>");
		}
		</script>
	<% 
	}
	%>


</body>
</html>





