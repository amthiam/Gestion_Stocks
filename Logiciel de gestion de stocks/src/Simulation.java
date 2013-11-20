import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Simulation implements Runnable{
	List<Product> listOfProducts;
	final int simulationSpeed = 3;	//nombre d'appels à simulationStep par seconde
	Date simulationDate;
	Calendar cal;
	
	public Simulation(){
		listOfProducts = new ArrayList<Product>();
		simulationDate = new Date();
		cal = Calendar.getInstance();
	}
	
	public Simulation(String startingDate){
		this();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			simulationDate = df.parse(startingDate);
			cal.setTime(simulationDate);
		} catch (ParseException e) {
			System.out.println("Error: the date " + startingDate + " is not of the expected format");
			e.printStackTrace();
		}
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
		cal.add(Calendar.DAY_OF_MONTH, 1);
		simulationDate = cal.getTime();
		for(Product p:this.listOfProducts){
			p.sell(Math.random()*10, simulationDate);
		}
		System.out.println("simDate:" + simulationDate);
	}
	
	/**
	 * @return the simulationDate
	 */
	public Date getSimulationDate() {
		return simulationDate;
	}

	/**
	 * @param simulationDate the simulationDate to set
	 */
	public void setSimulationDate(Date simulationDate) {
		this.simulationDate = simulationDate;
	}

	/**
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	/**
	 * @return the simulationSpeed
	 */
	public int getSimulationSpeed() {
		return simulationSpeed;
	}
}
