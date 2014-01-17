package Application;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;


/**
 * Author : Imen
 * Classe permettant de modéliser un JTable  dynamique contient de produits 
 * 
 * 
 */
public class ModeleDynamiqueObjet extends AbstractTableModel {
    private final ArrayList<Product> prod = new ArrayList<Product>();
    Category magasin;
 
    String[] entetes = {"Id_Article", "Libellé", "Catégorie", "Prix unitaire", "nombre d'articles en stock","nombre d'articles vendus","chiffre d'affaires"};
    public ModeleDynamiqueObjet(Category magasin) {
        super();
        this.magasin = magasin;
    }
 
    public int getRowCount() {
        return prod.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
    	System.out.println(rowIndex + " " + columnIndex);
        switch(columnIndex){
            case 0:
                return prod.get(rowIndex).getId();
            case 1:
                return prod.get(rowIndex).getName();
            case 2:
                return magasin.getParentCategory(prod.get(rowIndex)).getName();
            case 3:
                return prod.get(rowIndex).getPrice();
            case 4:
                return prod.get(rowIndex).getCurrentQuantity();
            case 5:
            	return prod.get(rowIndex).getSoldQ();
            case 6:
            	return prod.get(rowIndex).getSoldQ()*prod.get(rowIndex).getPrice();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addProduct(Product produit) {
        prod.add(produit);
 
        fireTableRowsInserted(prod.size() -1, prod.size() -1);
    }
 
    public void removeProduct(int rowIndex) {
        prod.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

	public void updateTableContent(Category cat) {
		prod.removeAll(prod);
		LinkedList<Product> listOfProducts = (LinkedList<Product>) cat.getProducts();
		for(Product p:listOfProducts){
			addProduct(p);
		}
	}
}
