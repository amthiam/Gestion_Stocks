import java.awt.*;

import javax.swing.*;



public class Stats extends Panel {
JButton getBackButton;
	
	public Stats(Application app){
		getBackButton = new JButton("Retour(2)");
		getBackButton.setVisible(true);
		getBackButton.addActionListener(app);
		
		this.add(getBackButton);
	}
	
	public JButton getGetBackButton(){
		return getBackButton;
	}
}