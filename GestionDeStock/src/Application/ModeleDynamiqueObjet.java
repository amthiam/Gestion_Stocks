package Application;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


/**
 * Author : Imen
 * Classe permettant de modéliser un JTable  dynamique contient de produits 
 * 
 * 
 */
public class ModeleDynamiqueObjet extends AbstractTableModel {
    private final ArrayList<Product> prod = new ArrayList<Product>();
 
    String[] entetes = {"Id_Article", "Libellé", "Catégorie", "Prix unitaire", "nombre d'articles en stock","nombre d'articles vendus","chiffre d'affaires"};
    public ModeleDynamiqueObjet() {
        super();
        prod.add(new Product("imen", 20.0, 200.0));
        prod.add(new Product("test", 22.0, 100.0));
        prod.add(new Product("test2", 23.0, 224.0));
        prod.add(new Product("blabla", 19.0, 112.0));
        prod.add(new Product("bbbb", 5.0, 225.0));  prod.add(new Product("blabla", 19.0, 227.0));
        prod.add(new Product("bbbb", 5.0, 226.0));  prod.add(new Product("blabla", 19.0, 229.0));
        prod.add(new Product("bbbb", 5.0, 223.0));  prod.add(new Product("blabla", 19.0, 221.0));
        prod.add(new Product("bbbb", 5.0, 228.0));  prod.add(new Product("blabla", 19.0, 120.0));
        prod.add(new Product("bbbb", 5.0, 230.0));  prod.add(new Product("blabla", 19.0, 130.0));
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
        switch(columnIndex){
            case 0:
                return prod.get(rowIndex).getId();
            case 1:
                return prod.get(rowIndex).getName();
            case 2:
                return prod.get(rowIndex).getPrice();
            case 3:
                return prod.get(rowIndex).getCurrentQuantity();
            case 4:
                return prod.get(rowIndex).getCurrentQuantity();
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
}
