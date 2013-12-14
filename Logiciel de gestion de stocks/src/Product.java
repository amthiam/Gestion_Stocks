import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;


public class Product extends Category{
	Double price;
	HashMap<Integer,Double> quantityLevels;
	Double currentQuantity;

	public Product(String n) {
		super(n);
		quantityLevels = new HashMap<Integer,Double>();
	}
	
	public Product(String n, Double p) {
		super(n);
		this.price = p;
		quantityLevels = new HashMap<Integer,Double>();
	}
	
	public Product(String n, Double p, Double currentQ) {
		super(n);
		this.price = p;
		this.currentQuantity = currentQ;
		quantityLevels = new HashMap<Integer,Double>();
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
	public HashMap<Integer, Double> getQuantityLevels() {
		return quantityLevels;
	}

	/**
	 * @param quantityLevels the quantityLevels to set
	 */
	public void setQuantityLevels(HashMap<Integer, Double> quantityLevels) {
		this.quantityLevels = quantityLevels;
	}

	public void sell(double quantity, int simulationDate) throws QuantityHigherThanAvailabilityException{
		if(quantity > currentQuantity){
			throw new QuantityHigherThanAvailabilityException(quantity);
		}
		else{
			this.currentQuantity -= quantity;
			quantityLevels.put(simulationDate, this.currentQuantity);
		}
	}

	public Double getCurrentQuantity() {
		return currentQuantity;
	}

	public double getQuantityLevelOnDate(int d) {
		System.out.println("Liste des historiques pour le produit " + this.getName());
		for(int date:quantityLevels.keySet()){
			System.out.println(date);
			System.out.println(d);
		}
		try{
			return quantityLevels.get(d);
		}
		catch(Exception e){
			return 0;
		}
	}
	
}
