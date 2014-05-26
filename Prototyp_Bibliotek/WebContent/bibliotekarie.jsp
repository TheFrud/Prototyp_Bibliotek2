<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"
    import="java.util.ArrayList" 
    import="java.text.SimpleDateFormat" import="java.util.Date"
    import="modell.*"	import="funktion.*"
    import="java.util.Collections"
    %>
    
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
String sparatPostnummer = (String) session.getAttribute("sparatPostnummer");
String sparadTelefon = (String) session.getAttribute("sparadTelefon");
String sparadEpost = (String) session.getAttribute("sparadEpost");
String sparadRoll = (String) session.getAttribute("sparadRoll");

if(sparadRoll == null){
	request.setAttribute("svar", "Du har inte rätt behörighet för att nå denna sida.");
	RequestDispatcher dispatcher;
	dispatcher = request.getRequestDispatcher("login.jsp");
	dispatcher.forward(request, response);
	return;
}

if(!sparadRoll.equals("Bibliotekarie")){
	request.setAttribute("svar", "Du har inte rätt behörighet för att nå denna sida.");
	RequestDispatcher dispatcher;
	dispatcher = request.getRequestDispatcher("login.jsp");
	dispatcher.forward(request, response);
	return;
}

%>

<div class="page-header">
<h1>  <a href="bibliotekarie.jsp"> <span class="glyphicon glyphicon-book"></span> Bibliotek Informatika</a> <small>Bibliotekarie</small> </h1>
</button>
</div>
	
<nav class="navbar navbar-default" role="navigation">	
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-search"></span> Sök<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="listaAlltKnapp"><a href="#">Listning av alla dokument<span class="badge">
            <%
            	HamtaDokument gts = new HamtaDokument();
                                      			int amount = gts.hamtaAntalTitlar()+1;
            %>
            <%=amount%>
            </span></a></li>
            <!--  
            <li id="listaEnskildTitelKnapp"><a href="#">Sök på enskilt dokument</a></li>
            -->
          </ul>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-book"></span> Dokument<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="laggTillDokumentKnapp"><a href="#">Lägg till</a></li>
			<li id="andraDokumentKnapp"><a href="#">Ändra</a></li>
			<li id="taBortDokumentKnapp"><a href="#">Ta bort</a></li>
          </ul>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-retweet"></span> Lån<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="utlaningKnapp"><a href="#">Utlåning</a></li>
			<li id="pagaendeLanKnapp"><a href="#">Pågående lån</a></li>
			<li id="reservationerKnapp"><a href="#">Reservationer</a></li>
			<li id="forseningarKnapp"><a href="#">Förseningar</a></li>
          </ul>
          <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-shopping-cart"></span> Inköp<b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li id="lagerKnapp"><a href="#">Lager</a></li>
			<li id="statistikKnapp"><a href="#">Statistik</a></li>
			<li id="forslagKnapp"><a href="#">Förslag</a></li>
			<li id="leverantorerKnapp"><a href="#">Leverantörer</a></li>
          </ul>
        
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      
      <p class="navbar-text navbar-right"><a href="#" class="navbar-link"></a></p>
      
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Mina sidor<b class="caret"></b></a>
          <ul class="dropdown-menu">
          	<li id="installningarKnapp"><a href="#">Inställningar</a></li>
          	 <li id="lamnaInkopsforslagKnapp"><a href="#">Lämna inköpsförslag</a></li>
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<div class="alert alert-success"><span class="glyphicon glyphicon-ok"></span> Hämtat: <%=sdf.format(date)%> </div> 
	<form>
	</button>
	<%
		HamtaDokument getLiteratureService = new HamtaDokument();
			ArrayList<Dokument> list = getLiteratureService.hamtaDokument();
			Collections.sort(list);
			int lineCount = 0;
			for(Dokument book: list){
		lineCount++;
	%>
	<!--

	-->
  <ul class="list-group">
	<li class="list-group-item list-group-item-info" name="bokListning<%=lineCount%>"><input type="checkbox" class="taBortInput"><%=book.getTitel()%> 
	<p>
	<ul class="list.group">
	
	<%
			ArrayList<Lager> lagerLista = getLiteratureService.hamtaLager(book.getIsbn()); 
			Collections.sort(lagerLista);
			for(Lager lager: lagerLista){
		%>
	<%
		if(lager.getTillganglig() == 1){
	%> <li class="list-group-item list-group-item-success">
	<%
		}
	%>
	<%
		if(lager.getTillganglig() == 0){
	%> <li class="list-group-item list-group-item-danger">
	<%
		}
	%>
	
	
	<%=lager.toString()%>
	<br>
	<%
		if(lager.getTillganglig() == 1){
	%>Reservera <span class="glyphicon glyphicon-share-alt"></span> <input type="checkbox">
	<%
		}
	%>
	<%
		if(lager.getTillganglig() == 0){
	%>Inte tillgänglig. <span class="glyphicon glyphicon-remove"></span>
	<%
		}
	%>
	<br>
	<%
		}
	%>
	</li>
	</ul>
	</li>
	<%
		}
	%>
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
    <span class="glyphicon glyphicon-heart"></span>
  </a>
  <div class="media-body">
    <h4 class="media-heading">Välkommen <%=sparatFornamn%> <%=sparatEfternamn%>!</h4>
    <br>
    <h4 class="media-heading">Nyheter:</h4>
    <ul>
    <li>Senast tillagda dokument:</li>
    	<ul>
    		<%
    			ArrayList<Lager> listaSenasteDokument = gts.hamtaSenasteLagerdokument();
    		    		for(int i = 0; i<5; i++){
    		%>
    		<li><%=listaSenasteDokument.get(i).toString()%></li>
    		<%
    			}
    		%>
    		
    	</ul>
    </ul>
  </div>
</div>
</div>
 
	
	</p>
</div>

<form id="lamnaInkopsforslagPanel" action="lamnaInkopsForslag" method="post">
	<div id="lamnaInkopsforslagTextArea">
		<textarea name="forslagText" id="textarea" rows = "3" cols = "80" placeholder="Skriv förslag..."></textarea>
	</div>
	<br>
	<input type = "submit" value="Skicka in förslag" class="btn btn-primary btn-sm" />
</form>

<div id="redigeraAnvandare">
	<form id = "redigera" action="editUser" method = "post" class="navbar-form navbar-left">
		Personnummer: <br><input disabled="disabled" type = "text" name = "personnummerEdit" id="personnummerEdit" autocomplete="off" placeholder="<%=sparatPersonnummer%>" value="<%=sparatPersonnummer%>"/>
		<br>
		Användarnamn: <br><input disabled="disabled" type = "text" name = "anvandarnamnEdit" id="anvandarnamnEdit" autocomplete="off" placeholder="<%=sparatAnvandarnamn%>..." value="<%=sparatAnvandarnamn%>"/>
		<br>
		Lösenord: <br><input disabled="disabled" type = "password" name = "losenordEdit" id="losenordEdit" autocomplete="off" placeholder="..." value="<%=sparatLosenord%>"/>
		<br>
		Förnamn: <br><input disabled="disabled" type = "text" name = "fornamnEdit" id="fornamnEdit" autocomplete="off" placeholder="<%=sparatFornamn%>..." value="<%=sparatFornamn%>"/>
		<br>
		Efternamn: <br><input disabled="disabled" type = "text" name = "efternamnEdit" id="efternamnEdit" autocomplete="off" placeholder="<%=sparatEfternamn%>..." value="<%=sparatEfternamn%>" />
		<br>
		Gatuadress: <br><input disabled="disabled" type = "text" name = "gatuadressEdit" id="gatuadressEdit" autocomplete="off" placeholder="<%=sparadGatuadress%>..." value="<%=sparadGatuadress%>"/>
		<br>
		Stad: <br><input disabled="disabled" type = "text" name = "stadEdit" id="stadEdit" autocomplete="off" placeholder="<%=sparadStad%>..." value="<%=sparadStad%>"/>
		<br>
		Postnummer: <br><input disabled="disabled" type = "text" name = "postnummerEdit" id="postnummerEdit" autocomplete="off" placeholder="<%=sparatPostnummer%>..." value="<%=sparatPostnummer%>"/>
		<br>
		Telefon: <br><input disabled="disabled" type = "tel" name = "telefonEdit" id="telefonEdit" autocomplete="off" placeholder="<%=sparadTelefon%>..." value="<%=sparadTelefon%>"/>
		<br>
		E-post: <br><input disabled="disabled" type = "email" name = "epostEdit" id="epostEdit" autocomplete="off" placeholder="<%=sparadEpost%>..." value="<%=sparadEpost%>"/>
		<br>
		<br>
		<input id="edit" type = "button" class="btn btn-primary btn-sm" value="Ändra"/>
		<input disabled="disabled" id="genomforAndringar" type = "submit" class="btn btn-primary btn-sm" value="Genomför ändringar"/>
	</form>
</div>

<div id="minaLan">
	<%
		HamtaLan getLoansService = new HamtaLan();
			ArrayList<Lan> lanLista = getLoansService.hamtaLan(sparatPersonnummer);
			if(!lanLista.isEmpty()){
		for(Lan lan: lanLista){
	%>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info"><%=lan.getDokument().getTitel()%> | Tillbaka: <%=sdf.format(lan.getSlutdatum().getTime())%></li>
	<%
		} 
	}
	%>
	</ul>

</div>

<div id="minaReservationer">
	<%
	HamtaReservation hamtaReservation = new HamtaReservation();
	ArrayList<Reservation> reservationsLista = hamtaReservation.hamtaReservationer(sparatPersonnummer);
	if(!reservationsLista.isEmpty()){
		for(Reservation reservation: reservationsLista){
	%>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info"><%=reservation.getDokument().getTitel()%> </li>
	<%
		} 
	}
	%>
	</ul>

</div>

<div id="pagaendeLan">
		<%
			ArrayList<Lan> allaLanLista = getLoansService.hamtaLan();
			if(!lanLista.isEmpty()){
				for(Lan lan: allaLanLista){
		%>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info"><%=lan.getAnvandare().getAnvandarnamn()%> | <%=lan.getDokument().getTitel() %> | Tillbaka: <%=sdf.format(lan.getSlutdatum().getTime()) %></li>
	<%
		} 
	}
	%>
	</ul>


</div>

<div id="forslag">
		<%
			HamtaForslag hamtaForslag = new HamtaForslag();
			ArrayList<Forslag> allaForslagLista = hamtaForslag.hamtaForslag();
			if(!allaForslagLista.isEmpty()){
				for(Forslag forslag: allaForslagLista){
		%>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info"><%=forslag.getAnvandare().getAnvandarnamn()%> | <%=forslag.getInkopsforslag()%></li>
	<%
		} 
	}
	%>
	</ul>


</div>

<div id="pagaendeReservationer">
		<%
	ArrayList<Reservation> reservationsLista2 = hamtaReservation.hamtaReservationer();
	if(!reservationsLista2.isEmpty()){
		for(Reservation reservation: reservationsLista2){
	%>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info"><%=reservation.getAnvandare().getAnvandarnamn()%> | <%=reservation.getDokument().getTitel()%> </li>
	<%
		} 
	}
	%>
	</ul>


</div>

<div id="laggTillDokument">
	<form action="catalog" method="post">
		ISBN: <br><input type = "text" name = "isbn" autocomplete="off" required/>
		<br>
		Titel: <br><input type = "text" name = "titel" autocomplete="off" />
		<br>
		Upplaga: <br><input type = "text" name = "upplaga" autocomplete="off" />
		<br>
		Författare: <br><input type = "text" name = "forfattare" autocomplete="off" />
		<br>
		Förlag: <br><input type = "text" name = "forlag" autocomplete="off" />
		<br>
		Sidantal: <br><input type = "text" name = "sidantal" autocomplete="off" />
		<br>
		Språk: <br><input type = "text" name = "sprak" autocomplete="off" />
		<br>
		Bindningstyp: <br><input type = "text" name = "bindningstyp" autocomplete="off" />
		<br>
		Beskrivning: <br><input type = "text" name = "beskrivning" autocomplete="off" />
		<br>
		Dokumenttyp: <br><select name="dokumenttyp"> <option value="Pappersdokument">Pappersdokument</option> <option value="edokument">E-dokument</option></select>
		<br><br>
		Hylla: <select name="hylla"> <option value="A">A</option> <option value="B">B</option></select>
		Hyllplan: <select name="hyllplan"> <option value="1">1</option> <option value="2">2</option><option value="3">3</option><option value="4">4</option></select>
		<br><br>
		<button type="submit" class="btn btn-success btn-default" id="register">
 		 <span class="glyphicon glyphicon-plus"></span> Lägg till
	</button>
	</form>

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





