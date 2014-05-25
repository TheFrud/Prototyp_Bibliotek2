package kontroll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funktion.LaggTillForslag;

@WebServlet("/lamnaInkopsForslag")
public class KontrollerLaggTillForslag extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("sparadRoll");
		String personnummer = (String) session.getAttribute("sparatPersonnummer");
		String forslag = req.getParameter("forslagText");

		RequestDispatcher dispatcher;
		LaggTillForslag laggTillForslag = new LaggTillForslag();
		
		if(req.getParameter("forslagText").equals("")){
			req.setAttribute("svar", "Som vi brukar s�ga p� Bibliotek Informatika: 'Ett tomt f�rslag �r inget f�rslag.' / Bruno Nilsson, avdelning C");
			if(role.equals("L�ntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administrat�r")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		
		if(laggTillForslag.laggTillForslag(personnummer, forslag)){
			req.setAttribute("svar", "Tack f�r visat intresse! Vi tittar p� ditt f�rslag s� fort vi kan. / Bruno Nilsson, avdelning C");
			
			if(role.equals("L�ntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administrat�r")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		else{
		req.setAttribute("svar", "F�rslaget kunde inte l�ggas till. Kontakta administrat�ren.");
			
			if(role.equals("L�ntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administrat�r")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		



	}
	
	
}
