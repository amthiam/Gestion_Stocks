import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;






/*
 * Author : Clara
 * Classe permettant l'affichage de la fenetre "GESTION DES CATEGORIES ET DES PRODUITS"
 * 
 * 
 */




public class StructureManagerWindow extends Panel {
	boolean active;

	public StructureManagerWindow(Application app, Category magasin)  {
		active = false;
		// ************* Creation des bouttons ***************************
		JButton addProduct = new JButton("  Creer un nouveau produit  ");
		JButton addCategory = new JButton("Creer une nouvelle categorie");
		JButton deleteCategory = new JButton("Supprimer categorie ou produit");
		JButton modifyProduct = new JButton("    Modifier un produit    ");
		JButton modifyTree = new JButton("    Deplacer une categorie    ");
		JButton renameCategorie = new JButton("Renommer categorie ou produit");


		// ******************* Actions Listener  ****************************
		// Boutton "Creer un nouveau produit"
		addProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){               
				AddProductWindow fenetre1 = new AddProductWindow();
			}
		});

		// Boutton "Creer une nouvelle categorie"
		/*
		 * A compléter
		 */


		// Boutton "Supprimer categorie ou produi"                
		/*
		 * A completer
		 */


		// Boutton "Modifier un produit"
		/*
		 * A completer
		 */

		// Boutton "Deplacer une categorie"
		/*
		 * A completer
		 */

		// Boutton "Renommer categorie ou produit"
		/*
		 * A completer
		 */



		// *******************   Arrangement des bouttons ************************
		JPanel buttonZone = new JPanel();
		GridLayout south = new GridLayout();
		south.setColumns(3);
		south.setRows(2);
		south.setHgap(5);
		south.setVgap(5);

		buttonZone.setLayout(south);
		buttonZone.add(addProduct);
		buttonZone.add(addCategory);
		buttonZone.add(deleteCategory);
		buttonZone.add(modifyProduct);
		buttonZone.add(modifyTree);
		buttonZone.add(renameCategorie);
		buttonZone.setVisible(true);



		// ******************  Affichage de l'arbre **************************
		Arbre arbre = new Arbre(magasin);
		JScrollPane treeZone = new JScrollPane(arbre.arbre);


		this.add(treeZone, BorderLayout.CENTER);
		this.add(buttonZone, BorderLayout.SOUTH);
		this.setVisible(true);

	}
	
	public void activate(){
		active = true;
	}
	
	public void inactivate(){
		active = false;
	}
}


