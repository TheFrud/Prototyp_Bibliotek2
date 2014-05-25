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
			req.setAttribute("svar", "Som vi brukar säga på Bibliotek Informatika: 'Ett tomt förslag är inget förslag.' / Bruno Nilsson, avdelning C");
			if(role.equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administratör")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		
		if(laggTillForslag.laggTillForslag(personnummer, forslag)){
			req.setAttribute("svar", "Tack för visat intresse! Vi tittar på ditt förslag så fort vi kan. / Bruno Nilsson, avdelning C");
			
			if(role.equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administratör")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		else{
		req.setAttribute("svar", "Förslaget kunde inte läggas till. Kontakta administratören.");
			
			if(role.equals("Låntagare")){
				dispatcher = req.getRequestDispatcher("lantagare.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Bibliotekarie")){
				dispatcher = req.getRequestDispatcher("bibliotekarie.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			if(role.equals("Administratör")){
				dispatcher = req.getRequestDispatcher("administrator.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		



	}
	
	
}
