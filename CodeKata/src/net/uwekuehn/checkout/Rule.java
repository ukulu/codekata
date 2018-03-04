package net.uwekuehn.checkout;

/**
 * abstract class Rule
 * 
 * @author uwe.kuehn
 */
public abstract class Rule {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinimumPieces() {
		return minimumPieces;
	}

	public void setMinimumPieces(int minimumPieces) {
		this.minimumPieces = minimumPieces;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	protected String name;
	protected int minimumPieces;
	protected double fullPrice;
	
	public abstract boolean getRuleCounts(int currentPiecesAmount);
	public abstract double getPricePerItem() throws Exception;
}
