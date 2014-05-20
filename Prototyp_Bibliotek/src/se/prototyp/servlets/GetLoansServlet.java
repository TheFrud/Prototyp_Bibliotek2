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

import model.Lan;
import se.prototyp.services.GetLoansService;

@WebServlet("/getLoans")
public class GetLoansServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		GetLoansService getLoans = new GetLoansService();
		HttpSession session = req.getSession();
		String personnummer= (String) session.getAttribute("sparatPersonnummer");
		
		ArrayList<Lan> loans = getLoans.getLoans(personnummer);
		
		RequestDispatcher dispatcher;
	
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
