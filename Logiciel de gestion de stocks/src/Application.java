import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;


public class Application extends JFrame implements ActionListener {
	HomeScreen homeScreen;
	GestionnaireStructure structureManagerScreen;
	Stats statsScreen;
	String requiredScreen;
	String formerScreen;
	
	public Application(){
		//Titre de la fenêtre de l'application
		super("Application de gestion de stocks V0.1");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setBounds(50,50,800,600);
		
		//On ajoute quelques produits
		Product banane = new Product("Banane", 0.25, 500.0);
		Product cerise = new Product("Cerise", 0.02, 500.0);
		Product clementine = new Product("Clémentine", 0.35, 500.0);
		
		//Nouvelle catégorie
		Category yahourts = new Category("yahourts");
		Product petitEncas = new Product("Petit Encas");
		yahourts.addChild(petitEncas);
		
		//Nouvelle catégorie
		Category fruits = new Category("Fruits");
		fruits.addChild(banane);
		fruits.addChild(cerise);
		fruits.addChild(clementine);
		
		//Magasin
		Category magasin = new Category("Magasin");
		magasin.addChild(fruits);
		magasin.addChild(yahourts);
		
		//Simulation
		Simulation sim = new Simulation();
		sim.addProduct(banane);
		sim.addProduct(cerise);
		sim.addProduct(clementine);
		Thread t = new Thread(sim);
		t.start();
		
		//Ecran d'accueil
		homeScreen = new HomeScreen(this);
		
		//Ecran du gestionnaire de structure
		structureManagerScreen = new GestionnaireStructure(this);
		
		//Ecran des stats
		statsScreen = new Stats(this);
		
		formerScreen = "";
		requiredScreen= "Home Screen";
		//Affichage de l'écran d'accueil
		this.setVisible(true);
		while(true){
			if(!formerScreen.equals(requiredScreen)){
				this.getContentPane().removeAll();
				if(requiredScreen.equals("Home Screen")){
					homeScreen.setVisible(true);
					this.getContentPane().add(homeScreen);
					formerScreen = "Home Screen";
				}
				else if(requiredScreen.equals("Structure Manager")){
					this.getContentPane().add(structureManagerScreen);
					structureManagerScreen.setVisible(true);
					this.setVisible(true);
					formerScreen = "Structure Manager";
				}
				else if(requiredScreen.equals("Stats Screen")){
					this.getContentPane().add(statsScreen);
					statsScreen.setVisible(true);
					this.setVisible(true);
					formerScreen = "Stats Screen";
				}
			}
		}
		
		//Affichage des statistiques
		/*
		HistoryGraph graphe = new HistoryGraph(fruits);
		graphe.setEndDate(sim.getSimulationDate());
		graphe.setStartDate((Date)sim.getSimulationDate().clone());
		graphe.setVisible(true);
		graphe.setLocation(100, 100);
		this.add(graphe);
		this.setVisible(true);
		
		while(true){
			graphe.setEndDate(sim.getSimulationDate());
			graphe.setVisible(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		//fruits.afficherPrix(fruits);
		

	}
	
	public static void main(String[] args) {
		Application app = new Application();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b==homeScreen.getGoToStructureManagerButton()){
			this.requiredScreen = "Structure Manager";
		}
		else if(b==homeScreen.getGoToStatsButton()){
			this.requiredScreen = "Stats Screen";
		}
		else if(b==structureManagerScreen.getGetBackButton()){
			this.requiredScreen = "Home Screen";
		}
		else if(b==statsScreen.getGetBackButton()){
			this.requiredScreen = "Home Screen";
		}
	}
}
