package se.prototyp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.prototyp.services.DBConsistencyService;
import se.prototyp.services.EditUserService;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();

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
		EditUserService editUserService = new EditUserService();
		RequestDispatcher dispatcher;

		DBConsistencyService consistency = new DBConsistencyService();
		
		// Vi tittar om ifyllt lösenord eller användarnamn redan finns i databasen.
		if(consistency.checkIfUserNameExists(anvandarnamn) && anvandarnamn.equals(a) == false){
			req.setAttribute("svar", "Detta användarnamn finns redan i databasen. Välj ett annat.");
			if(session.getAttribute("sparadRoll").equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(session.getAttribute("sparadRoll").equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		
		// Vi försöker uppdatera databasen med de nya användaruppgifterna.
		if(editUserService.editUser(personnummer, anvandarnamn, losenord, fornamn, efternamn, gatuadress, stad, postnummer, telefon, epost)){
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
			req.setAttribute("svar", "Gratulerar " + fornamn + "! Dina användaruppgifter är nu uppdaterade.");
			if(session.getAttribute("sparadRoll").equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(session.getAttribute("sparadRoll").equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			
		}
		else{
			// Uppdateringen lyckades inte.
			fornamn = (String) session.getAttribute("sparatFornamn");
			req.setAttribute("svar", "Tyvärr " + fornamn + "! Dina användaruppgifter kunde inte uppdateras.");
			if(session.getAttribute("sparadRoll").equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(session.getAttribute("sparadRoll").equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}

		}
		
	}
	
	
}
