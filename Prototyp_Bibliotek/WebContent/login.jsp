<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<form action="login" method = "post">
		Användarnamn:<br> <input type = "text" name = "userNameLogin" autocomplete="off" />
		<br>
		Lösenord:<br> <input type = "password" name = "passwordLogin" autocomplete="off" />
		<br><br>
		<input type = "submit" value="Logga in" class="btn btn-primary btn-sm"/>
	</form>
	<br>
	
	<button type="button" class="btn btn-success btn-default" id="register"><strong>+</strong>
  <span class="glyphicon glyphicon-user"></span> Har du ingen användare?
	</button>
	<form id = "registerpanel" action="register" method = "post">
		<div class="page-header">
  			<h1>Registrera <small></small></h1>
		</div>
		Användarnamn: <br><input type = "text" name = "userNameRegister" autocomplete="off" />
		<br>
		Färnamn:<br> <input type = "text" name = "firstNameRegister" autocomplete="off" />
		<br>
		Efternamn:<br> <input type = "text" name = "familyNameRegister" autocomplete="off" />
		<br>
		Lösenord:<br> <input type = "password" name = "passwordRegister" autocomplete="off" />
		<br><br>
		<input type = "submit" value="Registrera" class="btn btn-primary btn-sm"/>
	</form>
</body>
</html>