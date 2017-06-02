/*
 * Se consultaran dos paginas para realizar el scaneo de juegos: https://www.xtralife.com/ y www.otogami.es
 */
package com.basics.gamecomparator;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.basics.gamecomparator.Game;

//import org.jsoup.Connection.Response;

public class WebScanning{
	
	public static void ScrapingOtogami (HttpServletRequest req, HttpServletResponse resp,  ArrayList<Game> games) throws IOException {
		 String busqueda = req.getQueryString().substring(5);
		 
		 String urlPage = "https://www.otogami.com/buscar?search=" + busqueda;
		 	 
	     // Compruebo si me da un 200 al hacer la peticion
	     if (JsoupMethods.GetStatusConnection(urlPage) == 200) {
	         Document document = JsoupMethods.getHtmlDocument(urlPage);
	         Elements entradas1 = document.select("a");
	         Elements entradas2 = document.getElementsByClass("price__digit");
	         String price = "0";
	         String name;
	         String Id; 
	         String site = "otogami";
	         ArrayList<String> names = new ArrayList<>();
	         ArrayList<String> Ids = new ArrayList<>();
	         ArrayList<String> Prices = new ArrayList<>();
      	 
	         // Nombre y link
	         //Al no tener la etiqueta <a> que busco una clase especifica busco en todos los <a> de la página y comparo el attr
	         //"title" con la busqueda, si comienzan por el nombre de la busqueda selecciona ese y su enlace
	         for (Element elem1 : entradas1) {
	        	 busqueda = busqueda.toUpperCase();
	        	 String resultSeach = elem1.attr("title").toUpperCase();
	        	 Integer resultado = resultSeach.indexOf(busqueda);
	             if (resultado == 0) {
	            	name = elem1.attr("title");		            
		            Id = "https://www.otogami.com" + elem1.attr("href").toString();
		            
		            names.add(name);
		            Ids.add(Id);
				}
	         }
	         //price
	         for(Element element : entradas2){
	        	 price = element.toString();
	        	 String auxprice = "";
		        if (price.indexOf(',') > 0) {
		        	for (int i = 0; i < price.length(); i++) {
		        		 if (price.charAt(i) >= '0' & price.charAt(i) <= '9' | price.charAt(i) == ',') {
		        			 if (price.charAt(i) == ',') {
		        				 auxprice = auxprice + '.';
		        			 }
		        			 else {
		        				 auxprice = auxprice + price.charAt(i);
		        			 }
		        		 }
		        	 }
				}			        	 
	        	 price = auxprice;
	        	 if (price != null) {
		        	 Prices.add(price);
				}
	         }
	         
	         
	         if (names.size() != 0) {
		         String tituloAux = names.get(0);
		         int contadorPrecio = 0;
		         for (int i = 1; i < names.size(); i++) {
		        	 if (tituloAux.indexOf(names.get(i).toString()) == 0) {
		        		 
						Float precioFloat = Float.parseFloat(Prices.get(contadorPrecio).toString());
						contadorPrecio++;
						if(names.get(i).toString() != ""){
						Game g = new Game(Ids.get(i).toString(), names.get(i).toString(), site, precioFloat);
						
	               	 	if (g != null) 
	               	 		games.add(g);
	               	 	
						}
	               	 	i++;
	               	 	if (i < names.size()) 
	               	 		tituloAux = names.get(i).toString();
						
						
	
					}
				
		         }
		    }
	         
	         
	         
	     }else{
	     	resp.getWriter().println("El estado es: "+ JsoupMethods.GetStatusConnection(urlPage));
	     }
	     
	}
	
	public static void ScrapingXtraLife (HttpServletRequest req, HttpServletResponse resp, ArrayList <Game> games) throws IOException{
		
		String busqueda = req.getQueryString().substring(5);
		 
		
		 String url = "https://www.xtralife.es/buscar/" + busqueda; 
		 // Check the Status Code.
		 // If = 200 means everything is ok and we can get the html document.
		 //
		
	     if (JsoupMethods.GetStatusConnection(url) == 200) {
	         Document document = JsoupMethods.getHtmlDocument(url);
	         System.out.println(document);
	         Elements entradas = document.getElementsByClass("listaProductosTitleUpper").select("a");
	         Elements entradas1 = document.getElementsByClass("listaProductosFoterPrice");
	         String Id;
	         String name;
	         String site = "XtraLife";
	         String price = "0";
	         ArrayList<String> names = new ArrayList<>();
	         ArrayList<String> Ids = new ArrayList<>();
	         ArrayList<String> prices = new ArrayList<>();
	         
	         //take name and link
	         System.out.println("Conexion OK");
	         for(Element elem : entradas){
	        	 	 Id = elem.attr("href");
		        	 name = elem.attr("title");
		        	 
		        	 names.add(name);
		        	 Ids.add(Id);
	         }
	         
	         for(Element elem : entradas1){
	        	 price = elem.toString();
	        	 String precioAux = "";
		        for (int i = 0; i < price.length(); i++) {
		        	if (price.charAt(i) >= '0' & price.charAt(i) <= '9' | price.charAt(i) == '.') {
		        		if (price.charAt(i) == '.') {
		        			precioAux = precioAux + '.';
		        			}
		        			else {
		        			precioAux = precioAux + price.charAt(i);
		        			}
		        	}
		        }
											        	 
	        	 price = precioAux;
	        	 if (price != null) {
		        	 prices.add(price);
		        	 price = "";
				}

	         }
	         
	         if (names.size() != 0) {
					
		         int contadorPrecio = 0;
		         for (int i = 0; i < names.size(); i++) {
		        		Float precioFloat = Float.parseFloat(prices.get(contadorPrecio).toString());
						contadorPrecio++;
	               	 	Game g = new Game(Ids.get(i).toString(), names.get(i).toString(), site, precioFloat);

	               	 	if (g != null) {
	               	 		games.add(g);
	               	 	}
		         }
		    }
	     }
	     else{
	    	 resp.getWriter().println("El estado es: "+ JsoupMethods.GetStatusConnection(url));
	     }
	}
}

