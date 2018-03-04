package net.uwekuehn.app;

import net.uwekuehn.checkout.CheckOut;
import net.uwekuehn.checkout.CheckOutItem;
import net.uwekuehn.checkout.CheckOutRule;
import net.uwekuehn.checkout.Rule;

/**
 * Test Driver class to run and debug code directly
 * 
 * @author uwe.kuehn
 */
public class TestDriver {
	public static void main(String[] args) {
		System.out.println("Hello world.");
		CheckOutItem<Rule> A = new CheckOutItem<Rule>("A", 50);
		CheckOutItem<Rule> B = new CheckOutItem<Rule>("B", 30);
		CheckOutItem<Rule> C = new CheckOutItem<Rule>("C", 20);
		CheckOutItem<Rule> D = new CheckOutItem<Rule>("D", 15);
		
		CheckOutRule get_3_for_130 = new CheckOutRule("3 for 130", 3, 130);
		CheckOutRule get_2_for_45 = new CheckOutRule("2 for 45", 2, 45);
		
		CheckOutRule get_2_for_25 = new CheckOutRule("2 for 25", 2, 25);
		// next is is worse than 2 for 25, should not match
		CheckOutRule get_3_for_40 = new CheckOutRule("3 for 40", 3, 40);
		// next is is better the both before
		CheckOutRule get_3_for_36 = new CheckOutRule("3 for 36", 3, 36);
		
		A.addCheckOutRule(get_3_for_130);
		B.addCheckOutRule(get_2_for_45);
		
		// now an example with other additional rules for same product
		C.addCheckOutRule(get_2_for_25);
		C.addCheckOutRule(get_3_for_40); // remember single item would cost 13,33, is worse than 12,50
		C.addCheckOutRule(get_3_for_36);
				
		CheckOut co = new CheckOut();
		co.scanArticle(A);
		co.scanArticle(B);
		co.scanArticle(A);
		co.scanArticle(C);
		co.scanArticle(D);
		co.scanArticle(B);
		co.scanArticle(A);
		co.scanArticle(C);
		co.scanArticle(C);
		
		co.printCheckoutList();
	}
}
