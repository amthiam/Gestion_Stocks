import java.util.*;


public class Simulation{
	List<Product> listOfProducts;
	int simulationSpeed = 3;	//nombre d'appels à simulationStep par seconde
	int simulationDate;
	boolean active = false;		//Boolean à vrai si la simulation tourne
	
	public Simulation(){
		listOfProducts = new ArrayList<Product>();
		simulationDate = 0;
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
	
	public void addProductsOfCategory(Category category){
		boolean isProduct = true;
		for(Category c:category.getChildren()){
			isProduct = false;
			addProductsOfCategory(c);
		}
		if(isProduct){
			addProduct((Product)category);
		}
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep((long)(1.0/(double)simulationSpeed*1000.0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				simulationStep();
			} catch (QuantityHigherThanAvailabilityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void simulationStep() throws QuantityHigherThanAvailabilityException{
		if(active){
			for(Product p:this.listOfProducts){
				p.sell(Math.random()*10.0, simulationDate);
			}
			simulationDate++;
		}
		else{
			//TODO Exception to throw
		}
	}
	
	/**
	 * @return the simulationDate
	 */
	public int getSimulationDate() {
		return simulationDate;
	}

	/**
	 * @param simulationDate the simulationDate to set
	 */
	public void setSimulationDate(int simulationDate) {
		this.simulationDate = simulationDate;
	}


	/**
	 * @return the simulationSpeed
	 */
	public int getSimulationSpeed() {
		return simulationSpeed;
	}

	public boolean startPause() {
		active = !active;
		return active;
	}

	public void stopSimulation() {
		active = false;
	}

	public void setSimulationSpeed(int value) {
		this.simulationSpeed = value;
	}
}
