package net.uwekuehn.checkout;

import java.util.ArrayList;

/**
 * is similar to an entry in articles data 
 * if existing it is 1 piece inside
 * 
 * @author uwe.kuehn
 *
 */
public class CheckOutItem<T extends Rule> extends Article {
	private int checkOutAmount = 1;	
	private ArrayList<T> checkOutRules = null; 
	
	/**
	 * initializes within 1 piece of an article
	 * 
	 * @param name
	 * @param price
	 */
	public CheckOutItem(String name, double price) {
		super(name, price);
		this.checkOutRules = new ArrayList<T>();
	}

	/**
	 * increment pieces by one
	 */
	public void addCheckOutItem(){
		this.setCheckOutAmount(this.getCheckOutAmount() + 1);
	}
	
	/**
	 * increment pieces by given value
	 * please note: take care negative values 
	 * @param amountToAdd
	 */
	public void addCheckOutItem(int amountToAdd){
		this.setCheckOutAmount(this.getCheckOutAmount() + amountToAdd);
	}
	
	/**
	 * adds any Rule to this CheckOutItem
	 * @param t
	 */
	public void addCheckOutRule(T t){
		this.checkOutRules.add(t);
	}
	
	/**
	 * returns first best matching rule
	 * 
	 * @return
	 */
	public T getBestRule(){
		T t = null;
		double bestPrice = Double.MAX_VALUE;
		
		for(T rule : this.checkOutRules){
			if(rule.getRuleCounts(this.checkOutAmount)){
				double currentPrice = Double.MAX_VALUE;
				try{
					currentPrice = rule.getPricePerItem();
				}
				catch (Exception ex){}
				
				if(currentPrice <= bestPrice){
					t = rule;
					bestPrice = currentPrice;
				}
			}
		}		
		
		return t;
	}
	
	/**
	 * return the best price for this article
	 * 
	 * @return
	 */
	public double getCurrentCheckOutPrice(){	
		double bestPrice = Double.MAX_VALUE;
		
		for(T rule : this.checkOutRules){
			if(rule.getRuleCounts(this.checkOutAmount)){
				double currentPrice = Double.MAX_VALUE;
				try{
					bestPrice = Math.min(bestPrice, rule.getPricePerItem());
				}
				catch (Exception ex){}
			}
		}
		
		// if no rules or none of rule will match, we use original price
		return Math.min(this.price, bestPrice);
	}

	public int getCheckOutAmount() {
		return checkOutAmount;
	}

	public void setCheckOutAmount(int checkOutAmount) {
		this.checkOutAmount = checkOutAmount;
	}
}
