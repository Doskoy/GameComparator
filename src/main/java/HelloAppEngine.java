/*
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basics.gamecomparator.Game;
import com.basics.gamecomparator.WebScanning;


public class HelloAppEngine extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    response.setContentType("text/plain");
    response.getWriter().print("Hello App Engine!Has clicado en Servlet :D\r\n");
    String Games = request.getParameter("game");
    response.getWriter().print(Games);

  }
}
*/

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import com.basics.gamecomparator.*;


@SuppressWarnings("serial")
public class HelloAppEngine extends HttpServlet{
	
	public static ArrayList <Game> game = new ArrayList<Game>();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Get the results
		WebScanning.ScrapingXtraLife(req, resp, game);
		WebScanning.ScrapingOtogami(req, resp, game);
	    //Sort results
	   try {
			core.getSystemPrice(game);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //Show the results
	    if (game.size() > 0) {
		    resp.getWriter().println(core.getWeb(game));
		}
	    else{
		    resp.getWriter().println(core.getWebError());
	    }
			    
	}
}

