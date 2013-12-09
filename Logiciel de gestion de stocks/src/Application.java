import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;


public final class Application extends JFrame implements ActionListener {
	protected HomeScreen homeScreen;
	protected StructureManagerWindow structureManagerScreen;
	protected Stats statsScreen;
	protected String requiredScreen;
	protected String formerScreen;
	protected Simulation sim;
	public Category magasin;
	
	public Application(){
		//Titre de la fenêtre de l'application
		super("Application de gestion de stocks V0.1");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setBounds(50,50,800,600);
		
		//Magasin
		magasin = new Category("Magasin");
		
		//Simulation
		sim = new Simulation();
		/*
		Thread t = new Thread(sim);
		t.start();
		*/
		
		//Ecran d'accueil
		homeScreen = new HomeScreen(this);
		
		//Ecran du gestionnaire de structure
		structureManagerScreen = new StructureManagerWindow(this, magasin);
		
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
					homeScreen.activate();
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
					statsScreen.activate();
					formerScreen = "Stats Screen";
				}
			}
		}
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
		}/*
		else if(b==structureManagerScreen.getGetBackButton()){
			this.requiredScreen = "Home Screen";
		}*/
		else if(b==statsScreen.getGetBackButton()){
			this.requiredScreen = "Home Screen";
		}
		else if(b==structureManagerScreen.getGetBackButton()){
			this.requiredScreen = "Home Screen";
		}
	}

	public int getSimulationDate() {
		return sim.getSimulationDate();
	}
	
	public Simulation getSim(){
		return sim;
	}

	/**
	 * @return the magasin
	 */
	public Category getMagasin() {
		return magasin;
	}

	/**
	 * @param magasin the magasin to set
	 */
	public void setMagasin(Category magasin) {
		this.magasin = magasin;
	}
}
