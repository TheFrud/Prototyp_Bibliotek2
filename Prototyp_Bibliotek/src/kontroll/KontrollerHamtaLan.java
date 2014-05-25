package kontroll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funktion.HamtaAnvandare;
import funktion.HamtaLan;
import modell.Lan;

@WebServlet("/getLoans")
public class KontrollerHamtaLan extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HamtaAnvandare hamtaAnvandare = new HamtaAnvandare();
		HamtaLan getLoans = new HamtaLan();
		RequestDispatcher dispatcher;
		HttpSession session = req.getSession();
		
		// Vi h�mtar personnummer fr�n sessionen.
		String personnummer= (String) session.getAttribute("sparatPersonnummer");
		
		// Vi h�mtar en viss persons l�n med hj�lp av inloggningens sparade personnummer.
		ArrayList<Lan> loans = getLoans.hamtaLan(personnummer);
		
	
		// Vi tittar vilken roll anv�ndaren har. Och skickar vidare anv�ndaren till r�tt vy.
		dispatcher = req.getRequestDispatcher(hamtaAnvandare.hamtaRoll(personnummer));
		dispatcher.forward(req, resp);
		return;
		
	}
}
