package net.uwekuehn.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.uwekuehn.checkout.CheckOutItem;
import net.uwekuehn.checkout.CheckOutRule;
import net.uwekuehn.checkout.Rule;

/**
 *  all test cases for test code 
 * 
 * @author uwe.kuehn
 *
 */
public class TestCheckout {
	
	/*
	 * create basket enties with amount 1
	 */
	CheckOutItem<Rule> A = new CheckOutItem<Rule>("A", 50);
	CheckOutItem<Rule> B = new CheckOutItem<Rule>("B", 30);
	CheckOutItem<Rule> C = new CheckOutItem<Rule>("C", 20);
	CheckOutItem<Rule> D = new CheckOutItem<Rule>("D", 15);

	/*
	 * create rules
	 */
	CheckOutRule get_3_for_130 = new CheckOutRule("3 for 130", 3, 130);
	CheckOutRule get_2_for_45 = new CheckOutRule("2 for 45", 2, 45);
	CheckOutRule get_4_for_85 = new CheckOutRule("4 for 85", 4, 85);

	@Before
	public void setup(){
		// nothing to do right now
	}
		
	@Test
	public void checkTheCurrentPriceA() {
		assertEquals(50, A.getCurrentCheckOutPrice(), 0);
	}
	
	@Test
	public void checkTheCurrentPriceB() {
		assertEquals(30, B.getCurrentCheckOutPrice(), 0);
	}
	
	@Test
	public void checkTheCurrentPriceC() {
		assertEquals(20, C.getCurrentCheckOutPrice(), 0);
	}
	
	@Test
	public void checkTheCurrentPriceD() {
		assertEquals(15, D.getCurrentCheckOutPrice(), 0);
	}
	
	@Test
	public void checkStupidRulePriceC() {
		B.addCheckOutRule(get_2_for_45);
		assertEquals(20, C.getCurrentCheckOutPrice(), 0);
	}
	
	@Test
	public void checkStupidRulePriceCNeg() {
		B.addCheckOutRule(get_2_for_45);
		assertNotEquals(25.5, C.getCurrentCheckOutPrice(), 0);
	}	

	@Test
	public void checkNotWorkingRulePriceB() {
		B.addCheckOutRule(get_2_for_45);
		assertEquals(30, B.getCurrentCheckOutPrice(), 0);
	}

	@Test
	public void checkWorkingRulePriceB() {
		B.addCheckOutRule(get_2_for_45);
		// now we got 2 in the basket
		B.addCheckOutItem();
		assertEquals(22.5, B.getCurrentCheckOutPrice(), 0);
	}

	@Test
	public void checkNotWorkingRulePriceB2Rules() {
		B.addCheckOutRule(get_2_for_45);
		B.addCheckOutRule(get_4_for_85);
		// now we got 3 in the basket
		B.addCheckOutItem(2);
		assertEquals(22.5, B.getCurrentCheckOutPrice(), 0);
	}

	@Test
	public void checkWorkingRulePriceB2Rules() {
		B.addCheckOutRule(get_2_for_45);
		B.addCheckOutRule(get_4_for_85);
		// now we got 4 in the basket
		B.addCheckOutItem(3);
		assertEquals(21.25, B.getCurrentCheckOutPrice(), 0);
	}
}
