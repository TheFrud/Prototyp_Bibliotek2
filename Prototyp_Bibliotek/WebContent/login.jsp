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

	<!-- Inloggningsformulär -->
	<form action="login" method = "post" >
		Användarnamn:<br> <input type = "text" name = "anvandarnamnInloggning" autocomplete="off" />
		<br>
		Lösenord:<br> <input type = "password" name = "losenordInloggning" autocomplete="off" />
		<br><br>
		<input type = "submit" value="Logga in användare" class="btn btn-primary btn-sm" />
	</form>

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