import java.awt.*;

import javax.swing.*;

public class HomeScreen extends Panel {
	Application app;
	boolean active;
	JButton buttonStructureManager;
	JButton buttonStats;
	
	public HomeScreen(Application app){
		this.app = app;
		active = false;
		//Création des trois bouttons de l'écran d'accueil
		buttonStructureManager = new JButton("Gestionnaire de catégories et produits");
		buttonStructureManager.setVisible(true);
		buttonStructureManager.addActionListener(app);
		this.add(buttonStructureManager);
		
		buttonStats = new JButton("Statistiques");
		buttonStats.setVisible(true);
		buttonStats.addActionListener(app);
		this.add(buttonStats);
	}
	
	public JButton getGoToStructureManagerButton(){
		return buttonStructureManager;
	}

	public JButton getGoToStatsButton() {
		return buttonStats;
	}
	
	public void activate(){
		active = true;
		if(app.getMagasin().checkCategoryStructure()){
			buttonStats.setEnabled(true);
		}
		else{
			buttonStats.setEnabled(false);
		}
	}
	
	public void inactivate(){
		active = false;
	}
}
