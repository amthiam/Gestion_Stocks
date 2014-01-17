package Application;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;

/*
 * 
 * Classe permettant la gestion des produits.
 */
public class Product extends Category{
	protected static int idMax = 0;
	protected int id;
	protected Double price;
	protected HashMap<Integer,Double> quantityLevels;
	protected Double currentQuantity;
	protected Double soldQ=0.;
	
	/**
	 * Constructeur avec uniquement le nom du produit
	 * @param n nom du produit
	 */
	public Product(String n) {
		super(n);
		quantityLevels = new HashMap<Integer,Double>();
		id = ++idMax;
	}
	
	/**
	 * Constructeur avec le nom du produit et son prix
	 * @param n nom du produit
	 * @param p prix du produit
	 */
	public Product(String n, Double p) {
		super(n);
		this.price = p;
		quantityLevels = new HashMap<Integer,Double>();
		id = ++idMax;
	}
	
	/**
	 * Constructeur avec nom, prix, et quantit� initiale du produit.
	 * @param n nom du produit
	 * @param p prix du produit
	 * @param currentQ quantit� du produit
	 */
	public Product(String n, Double p, Double currentQ) {
		super(n);
		this.price = p;
		this.currentQuantity = currentQ;
		quantityLevels = new HashMap<Integer,Double>();
		id = ++idMax;
	}
	
	/**
	 * @return the idMax
	 */
	public static int getIdMax() {
		return idMax;
	}

	/**
	 * @param idMax the idMax to set
	 */
	public static void setIdMax(int idMax) {
		Product.idMax = idMax;
	}

	/**
	 * Renvoie l'id du produit
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * D�finit l'id du produit
	 * @param id the id to set
	 */
	public void setId(int id) {
		id = id;
	}
	
	/**
	 * Renvoie le prix du produit
	 * @return prix du produit
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * D�finit le prix du produit
	 * @param nouveau prix du produit
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * Renvoie l'historique des quantit�s du produit
	 * @return the quantityLevels
	 */
	public HashMap<Integer, Double> getQuantityLevels() {
		return quantityLevels;
	}

	/**
	 * D�finit les quantit�s des produits
	 * @param quantityLevels the quantityLevels to set
	 */
	public void setQuantityLevels(HashMap<Integer, Double> quantityLevels) {
		this.quantityLevels = quantityLevels;
	}
	
	/**
	 * vend une certaine quantit� de produit un jour donn�e, et met � jour l'historique des quantit�s en fonction
	 * @param quantity la quantit� du produit vendue
	 * @param simulationDate le jour de la vente
	 * @throws QuantityHigherThanAvailabilityException
	 */
	public void sell(double quantity, int simulationDate) throws QuantityHigherThanAvailabilityException{
		if(quantity > currentQuantity){
			throw new QuantityHigherThanAvailabilityException(quantity);
		}
		else{
			this.currentQuantity -= quantity;
			quantityLevels.put(simulationDate, this.currentQuantity);
			soldQ+=quantity;
		}
	}
	
	/**
	 * Renvoie la quantit� actuelle du produit en stock
	 * @return
	 */
	public Double getCurrentQuantity() {
		return currentQuantity;
	}
	
	/**
	 * D�finit la quantit� actuelle du produit.
	 * @param q
	 */
	public void setCurrentQuantity(double q){
		currentQuantity = q;
	}
	
	
	/**
	 * Renvoie la quantit� du produit � la date d selon l'historique du produit
	 * @param d date
	 * @return
	 */
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

	/**
	 * @return the soldQ
	 */
	public Double getSoldQ() {
		return soldQ;
	}

	/**
	 * @param soldQ the soldQ to set
	 */
	public void setSoldQ(Double soldQ) {
		this.soldQ = soldQ;
	}
	
}
