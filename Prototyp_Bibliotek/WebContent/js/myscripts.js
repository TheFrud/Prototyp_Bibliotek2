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
	$("#lamnaInkopsforslagPanel").hide();
	$("#minaLan").hide();
	$("#minaReservationer").hide();
	$("#laggTillDokument").hide();
	$("#pagaendeLan").hide();
	$("#pagaendeReservationer").hide();
	$("#forslag").hide();
	
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
		
		
		$("#katalogisera").fadeIn(200);
		// Observera att vi g�mmer alla paneler som inte �r "katalogiserapanelen". Vi vill endast att katalogiserapanelen ska synas.
		// Observera ocks� att det s�kert kan g�ras 100 g�nger b�ttre om man f�rst�r vad man h�ller p� med.
		// Detta �r sj�lvklart inte ett bra s�tt att g�ra saker p� men det fungerar.. for now.
		$("#listaAllt").hide();
		$("#listaEnskildTitel").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();

	});
	
	$("#listaAlltKnapp").click(function(){
		$("#listaAllt").fadeIn(200);
		$("#katalogisera").hide();
		$("#listaEnskildTitel").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	$("#listaEnskildTitelKnapp").click(function(){
		$("#listaEnskildTitel").fadeIn(200);
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	
	$("#pagaendeLanKnapp").click(function(){
		$("#listaPagaendeLan").fadeIn(200);
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaEnskildTitel").hide();
		$("#redigeraAnvandare").hide();
		$("#valkomsttext").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	
	$("#avregistreraKnapp").click(function(){
		$(".taBortInput").slideToggle(500);
		$("#avregistreraKnapp2").slideToggle();

	});
	$("#lamnaInkopsforslagKnapp").click(function(){
		$("#lamnaInkopsforslagPanel").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#redigeraAnvandare").hide();
		$("#listaEnskildTitel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();

	});
	$("#installningarKnapp").click(function(){
		$("#redigeraAnvandare").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	
	$("#edit").click(function(){
		$("#anvandarnamnEdit").fadeIn(200);
		$("#losenordEdit").removeAttr("disabled");
		$("#fornamnEdit").removeAttr("disabled");
		$("#efternamnEdit").removeAttr("disabled");
		$("#gatuadressEdit").removeAttr("disabled");
		$("#stadEdit").removeAttr("disabled");
		$("#postnummerEdit").removeAttr("disabled");
		$("#telefonEdit").removeAttr("disabled");
		$("#epostEdit").removeAttr("disabled");
		$("#genomforAndringar").removeAttr("disabled");
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	
	$("#minaLanKnapp").click(function(){
		$("#minaLan").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	
	$("#minaReservationerKnapp").click(function(){
		$("#minaReservationer").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaLan").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	$("#laggTillDokumentKnapp").click(function(){
		$("#laggTillDokument").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	$("#pagaendeLanKnapp").click(function(){
		$("#pagaendeLan").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeReservationer").hide();
		$("#forslag").hide();
	});
	$("#reservationerKnapp").click(function(){
		$("#pagaendeReservationer").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#forslag").hide();
	});
	$("#forslagKnapp").click(function(){
		$("#forslag").fadeIn(200);
		$("#valkomsttext").hide();
		$("#katalogisera").hide();
		$("#listaAllt").hide();
		$("#searchedTitle").hide();
		$("#listaPagaendeLan").hide();
		$("#listaEnskildTitel").hide();
		$("#lamnaInkopsforslagPanel").hide();
		$("#redigeraAnvandare").hide();
		$("#minaLan").hide();
		$("#minaReservationer").hide();
		$("#laggTillDokument").hide();
		$("#pagaendeLan").hide();
		$("#pagaendeReservationer").hide();
	});
	
	
	$("#fragetecken").hover(function(){
		$("#tooltip").fadeToggle(360);
	});
});


