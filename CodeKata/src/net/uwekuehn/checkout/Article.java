package net.uwekuehn.checkout;

/**
 * class Article does implement specific CRM issues 
 * 
 * @author uwe.kuehn
 *
 */
public class Article {
	protected String name;
	protected double price;
	
	@SuppressWarnings("unused")
	private Article(){}
	
	/**
	 * initializing constructor 
	 * 
	 * @param name
	 * @param price
	 */
	public Article(String name, double price){
		this.name = name;
		this.price = price;
	}

}
