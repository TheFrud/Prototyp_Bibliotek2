package kontroll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funktion.HamtaAnvandare;
import funktion.Konsistenskontroll;
import funktion.RedigeraAnvandare;

@WebServlet("/editUser")
public class KontrollerRedigeraAnvandare extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Konsistenskontroll consistency = new Konsistenskontroll();
		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();

		// Vi hämtar all data användaren har fyllt i formuläret.
		String personnummer = (String) session.getAttribute("sparatPersonnummer");
		String anvandarnamn = (String) req.getParameter("anvandarnamnEdit");
		String losenord = (String) req.getParameter("losenordEdit");
		String fornamn = (String) req.getParameter("fornamnEdit");
		String efternamn = (String) req.getParameter("efternamnEdit");
		String gatuadress = (String) req.getParameter("gatuadressEdit");
		String stad = (String) req.getParameter("stadEdit");
		String postnummer = (String) req.getParameter("postnummerEdit");
		String telefon = (String) req.getParameter("telefonEdit");
		String epost = (String) req.getParameter("epostEdit");
		String a = (String) session.getAttribute("sparatAnvandarnamn");
		RedigeraAnvandare redigeraAnvandare = new RedigeraAnvandare();
		RequestDispatcher dispatcher;


		
		// Vi tittar om ifyllt lösenord eller användarnamn redan finns i databasen.
		if(consistency.hittaAnvandarnamn(anvandarnamn) && anvandarnamn.equals(a) == false){
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Detta användarnamn finns redan i databasen. Välj ett annat.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
	
		}
		
		// Vi försöker uppdatera databasen med de nya användaruppgifterna.
		if(redigeraAnvandare.redigeraAnvandare(personnummer, anvandarnamn, losenord, fornamn, efternamn, gatuadress, stad, postnummer, telefon, epost)){
			// Uppdateringen lyckades.
			// Vi läser in de nya uppgifterna i sessionen (inloggningen).
			session.setAttribute("sparatAnvandarnamn", anvandarnamn);
			session.setAttribute("sparatLosenord", losenord);
			session.setAttribute("sparatFornamn", fornamn);
			session.setAttribute("sparatEfternamn", efternamn);
			session.setAttribute("sparadGatuadress", gatuadress);
			session.setAttribute("sparadStad", gatuadress);
			session.setAttribute("sparatPostnummer", postnummer);
			session.setAttribute("sparadTelefon", telefon);
			session.setAttribute("sparadEpost", epost);
			fornamn = (String) session.getAttribute("sparatFornamn");
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Gratulerar " + fornamn + "! Dina användaruppgifter är nu uppdaterade.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;

			
		}
		else{
			// Uppdateringen lyckades inte.
			fornamn = (String) session.getAttribute("sparatFornamn");
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Tyvärr " + fornamn + "! Dina användaruppgifter kunde inte uppdateras.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;

		}
		
	}
	
	
}
