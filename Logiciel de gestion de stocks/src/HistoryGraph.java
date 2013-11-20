import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;


public class HistoryGraph extends Canvas {
	protected Category rootCategory;
	protected Calendar cal;
	protected Calendar startDate, endDate;	//On affiche les données entre les dates startDate et endDate.
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate.getTime();
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate.setTime(startDate);
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate.getTime();
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate.setTime(endDate);
	}

	public HistoryGraph(Category rootC){
		this.rootCategory = rootC;
		this.cal = Calendar.getInstance();
		startDate = Calendar.getInstance();
		endDate = Calendar.getInstance();
	}

	public void setCategory(Category cat) {
		this.rootCategory = cat;
	}
	
	public Dimension getMinimumSize(){
		return new Dimension(400, 200);
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
	public double computeTotalQuantities(Date d, Category c){
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
	public TreeMap<Date,Double> computeCategoryQuantities(Category c){
		TreeMap<Date,Double> categoryQuantities = new TreeMap<Date,Double>();
		for(Date d:getDatesBetweenStartAndEndDates(startDate,endDate)){
			System.out.println(startDate.getTime()+ ";" + endDate.getTime());
			System.out.println("--------------------------------------"+d);
			categoryQuantities.put(d, computeTotalQuantities(d,c));
		}
		return categoryQuantities;
	}
	
	
	/**
	 * @param sDate Date de début (incluse)
	 * @param eDate Date de fin (incluse)
	 * @return Liste des dates comprises entre la date de début et la date de fin
	 */
	public List<Date> getDatesBetweenStartAndEndDates(Calendar sDate, Calendar eDate){
		List<Date> dates = new LinkedList<Date>();
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(sDate.getTime());
		while(currentDate.compareTo(eDate)<=0){
			dates.add(currentDate.getTime());
			currentDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dates;
	}
	
	public void paint(Graphics g){
		drawBackground(g);
		g.setColor(Color.black);
		//On récupère la taille du canvas pour pouvoir dessiner dans les bonnes proportions
		Dimension size = this.getSize();
		TreeMap<Date,Double> dataToDraw = computeCategoryQuantities(rootCategory);
		int xLast=0;
		int yLast=0;
		int x,y;
		for(Date d:dataToDraw.keySet()){
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			x = (int)((double)(c.getTimeInMillis()-startDate.getTimeInMillis())/(double)(endDate.getTimeInMillis()-startDate.getTimeInMillis()+1)*size.getWidth());
			y = (int)((double)(dataToDraw.get(d))/1500.0*size.getHeight());	//TODO 1500 doit être changé ici!
			y = (int)size.getHeight() - y;
			g.drawLine(xLast, yLast, x, y);
			xLast = x;
			yLast=y;
		}
	}
	
	
	/*
	 * Cette fonction dessine la grille, les axes, les échelles, la légende.
	 */
	public void drawBackground(Graphics g){
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
	
}
