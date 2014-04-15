package se.prototyp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.prototyp.database.DBOperations;
import se.prototyp.services.DBConsistencyService;
import se.prototyp.services.EditUserService;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String StringId = (String) session.getAttribute("savedUserId");
		int id = Integer.parseInt(StringId);
		String userName = (String) req.getParameter("userNameEdit");
		String firstName = (String) req.getParameter("firstNameEdit");
		String familyName = (String) req.getParameter("familyNameEdit");
		String password = (String) req.getParameter("passwordEdit");
		
		EditUserService editUserService = new EditUserService();
		RequestDispatcher dispatcher;

		DBConsistencyService consistency = new DBConsistencyService();
		
		// Vi tittar om ifyllt l�senord eller anv�ndarnamn redan finns i databasen.
		if(consistency.checkIfUserNameExists(userName)){
			dispatcher = req.getRequestDispatcher("main.jsp");
			req.setAttribute("svar", "Detta anv�ndarnamn finns redan i databasen. V�lj ett annat.");
			dispatcher.forward(req, resp);
			return;
		}
		
		// Vi f�rs�ker uppdatera databasen med de nya anv�ndaruppgifterna.
		if(editUserService.editUser(id, userName, firstName, familyName, password)){
			// Uppdateringen lyckades.
			// Vi l�ser in de nya uppgifterna i sessionen (inloggningen).
			session.setAttribute("savedUserName", userName);
			session.setAttribute("savedFirstName", firstName);
			session.setAttribute("savedFamilyName", familyName);
			session.setAttribute("savedPassword", password);
			dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		else{
			// Uppdateringen lyckades inte.
			dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
	}
	
	
}
