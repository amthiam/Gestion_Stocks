import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;


public class HistoryGraph extends Canvas {
	protected ArrayList<Category> categoriesToShow;
	protected int nbDays, endDate;	//On affiche les données entre les dates startDate et endDate.
	protected static Color[] drawingColors = {Color.black, Color.blue, Color.red, Color.green, Color.orange, Color.white, Color.yellow,
											Color.magenta, Color.pink, Color.cyan};
	
	/**
	 * @return the nbOfDays
	 */
	public int getNbDays() {
		return nbDays;
	}

	/**
	 * @param nbDays the number of days that should be shown on the graph. 
	 */
	public void setNbDays(int nbDays) {
		this.nbDays = nbDays;
	}

	/**
	 * @return the endDate
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate la date de fin.
	 */
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Constructeur. 
	 */
	public HistoryGraph(){
		categoriesToShow = new ArrayList<Category>();
		nbDays=20;
		endDate = 0;
	}

	/**
	 * Ajoute une catégorie dont la courbe sera affichée par le graphe.
	 * @param cat la catégorie à ajouter
	 */
	public void addCategoryToShow(Category cat) {
		if(categoriesToShow.size() < drawingColors.length){
			categoriesToShow.add(cat);
		}
		else{
			String message = "Le nombre de produits et catégories pouvant être représentés sur le graphe se limite à " + drawingColors.length;
			String title = "Ajout impossible!";
			JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * @return la dimension minimale
	 */
	public Dimension getMinimumSize(){
		return new Dimension(500, 400);
	}
	
	/**
	 * @return la dimension maximale
	 */
	public Dimension getMaximumSize(){
		return getMinimumSize();
	}
	
	/**
	 * @return la dimension préférée
	 */
	public Dimension getPreferredSize(){
		return getMinimumSize();
	}
	
	
	/**
	 * @param d La date pour laquelle on souhaite calculer la quantité
	 * @param c La catégorie
	 * @return La somme des quantités des articles de la catégorie à la date d.
	 */
	public double computeTotalQuantities(int d, Category c){
		if(c.getChildren().size()==0){
			Product p = (Product)c;
			return p.getQuantityLevelOnDate(d);
		}
		else{
			double somme = 0.0;
			for(Category cat:c.getChildren()){
				somme += computeTotalQuantities(d, cat);
			}
			return somme;
		}
	}

	
	/**
	 * @param c La catégorie dont on souhaite calculer les quantités pour chacune des dates
	 * @return un HashMap qui à une date associe une quantité
	 */
	public TreeMap<Integer,Double> computeCategoryQuantities(Category c){
		TreeMap<Integer,Double> categoryQuantities = new TreeMap<Integer,Double>();
		for(int d=Math.max(0,endDate-nbDays);d<=endDate;d++){
			categoryQuantities.put(d, computeTotalQuantities(d,c));
		}
		return categoryQuantities;
	}
	
	/**
	 * Cette méthode est utilisée pour implémenter un double-buffering, qui évite le clignotement de l'affichage lors de sa mise à jour.
	 * @param g le graphique sur lequel dessiné.
	 */
	public void update(Graphics g){	//Fonction utile pour le double-buffering, qui évite des problèmes de clignotement
		Image memoryImage = createImage(getSize().width, getSize().height);
		Graphics gMemoryImage = memoryImage.getGraphics();
		paint(gMemoryImage);
		g.drawImage(memoryImage, 0, 0, this);
	}
	
	/**
	 * Redéfinition de la méthode paint. Gère le dessin des courbes.
	 * @param g graphique sur lequel dessiné
	 */
	public void paint(Graphics g){
		drawBackground(g);
		//On récupère la taille du canvas pour pouvoir dessiner dans les bonnes proportions
		Dimension size = this.getSize();
		for(Category cat:categoriesToShow){
			//Couleur du dessin
			g.setColor(drawingColors[categoriesToShow.indexOf(cat)]);
			//Données de la catégorie
			TreeMap<Integer,Double> dataToDraw = computeCategoryQuantities(cat);
			//Dessin
			boolean b = false;	//Ce booléen est utilisé pour ne pas dessiner les points de quantité nulle pour les cas où le produit n'existait pas.
			int xLast=-1;
			int yLast=0;
			int x,y;
			for(int d:dataToDraw.keySet()){
				x = (int)((double)(d-endDate+nbDays)/(double)nbDays*size.getWidth());
				y = (int)((double)(dataToDraw.get(d))/1500.0*size.getHeight());
				y = (int)size.getHeight() - y;
				if(xLast != -1 && (yLast != 0 || b)){
					b = true;
					g.drawLine(xLast, yLast, x, y);
					//g.drawLine(xLast, yLast-1, x, y-1);
				}
				xLast = x;
				yLast=y;
			}
		}
	}
	
	
	/**
	 * Fonction qui dessine la grille, les échelles et la légende
	 * @param g
	 */
	private void drawBackground(Graphics g){
		g.setColor(Color.gray);		//TODO à rendre paramétrisable
		Dimension size = this.getSize();
		int w = (int)size.getWidth();
		int h = (int)size.getHeight();
		//Dessin de la grille
		int nbVertical = 10;	//TODO à rendre paramétrisable
		int intervalHeight = (int)(h/nbVertical);
		for(int i=0;i<nbVertical;i++){
			//dessin de la ligne
			g.drawLine(0, i*intervalHeight, w, i*intervalHeight);
			//dessin de l'échelle
			g.drawString((int)((double)(nbVertical-i)/(double)nbVertical*1500.0)+"", 0, i*intervalHeight);	//TODO 1500 à changer ici!
		}
		//Dessin de l'abscisse et de l'échelle
		int nbHorizontal = 10;
		int intervalWidth = (int)(w/nbHorizontal);
		int j;
		for(int i=0;i<nbHorizontal;i++){
			//dessin du trait
			g.drawLine(i*intervalWidth, h, i*intervalWidth, h-10);
			//écriture de l'échelle
			j=(int)((double)i/(double)nbHorizontal*20.0)+endDate-nbDays+1;
			if(j>=0){
				g.drawString(j+"", i*intervalWidth, h-10);	//TODO 20 à changer
			}
		}
		
		//Dessin de la légende
		g.setColor(Color.white);
		g.fillRoundRect(50, 5, 150, 15*categoriesToShow.size(), 5, 5);
		for(Category cat:categoriesToShow){
			//Couleur du dessin
			g.setColor(drawingColors[categoriesToShow.indexOf(cat)]);
			g.drawLine(50, 10+categoriesToShow.indexOf(cat)*15, 65, 10+categoriesToShow.indexOf(cat)*15);
			g.setColor(Color.black);
			g.drawString(cat.getName(), 75, 15+categoriesToShow.indexOf(cat)*15);
		}
		
	}
	
	/**
	 * Retire la catégorie de la liste de celles dont les courbes doivent être affichées.
	 * @param c la catégorie à retirer de la liste.
	 */
	public void removeCategory(Category c) {
		categoriesToShow.remove(c);
	}
}
