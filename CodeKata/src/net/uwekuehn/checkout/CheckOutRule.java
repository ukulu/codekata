package net.uwekuehn.checkout;

/**
 * class defines a rule for any item 
 * 
 * @author uwe.kuehn
 */
public class CheckOutRule extends Rule {
	/**
	 * no default constructor need
	 */
	@SuppressWarnings("unused")
	private CheckOutRule(){}
	
	/**
	 * Initialization constructor for a specific rule
	 * 
	 * @param name defines name of rule
	 * @param minimumPieces defines the amount of items this rule will work, must greater than 0
	 * @param fullPrice defines the new price for the minimum pieces, must greater than 0 
	 * @throws Exception 
	 */
	public CheckOutRule(String name, int minimumPieces, double fullPrice){
		this.name = name;
		this.fullPrice = fullPrice;
		this.minimumPieces = minimumPieces;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fullPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + minimumPieces;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckOutRule other = (CheckOutRule) obj;
		if (Double.doubleToLongBits(fullPrice) != Double.doubleToLongBits(other.fullPrice))
			return false;
		if (minimumPieces != other.minimumPieces)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * returns price per item
	 * 
	 * @return
	 * @throws Exception if either fullPrice or minimumPieces is invalid
	 */
	public double getPricePerItem() throws Exception{
		double returnValue = 0.;
		
		if(minimumPieces < 1 || fullPrice <= 0)
			throw new Exception("invalid use of CheckOutRule");
		
		returnValue = fullPrice / minimumPieces;
		
		return returnValue;
	}
	
	/**
	 * identify if this rule counts 
	 * 
	 * @param currentPiecesAmount
	 * @return
	 */
	public boolean getRuleCounts(int currentPiecesAmount){
		return (currentPiecesAmount >= this.minimumPieces);
	}
}
