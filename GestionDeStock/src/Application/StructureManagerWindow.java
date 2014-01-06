package Application;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;






/*
 * Author : Clara
 * Classe permettant l'affichage de la fenetre "GESTION DES CATEGORIES ET DES PRODUITS"
 * 
 * 
 */




public class StructureManagerWindow extends Panel implements ActionListener, MouseListener {
	Application app;
	Category magasin;
	boolean active;
	JButton addProduct, deleteCategory, addCategory, modifyProduct, modifyTree, renameCategorie, getBackButton;
	JTable productInfoTable;
	ProductInfoModel productInfoModel;
	Arbre arbre;
	int a=0;
	DefaultMutableTreeNode nodeToMove;	//utilisé pour le déplacement d'une catï¿½gorie

	public StructureManagerWindow(Application app, Category magasin)  {
		active = false;
		this.magasin = magasin;
		this.app = app;
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
		renameCategorie.addActionListener(this);
		addCategory.addActionListener(this);
		modifyProduct.addActionListener(this);
		modifyTree.addActionListener(this);



		// *******************   Arrangement des bouttons ************************
		JPanel buttonZone = new JPanel();
		GridLayout south = new GridLayout();
		south.setColumns(3);
		south.setRows(3);
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


		//Affichage du tableau d'informations du produit
		String[] titles = {"id", "Nom produit", "Prix unitaire", "Quantitï¿½ en stock"};
		String[] defaultProductData = {"","","",""};
		productInfoModel = new ProductInfoModel(titles);
		productInfoModel.setProductData(defaultProductData);
		productInfoTable = new JTable(productInfoModel);



		// ******************  Affichage de l'arbre **************************
		arbre = new Arbre(magasin);
		JScrollPane treeZone = new JScrollPane(arbre.arbre);
		arbre.arbre.addMouseListener(this);


		BorderLayout test = new BorderLayout();
		this.setLayout(test);

		this.add(treeZone, BorderLayout.NORTH);
		this.add(productInfoTable, BorderLayout.CENTER);
		this.add(buttonZone, BorderLayout.SOUTH);
		this.setVisible(true);


	}


	public void addProduct(Category cat, Product p){
		cat.addChild(p);
		app.getSim().addProduct(p);
	}

	public void addCategory(Category cat, Category p){
		cat.addChild(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode selectedNode = arbre.getLastSelectedNode();
		if (selectedNode != null){
			if((JButton) e.getSource() == addProduct){
				a=0;
				Category cat = (Category)(selectedNode.getUserObject());
				AddProductWindow addWindow = new AddProductWindow(this, cat);
			}
			else if(((JButton) e.getSource() == addCategory)){
				a=0;
				Category cat = (Category)(arbre.getLastSelectedNode().getUserObject());
				AddCategoryWindow addWindow = new AddCategoryWindow(this, cat);
			}
			else if((JButton) e.getSource() == modifyTree){
				a=1;
				nodeToMove = arbre.getLastSelectedNode();
			}
			else if(((JButton) e.getSource() == renameCategorie)){
				a=0;
				Category cat = (Category)(arbre.getLastSelectedNode().getUserObject());
				RenameCategoryWindow renameWindow = new RenameCategoryWindow(this, cat);

			}
			else if((JButton) e.getSource() == modifyProduct) {
				a=0;
				Product produit = (Product) (arbre.getLastSelectedNode().getUserObject());
				ModifyProductWindow addWindow = new ModifyProductWindow(this, produit); 
			}
			else if((JButton) e.getSource() == deleteCategory){
				a=0;
				Category cat = (Category)(arbre.getLastSelectedNode().getUserObject());
				DeleteCategoryWindow addWindow = new DeleteCategoryWindow(this, cat);

			}


		}
	}

	private void modifyTree(){
		Category catToMove = (Category) nodeToMove.getUserObject();
		DefaultMutableTreeNode newParentNode;
		newParentNode = arbre.getLastSelectedNode();
		Category newParentCat = (Category) newParentNode.getUserObject();
		if(!(newParentCat instanceof Product)){
			Category parent = magasin.getParentCategory(catToMove);
			parent.removeChild(catToMove);
			newParentCat.addChild(catToMove);
			arbre.updateTree();
		}
		else{
			JOptionPane.showMessageDialog(this, "Erreur: veuillez recommencer l'opï¿½ration et sï¿½lectionner une catï¿½gorie comme nouveau parent, non pas un produit", "Erreur utilisateur",
                    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateTree() {
		arbre.updateTree();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(arbre.arbre.getBounds().contains(e.getPoint())){
			//Si l'utilisateur a cliquï¿½ sur l'arbre on regarde si le noeud sï¿½lectionnï¿½ est une catï¿½gorie ou un produit
			//et on active/dï¿½sactive certains boutons en consï¿½quence
			DefaultMutableTreeNode selectedNode = arbre.getLastSelectedNode();
			if(selectedNode != null){
				Category selectedObject = (Category) selectedNode.getUserObject();
				if(a==1){
					modifyTree();
					a=0;
				}
				if(selectedObject instanceof Product){
					addProduct.setEnabled(false);
					addCategory.setEnabled(false);
					modifyProduct.setEnabled(true);
					modifyTree.setEnabled(true);
					//La condition qui suit permet de s'assurer le bouton se suppression de catï¿½gorie n'est pas disponible pour la racine
					if(!arbre.getLastSelectedNode().isRoot()){
						deleteCategory.setEnabled(true);
					}
					else{
						deleteCategory.setEnabled(false);
					}
					String[] pData = {((Product) selectedObject).getId()+"", selectedObject.getName(), ((Product) selectedObject).getPrice().toString(),
							((Product) selectedObject).getCurrentQuantity().toString()};
					productInfoModel.setProductData(pData);
					productInfoTable.update(productInfoTable.getGraphics());
				}
				else if(selectedObject instanceof Category){
					addProduct.setEnabled(true);
					addCategory.setEnabled(true);
					modifyProduct.setEnabled(false);
					modifyTree.setEnabled(true);
					//La condition qui suit permet de s'assurer le bouton se suppression de catï¿½gorie n'est pas disponible pour la racine
					if(!selectedNode.isRoot()){
						deleteCategory.setEnabled(true);
					}
					else{
						deleteCategory.setEnabled(false);
					}
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
        
         public void activate() {
        this.magasin=app.getMagasin();
        arbre.setRootCategory(magasin);
        this.updateTree();
        System.out.println("Le nom du magasin est" + magasin.getName());
    }
}


