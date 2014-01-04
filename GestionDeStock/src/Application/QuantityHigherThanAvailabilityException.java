package Application;
public class QuantityHigherThanAvailabilityException extends Exception {
	Double askedQuantity;
	
	public QuantityHigherThanAvailabilityException(double quantity){
		super();
		askedQuantity = quantity;
	}
	
	public double getAskedQuantity(){
		return askedQuantity;
	}
}
