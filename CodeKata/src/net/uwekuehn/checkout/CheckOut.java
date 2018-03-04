package net.uwekuehn.checkout;

import java.util.HashMap;

/**
 * manage the basket for one instance
 * 
 * @author uwe.kuehn
 *
 */
public class CheckOut {
	
	private HashMap<String, CheckOutItem<Rule>> basket = null;
	
	/**
	 * default constructor
	 */
	public CheckOut(){
		this.basket = new HashMap<String, CheckOutItem<Rule>>();
	}
	
	/**
	 * adds an CheckoutItem to basket
	 * 
	 * @param scanned
	 */
	public void scanArticle(CheckOutItem<Rule> scanned){
		if(basket.containsKey(scanned.name)){
			basket.get(scanned.name).addCheckOutItem(scanned.getCheckOutAmount());
		}
		else{
			basket.put(scanned.name, scanned);
		}
	}
	
	public void printCheckoutList(){
		System.out.println("======================================================");
		System.out.println("Item\tUnit price\tspecial offer\tordered\tunit special price");
		
		for(String name : basket.keySet()){
			CheckOutItem<Rule> currentItem = basket.get(name);
			System.out.println(String.format("%s\t%f\t%s\t%4d\t\t%f", 
					currentItem.name,
					currentItem.price,
					currentItem.getBestRule()==null ? "no offer" : currentItem.getBestRule().getName(),
					currentItem.getCheckOutAmount(),
					currentItem.getCurrentCheckOutPrice()
					));
		}

		System.out.println("======================================================");
	}
}
