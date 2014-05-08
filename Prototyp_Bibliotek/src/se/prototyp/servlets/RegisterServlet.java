package se.prototyp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.prototyp.services.DBConsistencyService;
import se.prototyp.services.GetUserInfoService;
import se.prototyp.services.RegisterService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String anvandarnamn, losenord,personnummer, fornamn, efternamn, gatuadress, stad, postnummer, telefon, epost;
		anvandarnamn = request.getParameter("anvandarnamnRegistrering");
		losenord = request.getParameter("losenordRegistrering");
		personnummer = request.getParameter("personnummerRegistrering");
		fornamn = request.getParameter("fornamnRegistrering");
		efternamn = request.getParameter("efternamnRegistrering");
		gatuadress = request.getParameter("gatuadressRegistrering");
		stad = request.getParameter("stadRegistrering");
		postnummer = request.getParameter("postnummerRegistrering");
		telefon = request.getParameter("telefonRegistrering");
		epost = request.getParameter("epostRegistrering");
		
		RegisterService registerService = new RegisterService();
		RequestDispatcher dispatcher;
		
		// Kollar om anv�ndaren matat in n�got i samtliga f�lt.
		if(anvandarnamn != "" && losenord != "" && personnummer != "" && fornamn != "" && 
				efternamn != "" && gatuadress != "" && stad != "" && postnummer != "" && telefon != "" && epost != ""){
			DBConsistencyService consistency = new DBConsistencyService();
			
			// Vi tittar om personnumret existerar i persondatabasen.
			/*
			if(consistency.checkUserExistanceAndRole(anvandarnamn, losenord) == 0){
				dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("svar", "Ditt personnummer existerar inte i v�r persondatabas.");
				dispatcher.forward(request, response);
				return;
			}
			*/
			// Vi tittar om l�senordet matchar det i persondatabasen
			if(!consistency.checkIfPasswordExistsInSchoolDatabase(personnummer, losenord)){
				dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("svar", "Detta l�senord st�mmer inte �verrens med det i v�r persondatabas.");
				dispatcher.forward(request, response);
				return;
			}
			
			// Vi tittar om anv�ndarnamnet redan finns i databasen.
			if(consistency.checkIfUserNameExists(anvandarnamn)){
				dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("svar", "Anv�ndarnamnet finns redan i databasen. V�lj ett annat.");
				dispatcher.forward(request, response);
				return;
			}
			else{
				// Vi f�rs�ker att uppdatera databasen med den nya anv�ndaren.
				boolean result = registerService.addUser(anvandarnamn, losenord, personnummer, fornamn, efternamn, gatuadress, stad, postnummer, telefon, epost);
				// Vi tittar s� att databasen faktiskt uppdaterades.
					if(result){

						/*
						GetUserInfoService getUserInfoService = new GetUserInfoService();
						ArrayList<String> userInfo = getUserInfoService.getUserInfo(anvandarnamn, losenord);
						String id = userInfo.get(0);
						*/
						
						// Anv�ndaren loggas in i session.
						HttpSession session = request.getSession();
						session.setAttribute("sparatPersonnummer", personnummer);
						session.setAttribute("sparatAnvandarnamn", anvandarnamn);
						session.setAttribute("sparatLosenord", losenord);
						session.setAttribute("sparatFornamn", fornamn);
						session.setAttribute("sparatEfternamn", efternamn);
						session.setAttribute("sparadGatuadress", gatuadress);
						session.setAttribute("sparadStad", stad);
						session.setAttribute("sparatPostnummer", postnummer);
						session.setAttribute("sparadTelefon", telefon);
						session.setAttribute("sparadEpost", epost);
						
						// Anv�ndaren skickas till main.jsp som inloggad.
						request.setAttribute("svar", "V�lkommen " + anvandarnamn + "! Du �r nu registrerad p� Bibliotek Informatika.");
						dispatcher = request.getRequestDispatcher("main.jsp");
						dispatcher.forward(request, response);
						return;
					}
					else{
						// Databasen blev inte uppdaterad med den nya anv�ndaren.
						dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
						return;
					}
			}

		}
		else{
			// Anv�ndaren har inte matat in n�got i samtliga f�lt.
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Du m�ste fylla i samtliga f�lt.");
			dispatcher.forward(request, response);
			return;
		}
	}

}
