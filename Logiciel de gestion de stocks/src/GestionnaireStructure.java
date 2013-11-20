import java.awt.*;

import javax.swing.*;



public class GestionnaireStructure extends Panel {
	JButton getBackButton;
	
	public GestionnaireStructure(Application app){
		getBackButton = new JButton("Retour(1)");
		getBackButton.setVisible(true);
		getBackButton.addActionListener(app);
		
		this.add(getBackButton);
	}
	
	public JButton getGetBackButton(){
		return getBackButton;
	}
}
