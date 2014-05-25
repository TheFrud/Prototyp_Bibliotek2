package kontroll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funktion.HamtaDokument;
import modell.Dokument;

@WebServlet("/getLiterature")
public class KontrollerHamtaDokument extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HamtaDokument hamtaDokument = new HamtaDokument();
		ArrayList<Dokument> literature = hamtaDokument.hamtaDokument();
		RequestDispatcher dispatcher;
		
		dispatcher = req.getRequestDispatcher("main.jsp");
		dispatcher.forward(req, resp);
		return;

		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("soktTitel");
		HamtaDokument hamtaDokument = new HamtaDokument();
		ArrayList<Dokument> literature = hamtaDokument.hamtaDokument(title);
		RequestDispatcher dispatcher;

		dispatcher = req.getRequestDispatcher("searchedTitle.jsp");
		dispatcher.forward(req, resp);
		return;
	}
	
}
