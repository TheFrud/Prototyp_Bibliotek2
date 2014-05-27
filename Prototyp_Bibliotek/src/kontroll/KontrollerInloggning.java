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
	
	// G�stinlogging
	// Denna metod anv�nds inte //
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// S�tter in g�stdata i sessionen.
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
		session.setAttribute("sparadRoll", "G�st");
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
		Konsistenskontroll konsistenskontroll = new Konsistenskontroll();
		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		RequestDispatcher dispatcher;
		
		// Tittar s� att anv�ndaren fyllt i alla f�lt.
		if(anvandarnamnIn == "" || losenordIn == ""){
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Du måste fylla i samtliga fält.");
			dispatcher.forward(request, response);
			return;
		}

		// Vi tittar om anv�ndaren redan finns i bibliotek informatikas databas.
		if(konsistenskontroll.autentisera(anvandarnamnIn, losenordIn)){
	
			// Vi tar reda p� anv�ndardatan.
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
			
			// S�tter in anv�ndardatan i sessionen.
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
			
			// Vi tittar vilken roll anv�ndaren har. Och skickar vidare anv�ndaren till r�tt vy.
			dispatcher = request.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(request, response);
			return;



		}
		
		// Om anv�ndaren inte redan finns i bibliotek informatikas databas s� tittar vi om vi kan skapa anv�ndaren ifr�n persondatabasen.
		if(inloggning.laggTillAnvandareFranSkolDB(anvandarnamnIn, losenordIn)){
			
			// Vi tar reda p� anv�ndardatan.
			ArrayList<String> anvandarinfo = hamtaAnvandare.hamtaAnvandarInfo(anvandarnamnIn, losenordIn);
			HttpSession session = request.getSession();
			
			String personnummer = anvandarinfo.get(0);
			String anvandarnamn = anvandarinfo.get(1);
			String losenord = anvandarinfo.get(2);
			String fornamn = anvandarinfo.get(3);
			String efternamn = anvandarinfo.get(4);
			String gatuadress = anvandarinfo.get(5);
			String stad = anvandarinfo.get(6);
			String postnummer = anvandarinfo.get(7);
			String telefon = anvandarinfo.get(8);
			String epost = anvandarinfo.get(9);
			
			String roll = "Låntagare";
			
			
			// S�tter in anv�ndardatan i sessionen.
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
			
			
			// Anv�ndaren skickas vidare anv�ndaren till r�tt vy.
			// Observera att man alltid ses som en l�ntagare som nyregistrerad.
			// D�rf�r kollar vi inte upp n�gon roll.
			request.setAttribute("svar", "Välkommen " + fornamn + " " + efternamn + "! Du är nu registrerad på Bibliotek Informatika.");
			dispatcher = request.getRequestDispatcher("lantagare.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else{
			// Anv�ndaren existerar ej.
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Fel användarnamn eller lösenord!");
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

}
