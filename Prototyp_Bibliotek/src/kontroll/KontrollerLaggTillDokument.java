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
import funktion.LaggTillDokument;

@WebServlet("/catalog")
public class KontrollerLaggTillDokument extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		String personnummer = (String) session.getAttribute("sparatPersonnummer");
		
		// Vi h�mtar in all data anv�ndaren skrivit in.
		String isbn = req.getParameter("isbn");
		String titel = req.getParameter("titel");
		String upplaga = req.getParameter("upplaga");
		String forfattare = req.getParameter("forfattare");
		String forlag = req.getParameter("forlag");
		String sidantal = req.getParameter("sidantal");
		String sprak = req.getParameter("sprak");
		String bindningstyp = req.getParameter("bindningstyp");
		String nyckelord = "[placeholder]";
		String beskrivning = req.getParameter("beskrivning");
		String dokumenttyp = req.getParameter("dokumenttyp");
		String hylla = req.getParameter("hylla");
		String hyllplan = req.getParameter("hyllplan");
		
		LaggTillDokument laggTillDokument = new LaggTillDokument();
		RequestDispatcher dispatcher;
		
		// Vi tittar om alla f�lt �r ifyllda.
		if(isbn.equals("") || titel.equals("") || upplaga.equals("") || forfattare.equals("") || forlag.equals("") || sidantal.equals("") || sprak.equals("")
				|| bindningstyp.equals("") || nyckelord.equals("") || beskrivning.equals("")){
			
			req.setAttribute("svar", "Du måste fylla i samtliga fält!");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}
		// Vi tittar om ISBN �r 13 karakt�rer l�ngt.
		if(isbn.length() != 13){
			req.setAttribute("svar", "ISBN ska alltid vara 13 karaktärer. Det borde du veta som bibliotekarie.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}
		// Vi tittar om ISBN �r av typen heltal.
		if(!arLong(isbn)){
			req.setAttribute("svar", "ISBN ska endast innehålla siffror. Det borde du veta som bibliotekarie.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}
		// Vi tittar om det skrivna sidantalet �r ett heltal.
		if(!arHeltal(sidantal)){
			req.setAttribute("svar", "Sidantalet måste vara av typen heltal.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
		}	
		
		// Vi tittar om dokumentet kunde l�ggas till.
		if(laggTillDokument.laggTillDokument(isbn, titel, upplaga, forfattare, forlag, sidantal, 
				  sprak, bindningstyp, nyckelord, beskrivning, dokumenttyp, hylla, hyllplan) > 0){
			req.setAttribute("svar", "Dokumentet har lagts till!");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
			

		}
		// Om dokumentet inte kunde l�ggas till.
		else{
			req.setAttribute("svar", "Dokumentet kunde inte läggas till.");
			dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
			dispatcher.forward(req, resp);
			return;
			

		}
	}
	// Metod f�r att titta om en str�ng kan omvandlas till ett heltal.
	public static boolean arHeltal(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	// Metod f�r att titta om en str�ng kan omvandlas till ett stort heltal.
	public static boolean arLong(String s) {
	    try { 
	        Long.parseLong(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}

}
