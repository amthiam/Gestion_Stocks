package Application;
import java.util.*;

/**
 * Classe pour la simulation des ventes.
 * @author Arthur
 *
 */
public class Simulation{
	List<Product> listOfProducts;
	int simulationSpeed = 3;	//nombre d'appels � simulationStep par seconde
	int simulationDate;
	boolean active = false;		//Boolean � vrai si la simulation tourne
	
	/**
	 * Constructeur. Initialise la liste des produits simul�s et le jour courrent de la simulation � z�ro.
	 */
	public Simulation(){
		listOfProducts = new ArrayList<Product>();
		simulationDate = 0;
	}

	/**
	 * @return la liste des produits dont des ventes sont simul�es.
	 */
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	/**
	 * D�finit la liste des produits dont on veut simuler les ventes.
	 * @param listOfProducts la liste des produits dont on souhaite simuler les ventes.
	 */
	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
                System.out.println(listOfProducts.size());
	}
	
	/**
	 * Ajoute un produit � la liste des produits dont les ventes sont simul�es
	 * @param p le produit � ajouter
	 */
	public void addProduct(Product p){
		this.listOfProducts.add(p);
	}
	
	/**
	 * retire un produit de la liste des produits dont on souhaite simuler les ventes.
	 * @param p le produit � retirer
	 */
	public void removeProduct(Product p){
		this.listOfProducts.remove(p);
	}
	
	/**
	 * Ajoute tous les produits d'une cat�gorie � la liste des produits qu'on souhaite simuler.
	 * @param category la cat�gorie dont on veut ajouter les produits.
	 */
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
	
	/**
	 * M�thode principale de la classe. Simule les ventes d'une journ�e pour tous les produits de la liste listOfProducts.
	 */
	public void simulationStep(){
		if(active){
			for(Product p:this.listOfProducts){
				try{
					p.sell(Math.random()*10.0, simulationDate);
				}
				catch(QuantityHigherThanAvailabilityException e){
					p.getQuantityLevels().put(simulationDate, 0.0);
				}
			}
			simulationDate++;
		}
	}
	
	/**
	 * @return la date courante. 
	 */
	public int getSimulationDate() {
		return simulationDate;
	}

	/**
	 * D�finit la date courante de la simulation.
	 * @param simulationDate la date courante de la simulation, en nombre de jours.
	 */
	public void setSimulationDate(int simulationDate) {
		this.simulationDate = simulationDate;
	}


	/**
	 * Retoure la vitesse de simulation.
	 * @return la vitesse de simulation.
	 */
	public int getSimulationSpeed() {
		return simulationSpeed;
	}
	
	/**
	 * Permet de mettre en pause/lancer la simulation selon l'�tat courant.
	 * @return true si la simulation est active, false si la simulation est en pause.
	 */
	public boolean startPause() {
		active = !active;
		return active;
	}
	
	/**
	 * Met la simulation en pause, peu importe son �tat actuel. Utilis� par exemple lorsque l'utilisateur utilise le module statistique et simulation
	 * et qu'il clique sur le bouton retour. Peu importe l'�tat actif ou inactif de la simulation, il faut alors le mettre � inactif.
	 */
	public void stopSimulation() {
		active = false;
	}
	
	/**
	 * D�finit la vitesse de simulation.
	 * @param value nombre de jours simul�s par seconde.
	 */
	public void setSimulationSpeed(int value) {
		this.simulationSpeed = value;
	}
}
