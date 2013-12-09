import javax.swing.JScrollPane;
import javax.swing.tree.*;
import javax.swing.*;






public class Arbre extends JTree{
	/**
	 * @param args
	 */
	protected JTree arbre;
	protected DefaultTreeModel treeModel;
	protected DefaultMutableTreeNode node;
	protected Category rootCategory;

	// *********** Constructeur *********************
	public Arbre(Category magasin){
		rootCategory = magasin;
		node = buildTree(magasin);
		treeModel = new DefaultTreeModel(node);
		arbre = new JTree(treeModel);
		arbre.setEditable(true);
	}


	// **************** Methodes ********************
	/*
	 * Methode permettant de creer un arbre
	 * Methode recursive
	 * Parametre en entree : category
	 */
	private DefaultMutableTreeNode buildTree(Category cat){
		//Création d'une racine initiale
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(cat);
		for (Category child : cat.children){
			racine.add(buildTree(child));
		}
		return racine;
	}
	
	public DefaultMutableTreeNode getLastSelectedNode(){
		return (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
	}



	/*
	 * Methode permettant de supprimer une categorie de l'arbre
	 */
	public void removeCategoryFromTree(Category cat){
		/*
		 * A completer
		 */

	}


	/*
	 * Methode permettant d'ajouter une categorie a l'arbre
	 * Paramètre d'entree : 
	 *                         categorie mere categorieMerea laquelle rattacher la categorie a rajouter
	 *                         categorie a rajouter
	 */
	public void addCategoryToTree(Category categorieMere, Category categorie){
		/*
		 * A completer
		 */

	}


	/*
	 * 
	 * 
	 */
	public DefaultMutableTreeNode findNode(Category cat){

		DefaultMutableTreeNode del = new DefaultMutableTreeNode();
		return del;
		/*
		 * a completer
		 */
	}
	
	public void updateTree(){
		node = buildTree(rootCategory);
		treeModel.setRoot(node);
		treeModel.reload();
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Category magasin = new Category("Magasin");
	}


}

