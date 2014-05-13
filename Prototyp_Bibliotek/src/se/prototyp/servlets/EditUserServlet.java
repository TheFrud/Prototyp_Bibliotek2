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
		String StringId = (String) session.getAttribute("savedUserId");
		int id = Integer.parseInt(StringId);
		String userName = (String) req.getParameter("userNameEdit");
		String firstName = (String) req.getParameter("firstNameEdit");
		String familyName = (String) req.getParameter("familyNameEdit");
		String password = (String) req.getParameter("passwordEdit");
		
		EditUserService editUserService = new EditUserService();
		RequestDispatcher dispatcher;

		DBConsistencyService consistency = new DBConsistencyService();
		
		// Vi tittar om ifyllt lösenord eller användarnamn redan finns i databasen.
		if(consistency.checkIfUserNameExists(userName)){
			dispatcher = req.getRequestDispatcher("main.jsp");
			req.setAttribute("svar", "Detta användarnamn finns redan i databasen. Välj ett annat.");
			dispatcher.forward(req, resp);
			return;
		}
		
		// Vi försöker uppdatera databasen med de nya användaruppgifterna.
		if(editUserService.editUser(id, userName, firstName, familyName, password)){
			// Uppdateringen lyckades.
			// Vi läser in de nya uppgifterna i sessionen (inloggningen).
			session.setAttribute("savedUserName", userName);
			session.setAttribute("savedFirstName", firstName);
			session.setAttribute("savedFamilyName", familyName);
			session.setAttribute("savedPassword", password);
			String newUserName = (String) session.getAttribute("savedUserName");
			req.setAttribute("svar", "Gratulerar " + newUserName + "! Dina användaruppgifter är nu uppdaterade.");
			dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		else{
			// Uppdateringen lyckades inte.
			String newUserName = (String) session.getAttribute("savedUserName");
			req.setAttribute("svar", "Tyvärr " + newUserName + "! Dina användaruppgifter kunde inte uppdateras.");
			dispatcher = req.getRequestDispatcher("main.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
	}
	
	
}
