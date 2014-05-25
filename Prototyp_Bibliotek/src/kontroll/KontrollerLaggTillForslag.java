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
		// Vi spar hämtar personens roll och personnummer.
		String role = (String) session.getAttribute("sparadRoll");
		String personnummer = (String) session.getAttribute("sparatPersonnummer");
		// Vi tittar vad användaren skrev i textrutan.
		String forslag = req.getParameter("forslagText");

		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		RequestDispatcher dispatcher;
		LaggTillForslag laggTillForslag = new LaggTillForslag();
		
		// Vi tittar om förslaget var tomt.
		if(req.getParameter("forslagText").equals("")){
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Som vi brukar säga på Bibliotek Informatika: 'Ett tomt förslag är inget förslag.' / Bruno Nilsson, avdelning C");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
	
		}
		
		// Vi försöker lägga till dokumentet.
		if(laggTillForslag.laggTillForslag(personnummer, forslag)){
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Tack för visat intresse! Vi tittar på ditt förslag så fort vi kan. / Bruno Nilsson, avdelning C");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
	
		}
		// Om det inte lyckades.
		else{
			// Ger feedback och skickar tillbaka användaren till rätt vy.
			req.setAttribute("svar", "Förslaget kunde inte läggas till. Kontakta administratören.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}

	
		



	}
	
	
}
