import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;


public class HistoryGraph extends Canvas {
	protected Category rootCategory;
	protected Calendar cal;
	protected Date startDate, endDate;	//On affiche les données entre les dates startDate et endDate.
	
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
