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
	 * @param startDate the startDate to set
	 */
	public void setNbDays(int startDate) {
		this.nbDays = startDate;
	}

	/**
	 * @return the endDate
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public HistoryGraph(){
		categoriesToShow = new ArrayList<Category>();
		nbDays=20;
		endDate = 0;
	}

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
	
	public Dimension getMinimumSize(){
		return new Dimension(300, 400);
	}
	
	public Dimension getMaximumSize(){
		return getMinimumSize();
	}
	
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
	
	public void paint(Graphics g){
		drawBackground(g);
		//On récupère la taille du canvas pour pouvoir dessiner dans les bonnes proportions
		Dimension size = this.getSize();
		for(Category cat:categoriesToShow){
			g.setColor(drawingColors[categoriesToShow.indexOf(cat)]);
			TreeMap<Integer,Double> dataToDraw = computeCategoryQuantities(cat);
			int xLast=-1;
			int yLast=0;
			int x,y;
			for(int d:dataToDraw.keySet()){
				x = (int)((double)(d-endDate+nbDays)/(double)nbDays*size.getWidth());
				y = (int)((double)(dataToDraw.get(d))/1500.0*size.getHeight());	//TODO 1500 doit être changé ici!
				y = (int)size.getHeight() - y;
				if(xLast != -1){
					g.drawLine(xLast, yLast, x, y);
					//g.drawLine(xLast, yLast-1, x, y-1);
				}
				xLast = x;
				yLast=y;
			}
		}
	}
	
	
	/*
	 * Cette fonction dessine la grille, les axes, les échelles, la légende.
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
			g.drawLine(0, i*intervalHeight, w, i*intervalHeight);
		}
	}

	public void removeCategory(Category c) {
		categoriesToShow.remove(c);
	}
	
}
