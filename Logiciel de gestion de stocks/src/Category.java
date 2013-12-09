import java.util.LinkedList;
import java.util.List;


public class Category {
	String name;
	List<Category> children;
	
	public Category(String n){
		this.name = n;
		children = new LinkedList<Category>();
	}
	
	public void addChild(Category c){
		children.add(c); 
	}
	
	public void removeChild(Category cat){
		if(children.contains(cat)){
			children.remove(cat);
		}
	}
	
	public List<Category> getChildren(){
		return this.children;
	}

	public void setName(String n){
		this.name = n;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name;
	}
	
}
