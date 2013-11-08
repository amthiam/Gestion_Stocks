import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Simulation implements Runnable{
	List<Product> listOfProducts;
	final int simulationSpeed = 1;	//nombre d'appels à simulationStep par seconde
	Date simulationDate;
	Calendar cal = Calendar.getInstance();
	
	public Simulation(){
		listOfProducts = new ArrayList<Product>();
		simulationDate = new Date();
		cal.setTime(simulationDate);
	}
	
	public Simulation(String startingDate){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			simulationDate = df.parse(startingDate);
		} catch (ParseException e) {
			System.out.println("Error: the date " + startingDate + " is not of the expected format");
			e.printStackTrace();
		}
		cal.setTime(simulationDate);
	}

	/**
	 * @return the listOfProducts
	 */
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	/**
	 * @param listOfProducts the listOfProducts to set
	 */
	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
	
	public void addProduct(Product p){
		this.listOfProducts.add(p);
	}
	
	public void removeProduct(Product p){
		this.listOfProducts.remove(p);
	}
	/*
	public void addProductsOfCategory(Category category){
		for(Category c:category.getChildren()){
			if()
		}
	}
	*/
	public void run(){
		while(true){
			System.out.println("Simulation step...");
			try {
				Thread.sleep((long)(1.0/(double)simulationSpeed*1000.0));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				simulationStep();
			} catch (QuantityHigherThanAvailabilityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void simulationStep() throws QuantityHigherThanAvailabilityException{
		for(Product p:this.listOfProducts){
			p.sell(Math.random(), simulationDate);
		}
		cal.add(Calendar.DAY_OF_MONTH, 1);
		simulationDate = cal.getTime();
	}
}
