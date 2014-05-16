package se.prototyp.servlets;

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

import se.prototyp.services.DBConsistencyService;
import se.prototyp.services.GetUserInfoService;
import se.prototyp.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// G�stinlogging
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// S�tter in g�stdata i sessionen.
		HttpSession session = request.getSession();
		session.setAttribute("sparatAnvandarnamn", "G�stkonto");
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
		LoginService loginService = new LoginService();
		DBConsistencyService dbConsistencyService = new DBConsistencyService();
		RequestDispatcher dispatcher;
		
		// Tittar s� att anv�ndaren fyllt i alla f�lt.
		if(anvandarnamnIn == "" || losenordIn == ""){
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Du m�ste fylla i samtliga f�lt.");
			dispatcher.forward(request, response);
			return;
		}

		// Vi tittar om anv�ndaren redan finns i bibliotek informatikas databas.
		if(dbConsistencyService.autentisera(anvandarnamnIn, losenordIn)){
	
			// Vi tar reda p� anv�ndardatan.
			GetUserInfoService getUserInfoService = new GetUserInfoService();
			ArrayList<String> userInfo = getUserInfoService.getUserInfo(anvandarnamnIn, losenordIn);
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
				roll = "L�ntagare";
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
			
			// Loggas in.
			if(roll.equals("L�ntagare")){
				dispatcher = request.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(roll.equals("Bibliotekarie")){
				dispatcher = request.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(request, response);
				return;
			}
			dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
			return;


		}
		
		// Om anv�ndaren inte redan finns i bibliotek informatikas databas s� tittar vi om vi kan skapa anv�ndaren ifr�n persondatabasen.
		if(loginService.addUserFromExistingDB(anvandarnamnIn, losenordIn)){
			
			// Vi tar reda p� anv�ndardatan.
			GetUserInfoService getUserInfoService = new GetUserInfoService();
			ArrayList<String> userInfo = getUserInfoService.getUserInfo(anvandarnamnIn, losenordIn);
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
			/*
			String roll = userInfo.get(10);
			*/
			
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
			/*
			session.setAttribute("sparadRoll", roll);
			*/
			
			// Loggas in.
			request.setAttribute("svar", "V�lkommen " + fornamn + " " + efternamn + "! Du �r nu registrerad p� Bibliotek Informatika.");
			dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else{
			// Anv�ndaren existerar ej.
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Fel anv�ndarnamn eller l�senord!");
			dispatcher.forward(request, response);
			return;
		}
		
		
	}

}
