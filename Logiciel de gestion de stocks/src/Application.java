import java.awt.Frame;
import java.awt.Graphics;


public class Application extends Frame {
	
	public Application(){
		//Titre de la fenêtre de l'application
		super("Application de gestion de stocks V0.1");
		
		this.setBounds(50,50,800,600);
		
		//On ajoute quelques produits
		Product banane = new Product("Banane", 0.25, 500.0);
		Product cerise = new Product("Cerise", 0.02, 5000.0);
		Product clementine = new Product("Clémentine", 0.35, 500.0);
		
		//Nouvelle catégorie
		Category fruits = new Category("Fruits");
		fruits.addChild(banane);
		fruits.addChild(cerise);
		fruits.addChild(clementine);
		
		//Simulation
		Simulation sim = new Simulation();
		sim.addProduct(banane);
		sim.addProduct(cerise);
		sim.addProduct(clementine);
		Thread t = new Thread(sim);
		//t.start();
		
		//Affichage des statistiques
		HistoryGraph graphe = new HistoryGraph(fruits);
		this.setVisible(true);
		
		Graphics g = this.getGraphics();
		/*
		while(true){
			graphe.update(g);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		fruits.afficherPrix(fruits);

	}
	
	public static void main(String[] args) {
		Application app = new Application();
	}
}
