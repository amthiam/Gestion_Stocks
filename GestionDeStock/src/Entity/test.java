package Entity;

import java.util.List;

public class test {

	public static void main(String[] args) {
		//Categories c=new Categories(25, "rabii", 22.0, 20, 20, 21);
		
		IMetierCategoriesImpl metier=new IMetierCategoriesImpl();
		/*metier.insetionProduit(c);
		List<Categories> prods=metier.getProduitParMotCle("asus") ;
		for(Categories p: prods){
			System.out.println(p.getNom());
			System.out.println("************");
		}
*/
		metier.supprimerProduits();
	} 
	}


