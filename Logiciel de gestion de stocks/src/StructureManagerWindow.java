import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;






/*
 * Author : Clara
 * Classe permettant l'affichage de la fenetre "GESTION DES CATEGORIES ET DES PRODUITS"
 * 
 * 
 */




public class StructureManagerWindow extends Panel implements ActionListener, MouseListener {
	boolean active;
	JButton addProduct, deleteCategory, addCategory, modifyProduct, modifyTree, renameCategorie, getBackButton;
	Arbre arbre;

	public StructureManagerWindow(Application app, Category magasin)  {
		active = false;
		// ************* Creation des bouttons ***************************
		addProduct = new JButton("  Creer un nouveau produit  ");
		addCategory = new JButton("Creer une nouvelle categorie");
		deleteCategory = new JButton("Supprimer categorie ou produit");
		modifyProduct = new JButton("    Modifier un produit    ");
		modifyTree = new JButton("    Deplacer une categorie    ");
		renameCategorie = new JButton("Renommer categorie ou produit");
		getBackButton = new JButton("Retour");
		getBackButton.addActionListener(app);
		
		addProduct.setEnabled(false);
		addCategory.setEnabled(false);
		deleteCategory.setEnabled(false);
		modifyProduct.setEnabled(false);
		modifyTree.setEnabled(false);
		renameCategorie.setEnabled(false);


		// ******************* Actions Listener  ****************************
		// Boutton "Creer un nouveau produit"
		addProduct.addActionListener(this);
		deleteCategory.addActionListener(this);

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
		buttonZone.add(getBackButton);
		buttonZone.setVisible(true);



		// ******************  Affichage de l'arbre **************************
		arbre = new Arbre(magasin);
		JScrollPane treeZone = new JScrollPane(arbre.arbre);
		arbre.arbre.addMouseListener(this);


		this.add(treeZone, BorderLayout.CENTER);
		this.add(buttonZone, BorderLayout.SOUTH);
		this.setVisible(true);

	}
	
	public void addProduct(Category cat, Product p){
		cat.addChild(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton) e.getSource() == addProduct){
			Category cat = (Category)(arbre.getLastSelectedNode().getUserObject());
			AddProductWindow addWindow = new AddProductWindow(this, cat);
		}
		else if((JButton) e.getSource() == deleteCategory){
			//TODO à compléter
		}
	}

	public void updateTree() {
		arbre.updateTree();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(arbre.arbre.getBounds().contains(e.getPoint())){
			//Si l'utilisateur a cliqué sur l'arbre on regarde si le noeud sélectionné est une catégorie ou un produit
			//et on active/désactive certains boutons en conséquence
			Object selectedObject = arbre.getLastSelectedNode().getUserObject();
			if(selectedObject instanceof Product){
				addProduct.setEnabled(false);
				addCategory.setEnabled(false);
				modifyProduct.setEnabled(true);
				//La condition qui suit permet de s'assurer le bouton se suppression de catégorie n'est pas disponible pour la racine
				if(!arbre.getLastSelectedNode().isRoot()){
					deleteCategory.setEnabled(true);
				}
				else{
					deleteCategory.setEnabled(false);
				}
			}
			else if(selectedObject instanceof Category){
				addProduct.setEnabled(true);
				addCategory.setEnabled(true);
				modifyProduct.setEnabled(false);
				//La condition qui suit permet de s'assurer le bouton se suppression de catégorie n'est pas disponible pour la racine
				if(!arbre.getLastSelectedNode().isRoot()){
					deleteCategory.setEnabled(true);
				}
				else{
					deleteCategory.setEnabled(false);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the getBackButton
	 */
	public JButton getGetBackButton() {
		return getBackButton;
	}

	/**
	 * @param getBackButton the getBackButton to set
	 */
	public void setGetBackButton(JButton getBackButton) {
		this.getBackButton = getBackButton;
	}
}


