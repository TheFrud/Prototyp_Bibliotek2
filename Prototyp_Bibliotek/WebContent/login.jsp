<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" name="viewport" content="width=device-width">
<title>Bibliotek Informatika - Inloggning</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/mystyles.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>	
    <script src="js/myscripts.js"></script>
    <link rel="shortcut icon" href="http://www.odontologi.gu.se/kirurgi/img/gu_logga.png">
</head>
<body>
<div class="page-header">
<h1>  <a href="login.jsp"> <span class="glyphicon glyphicon-book"></span> Bibliotek Informatika</a> <small>Inloggning</small> </h1>
</button>
</div>
	<form action="login" method = "post" >
		Användarnamn:<br> <input type = "text" name = "anvandarnamnInloggning" autocomplete="off" />
		<br>
		Lösenord:<br> <input type = "password" name = "losenordInloggning" autocomplete="off" />
		<br><br>
		<input type = "submit" value="Logga in användare" class="btn btn-primary btn-sm" />

		

	</form>
	<br>
	<!-- Fortsätt utan att logga in -->
	<br>
	<br>
	<!--  
	<form action="login" method = "get" >
		<input type = "submit" value="Logga in som gäst" class="btn btn-warning btn-sm"/>
	</form>
	<br>
	
	<button type="button" class="btn btn-success btn-default" id="register"><strong>+</strong>
  <span class="glyphicon glyphicon-user"></span> Har du ingen användare?
	</button>
	<form id = "registerpanel" action="register" method = "post">
		<div class="page-header">
  			<h1>Registrera <small></small></h1>
		</div>
		Användarnamn: <br><input type = "text" name = "anvandarnamnRegistrering" autocomplete="off" />
		<br>
		Lösenord:<br> <input type = "password" name = "losenordRegistrering" autocomplete="off" />
		<br>
		Personnummer:<br> <input type = "text" name = "personnummerRegistrering" autocomplete="off" />
		<br>
		Förnamn:<br> <input type = "text" name = "fornamnRegistrering" autocomplete="off" />
		<br>
		Efternamn:<br> <input type = "text" name = "efternamnRegistrering" autocomplete="off" />
		<br>
		Gatuadress: <br><input type = "text" name = "gatuadressRegistrering" autocomplete="off" />
		<br>
		Stad:<br> <input type = "text" name = "stadRegistrering" autocomplete="off" />
		<br>
		Postnummer:<br> <input type = "text" name = "postnummerRegistrering" autocomplete="off" />
		<br>
		Telefon:<br> <input type = "text" name = "telefonRegistrering" autocomplete="off" />
		<br>
		E-post:<br> <input type = "text" name = "epostRegistrering" autocomplete="off" />
		<br><br>
		<input type = "submit" value="Registrera" class="btn btn-primary btn-sm"/>
	</form>
	-->
	<!-- Svar ifrån backend.------------------------------------------------- -->
	
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