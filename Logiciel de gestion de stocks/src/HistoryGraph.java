import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;


public class HistoryGraph extends Canvas {
	protected Category rootCategory;
	protected Calendar cal;
	protected Date startDate, endDate;	//On affiche les données entre les dates startDate et endDate.
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public HistoryGraph(Category rootC){
		this.rootCategory = rootC;
		this.cal = Calendar.getInstance();
	}

	public void setCategory(Category cat) {
		this.rootCategory = cat;
	}
	
	public Dimension getMinimumSize(){
		return new Dimension(400, 200);
	}
	
	public Dimension getPreferredSize(){
		return getMinimumSize();
	}
	
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
	
	public HashMap<Date,Double> computeCategoryQuantities(Category c){
		HashMap<Date,Double> categoryQuantities = new HashMap<Date,Double>();
		for(Date d:getDatesBetweenStartAndEndDates(startDate,endDate)){
			categoryQuantities.put(d, computeTotalQuantities(d,c));
		}
		return categoryQuantities;
	}
	
	public List<Date> getDatesBetweenStartAndEndDates(Date sDate, Date eDate){
		List<Date> dates = new LinkedList<Date>();
		Date dateCourrante = (Date) sDate.clone();
		while(dateCourrante.compareTo(eDate)<=0){
			dates.add(dateCourrante);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			dateCourrante = cal.getTime();
		}
		return dates;
	}
	
	public void paint(Graphics g){
		/*Dimension size = this.getSize();
		for(Category c:this.rootCategory.getChildren()){
			Product p = (Product)c;
			Point lastPoint = new Point(0,0);
			for(Date d:p.getQuantityLevels().keySet()){
				cal.setTime(d);
				cal.get
				g.drawLine(lastPoint.getX(), lastPoint.getY(), lastPoint.getX()+5, arg3);
			}
		}
		Dimension size = this.getSize();
		for(Category c:this.rootCategory.getChildren()){
			Product p = (Product)c;
			Set<Date> keySet = p.getQuantityLevels().keySet();
			for(Date d:keySet){
				if(d.compareTo(startDate)<0){
					Date diff = new Date();
					diff.setTime(d.getTime()-startDate.getTime());
					cal.setTime(diff);
					int nbOfDays = cal.get(Calendar.)
				}
			}
			
		}*/
		
		for(Category c:this.rootCategory.getChildren()){
			Product p = (Product)c;
			System.out.println("Niveau de " + p.getName() + ": " + p.getCurrentQuantity());
		}
	}
	
}
