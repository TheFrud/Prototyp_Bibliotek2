package kontroll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funktion.HamtaAnvandare;
import funktion.Inloggning;
import funktion.Konsistenskontroll;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class KontrollerInloggning extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Gästinlogging
	// Denna metod används inte //
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Sätter in gästdata i sessionen.
		HttpSession session = request.getSession();
		session.setAttribute("sparatAnvandarnamn", "Gästkonto");
		session.setAttribute("sparatLosenord", "");
		session.setAttribute("sparatPersonnummer", "");
		session.setAttribute("sparatFornamn", "");
		session.setAttribute("sparatEfternamn", "");
		session.setAttribute("sparadGatuadress", "");
		session.setAttribute("sparadStad", "");
		session.setAttribute("sparatPostnummer", "");
		session.setAttribute("sparadTelefon", "");
		session.setAttribute("sparadEpost", "");
		/*
		session.setAttribute("sparadRoll", "Gäst");
		*/
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String anvandarnamnIn = request.getParameter("anvandarnamnInloggning");
		String losenordIn = request.getParameter("losenordInloggning");
		Inloggning inloggning = new Inloggning();
		Konsistenskontroll dbConsistencyService = new Konsistenskontroll();
		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		RequestDispatcher dispatcher;
		
		// Tittar så att användaren fyllt i alla fält.
		if(anvandarnamnIn == "" || losenordIn == ""){
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Du måste fylla i samtliga fält.");
			dispatcher.forward(request, response);
			return;
		}

		// Vi tittar om användaren redan finns i bibliotek informatikas databas.
		if(dbConsistencyService.autentisera(anvandarnamnIn, losenordIn)){
	
			// Vi tar reda på användardatan.
			ArrayList<String> userInfo = hamtaAnvandare.hamtaAnvandarInfo(anvandarnamnIn, losenordIn);
			HttpSession session = request.getSession();
			

			String personnummer = userInfo.get(0);
			String anvandarnamn = userInfo.get(1);
			String losenord = userInfo.get(2);
			String fornamn = userInfo.get(3);
			String efternamn = userInfo.get(4);
			String gatuadress = userInfo.get(5);
			String stad = userInfo.get(6);
			String postnummer = userInfo.get(7);
			String telefon = userInfo.get(8);
			String epost = userInfo.get(9);
			String roll;
			if(userInfo.size()<=10){
				roll = "Låntagare";
			}
			else{
				roll = userInfo.get(10);
			}
			
			// Sätter in användardatan i sessionen.
			session.setAttribute("sparatAnvandarnamn", anvandarnamn);
			session.setAttribute("sparatLosenord", losenord);
			session.setAttribute("sparatPersonnummer", personnummer);
			session.setAttribute("sparatFornamn", fornamn);
			session.setAttribute("sparatEfternamn", efternamn);
			session.setAttribute("sparadGatuadress", gatuadress);
			session.setAttribute("sparadStad", stad);
			session.setAttribute("sparatPostnummer", postnummer);
			session.setAttribute("sparadTelefon", telefon);
			session.setAttribute("sparadEpost", epost);
			session.setAttribute("sparadRoll", roll);
			
			// Vi tittar vilken roll användaren har. Och skickar vidare användaren till rätt vy.
			dispatcher = request.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(request, response);
			return;



		}
		
		// Om användaren inte redan finns i bibliotek informatikas databas så tittar vi om vi kan skapa användaren ifrån persondatabasen.
		if(inloggning.laggTillAnvandareFranSkolDB(anvandarnamnIn, losenordIn)){
			
			// Vi tar reda på användardatan.
			ArrayList<String> userInfo = hamtaAnvandare.hamtaAnvandarInfo(anvandarnamnIn, losenordIn);
			HttpSession session = request.getSession();
			
			String personnummer = userInfo.get(0);
			String anvandarnamn = userInfo.get(1);
			String losenord = userInfo.get(2);
			String fornamn = userInfo.get(3);
			String efternamn = userInfo.get(4);
			String gatuadress = userInfo.get(5);
			String stad = userInfo.get(6);
			String postnummer = userInfo.get(7);
			String telefon = userInfo.get(8);
			String epost = userInfo.get(9);
			
			String roll = "Låntagare";
			
			
			// Sätter in användardatan i sessionen.
			session.setAttribute("sparatAnvandarnamn", anvandarnamn);
			session.setAttribute("sparatLosenord", losenord);
			session.setAttribute("sparatPersonnummer", personnummer);
			session.setAttribute("sparatFornamn", fornamn);
			session.setAttribute("sparatEfternamn", efternamn);
			session.setAttribute("sparadGatuadress", gatuadress);
			session.setAttribute("sparadStad", stad);
			session.setAttribute("sparatPostnummer", postnummer);
			session.setAttribute("sparadTelefon", telefon);
			session.setAttribute("sparadEpost", epost);
			
			session.setAttribute("sparadRoll", roll);
			
			
			// Användaren skickas vidare användaren till rätt vy.
			// Observera att man alltid ses som en låntagare som nyregistrerad.
			// Därför kollar vi inte upp någon roll.
			request.setAttribute("svar", "Välkommen " + fornamn + " " + efternamn + "! Du är nu registrerad på Bibliotek Informatika.");
			dispatcher = request.getRequestDispatcher("lantagare.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else{
			// Användaren existerar ej.
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Fel användarnamn eller lösenord!");
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

}
