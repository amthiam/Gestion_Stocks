package Entity;

import java.util.List;

public interface IMetierCategories {
public void addProduit(Categories p);
public List<Categories> getProduitParMotCle(String mc);
public void insetionProduit(Categories c);
public void supprimerProduits();
}
