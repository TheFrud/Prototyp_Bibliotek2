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
import funktion.LaggTillForslag;

@WebServlet("/lamnaInkopsForslag")
public class KontrollerLaggTillForslag extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		// Vi spar h�mtar personens roll och personnummer.
		String role = (String) session.getAttribute("sparadRoll");
		String personnummer = (String) session.getAttribute("sparatPersonnummer");
		// Vi tittar vad anv�ndaren skrev i textrutan.
		String forslag = req.getParameter("forslagText");

		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		RequestDispatcher dispatcher;
		LaggTillForslag laggTillForslag = new LaggTillForslag();
		
		// Vi tittar om f�rslaget var tomt.
		if(req.getParameter("forslagText").equals("")){
			// Ger feedback och skickar tillbaka anv�ndaren till r�tt vy.
			req.setAttribute("svar", "Som vi brukar s�ga p� Bibliotek Informatika: 'Ett tomt f�rslag �r inget f�rslag.' / Bruno Nilsson, avdelning C");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
	
		}
		
		// Vi f�rs�ker l�gga till dokumentet.
		if(laggTillForslag.laggTillForslag(personnummer, forslag)){
			// Ger feedback och skickar tillbaka anv�ndaren till r�tt vy.
			req.setAttribute("svar", "Tack f�r visat intresse! Vi tittar p� ditt f�rslag s� fort vi kan. / Bruno Nilsson, avdelning C");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
	
		}
		// Om det inte lyckades.
		else{
			// Ger feedback och skickar tillbaka anv�ndaren till r�tt vy.
			req.setAttribute("svar", "F�rslaget kunde inte l�ggas till. Kontakta administrat�ren.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}

	
		



	}
	
	
}
