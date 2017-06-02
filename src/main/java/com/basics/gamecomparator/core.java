package com.basics.gamecomparator;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

public class core{
	public static ArrayList <Game> g = new ArrayList<Game>();
	public static Game game;
	
	
	
	
	public static String getWeb(ArrayList<Game> games)throws IOException{
		String web;
		web = GetWebBasic() + GetGames(games) + GetFooter();
		games.clear();
		
		return web;
	}
	
	public static String getWebError() throws IOException{
		String web;
		web = GetWebBasic() + GetError();
		return web;
	}
	
	public static String GetError(){
		String web;
		web = "<section class='searchbox'>" +
				"<h3> Error! </h3>" +
				"<a href=\"mainpage/gamecomparator.html\">Volver" +
				"</a>" +
				"</section>";
		return web;
	}
	
	public static String GetWebBasic(){
		String web;
		web = "<!DOCTYPE html\">" +
				"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\" >"+
				"<head>"+
				"<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />"+
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>" +
				"<title>GameComparator</title>" +
				"</head>" +
				
				"<body>" +
				"<header>" +
				"<h1>GAME RASTREATOR</h1>" +
				"</header>" +
				"<section>" +
				"<h3>Se han obtenido los siguientes resultados: </h3>" +
				"</section>" +
				"</body> " + 
				"</html>";
		return web;
	}
	
	public static String GetGames(ArrayList<Game> games){
		String name;
		String Id;
		String site;
		Float price;
		
		String web;
		web = "<table>" +
				"<thead>" +
				"<tr>" +
					"<td>Sitio</td>" +
					"<td>Nombre</td>" +
					"<td>Precio</td>" +
				"</tr>" +
				"</thead>" +
				"<tbody>";
		
		for (int i = 0; i < games.size(); i++) {
			name = games.get(i).GetName();
			Id = games.get(i).GetId();
			site = games.get(i).Getsite();
			price = games.get(i).GetPrice();
			
			web = web +
				        "<tr>" +
			            	"<td>" +
			            		site+ 
			            	"</td>" +
				            "<td>" +
				            "<a href=" + 
				            	Id +
				            	">"+ name + "</a>" +
				            "</td>" +
		            		"<td>" +
	                			price + "€" +
	                		"</td>" +
				        "</tr>";		    
		}
		 

		return web;
	}
	
	public static String GetFooter(){
		String web;
		web = "<footer>" +
				"Trabajo realizado por: Fernando Roldan Zafra" +
				"<br>" +
			    "<a href=\"como_se_hizo.pdf\" style=\"text-decoration-color: black; color: black;\"> About: </a>" +
				"</footer>";
		return web;
	}
	
	public static void getSystemPrice(ArrayList<Game> game) throws JAXBException
	{
		//oredenamos los resultados por precio antes utilizaba la funcion sort
	    Integer tam = game.size();
	    Game g = new Game();
	    for (int i = 0; i < game.size(); i++) {
			for (int j = i+1; j < tam; j++) {
				if (game.get(i).GetPrice() > game.get(j).GetPrice()) {
					g = game.get(j);
					game.set(j, game.get(i));
					game.set(i, g);
				}
			}
		}
	}
	
}