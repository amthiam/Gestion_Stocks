package Application;
import javax.swing.JFrame;


public class ModifyTreeWindow extends JFrame{

	/**
	 * @param args
	 */
	public ModifyTreeWindow(){
		
		JFrame addProductWindow = new JFrame();
		addProductWindow.setTitle("Deplacer une categorie");
		addProductWindow.setSize(500, 500);
		addProductWindow.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		addProductWindow.setLocationRelativeTo(null);
		addProductWindow.setResizable(false);
		addProductWindow.setVisible(true);
		
		/*
		 * a completer avec
		 * 		- Zones de textes
		 * 				Zone de texte pour le nom du nouveau produit
		 * 				Noeud Parent a renseigner
		 * 		- Boutton Valider
		 */
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
