/*
 *This class contain the information of a game 
*/
package com.basics.gamecomparator;

public class Game{
	private String Id;
	private String name;
	private String site;
	private float price;

	public Game(){
		this.Id = null;
		this.name = null;
		this.site = null;
		this.price = 0;
	}
	
	public Game(String Id, String name, String site, float price){
		this.Id = Id;
		this.name = name;
		this.site = site;
		this.price = price;
	}
	
	public String GetId(){
		return Id;
	}
	
	public void SetId(String Id){
		Id = this.Id;
	}
	
	public String GetName(){
		return name;
	}
	
	public void SetName(String name){
		Id = this.name;
	}
	
	public String Getsite(){
		return site;
	}
	
	public void SetNlatform(String site){
		Id = this.site;
	}
	
	public float GetPrice(){
		return price;
	}
	
	public void SetPrice(float price){
		price = this.price;
	}
	public String toString() {
		return "Game [name=" + name + ", site=" + site + ", price=" + price + "]";
	}
	
}