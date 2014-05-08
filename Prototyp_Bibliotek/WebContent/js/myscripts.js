/*
 * A page can't be manipulated safely until the document is "ready." 
 * jQuery detects this state of readiness for you. 
 * Code included inside $( document ).ready() will only run once the page 
 * Document Object Model (DOM) is ready for JavaScript code to execute. 
 */
$(document).ready(function() {
	
	// G�mmer alla paneler i de dokument som importerat detta javascript-dokument �ppnas.
	$("#registerpanel").hide();
	$("#katalogisera").hide();
	$("#listaAllt").hide();
	$("#listaEnskildTitel").hide();
	$("#avregistreraKnapp2").hide();
	$(".taBortInput").hide();
	$("#listaPagaendeLan").hide();
	$("#redigeraAnvandare").hide();
	$("#tooltip").hide();
	
	// N�r registerknappen klickats p� k�r vi denna funktion..
	// Denna knapp har getts id="register" i dokumentet login.jsp.
	// Vi n�r detta id genom att ange den som #register.
	// Denna del kallas f�r selectorn. ---> http://api.jquery.com/id-selector/
	$("#register").click(function(){
		
		// Registerpanelen slidar in (eller ut) med en snabbhet av "250".
		// Registerpanelen har getts id="registerpanel" i dokumentet login.jsp.
		$("#registerpanel").slideToggle(250);

	});
	// Funktion slut
	
	// Ny funktion... samma m�nster g�ller.
	$("#katalogiseraKnapp").click(function(){
		
		
		$("#katalogisera").fadeToggle(500);
		// Observera att vi g�mmer alla paneler som inte �r "katalogiserapanelen". Vi vill endast att katalogiserapanelen ska synas.
		// Observera ocks� att det s�kert kan g�ras 100 g�nger b�ttre om man f�rst�r vad man h�ller p� med.
		// Detta �r sj�lvklart inte ett bra s�tt att g�ra saker p� men det fungerar.. for now.
		$("#listaAllt").hide();
		$("#listaEnskildTitel").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();

	});
	
	$("#listaAlltKnapp").click(function(){
		$("#listaAllt").fadeToggle(500);
		$("#katalogisera").hide();
		$("#listaEnskildTitel").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
	});
	$("#listaEnskildTitelKnapp").click(function(){
		$("#listaEnskildTitel").fadeToggle(500);
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
	});
	
	$("#pagaendeLanKnapp").click(function(){
		$("#listaPagaendeLan").fadeToggle(500);
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaEnskildTitel").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
	});
	
	$("#redigeraAnvandareKnapp").click(function(){
		$("#redigeraAnvandare").fadeToggle(500);
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaEnskildTitel").hide();
		$("#listaPagaendeLan").hide();
		$("#valkomsttext").hide();
	});
	
	$("#avregistreraKnapp").click(function(){
		$(".taBortInput").slideToggle(500);
		$("#avregistreraKnapp2").slideToggle();

	});
	$("li[name^='bokListning']").click(function(){
		$(this).toggleClass(".list-group-item list-group-item-danger");
	});
	$("#fragetecken").hover(function(){
		$("#tooltip").fadeToggle(360);

	});
});


