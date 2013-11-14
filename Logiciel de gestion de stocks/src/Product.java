import java.util.Date;
import java.util.HashMap;


public class Product extends Category{
	Double price;
	HashMap<Date,Double> quantityLevels;
	Double currentQuantity;

	public Product(String n) {
		super(n);
		quantityLevels = new HashMap<Date,Double>();
	}
	
	public Product(String n, Double p) {
		super(n);
		this.price = p;
		quantityLevels = new HashMap<Date,Double>();
	}
	
	public Product(String n, Double p, Double currentQ) {
		super(n);
		this.price = p;
		this.currentQuantity = currentQ;
		quantityLevels = new HashMap<Date,Double>();
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * @return the quantityLevels
	 */
	public HashMap<Date, Double> getQuantityLevels() {
		return quantityLevels;
	}

	/**
	 * @param quantityLevels the quantityLevels to set
	 */
	public void setQuantityLevels(HashMap<Date, Double> quantityLevels) {
		this.quantityLevels = quantityLevels;
	}

	public void sell(double quantity, Date d) throws QuantityHigherThanAvailabilityException{
		if(quantity > currentQuantity){
			throw new QuantityHigherThanAvailabilityException(quantity);
		}
		else{
			this.currentQuantity -= quantity;
			quantityLevels.put(d, this.currentQuantity);
		}
	}

	public Double getCurrentQuantity() {
		return currentQuantity;
	}

	public double getQuantityLevelOnDate(Date d) {
		return quantityLevels.get(d);
	}
	
}
