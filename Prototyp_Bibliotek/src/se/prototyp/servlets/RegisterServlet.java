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
		String userName, firstName, familyName, password;
		userName = request.getParameter("userNameRegister");
		firstName = request.getParameter("firstNameRegister");
		familyName = request.getParameter("familyNameRegister");
		password = request.getParameter("passwordRegister");
		RegisterService registerService = new RegisterService();
		RequestDispatcher dispatcher;
		
		// Kollar om anv�ndaren matat in n�got i samtliga f�lt.
		if(userName != "" && firstName != "" && familyName != "" && password != ""){
			DBConsistencyService consistency = new DBConsistencyService();
			
			// Vi tittar om anv�ndarnamnet redan finns i databasen.
			if(consistency.checkIfUserNameExists(userName)){
				dispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("svar", "Anv�ndarnamnet finns redan i databasen. V�lj ett annat.");
				dispatcher.forward(request, response);
			}
			else{
				// Vi f�rs�ker att uppdatera databasen med den nya anv�ndaren.
				boolean result = registerService.addUser(userName, firstName, familyName, password);
				// Vi tittar s� att databasen faktiskt uppdaterades.
					if(result){
	
						GetUserInfoService getUserInfoService = new GetUserInfoService();
						ArrayList<String> userInfo = getUserInfoService.getUserInfo(userName, password);
						String id = userInfo.get(0);
						
						// Anv�ndaren loggas in i session.
						HttpSession session = request.getSession();
						session.setAttribute("savedUserId", id);
						session.setAttribute("savedUserName", userName);
						session.setAttribute("savedFirstName", firstName);
						session.setAttribute("savedFamilyName", familyName);
						session.setAttribute("savedPassword", password);
						
						// Anv�ndaren skickas till main.jsp som inloggad.
						request.setAttribute("svar", "V�lkommen " + userName + "! Du �r nu registrerad p� Bibliotek Informatika.");
						dispatcher = request.getRequestDispatcher("main.jsp");
						dispatcher.forward(request, response);
						return;
					}
					else{
						// Databasen blev inte uppdaterad med den nya anv�ndaren.
						dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					}
			}

		}
		else{
			// Anv�ndaren har inte matat in n�got i samtliga f�lt.
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("svar", "Du m�ste fylla i samtliga f�lt.");
			dispatcher.forward(request, response);
		}
	}

}
