import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;



public class Stats extends Panel implements ActionListener, ChangeListener, MouseListener {
	Application app;
	Simulation simulation;
	HistoryGraph graphe;
	Arbre categoriesTree;
	JButton getBackButton, startStopSimulationB, endSimulationB, addCategoryInGraphB, removeCategoryFromGraphB;
	JSlider simulationSpeedSlider;
	JLabel simulationSpeedLabel, simulationDateLabel;
	JScrollPane graphScroll;
	boolean active;
	int nbOfDaysToShow = 20;

	public Stats(Application app){
		active = false;
		this.app = app;
		simulation = app.getSim();
		//Ajout du boutton de retour
		getBackButton = new JButton("Retour");
		getBackButton.setVisible(true);
		getBackButton.addActionListener(app);
		getBackButton.addActionListener(this);
		this.add(getBackButton);
		//Ajout des boutons démarer/stopper/Arrêter simulation
		startStopSimulationB = new JButton("Lancer la simulation");
		startStopSimulationB.setVisible(true);
		startStopSimulationB.addActionListener(this);
		this.add(startStopSimulationB);
		
		//Ajout du texte indiquant la date de la simulation
		simulationDateLabel = new JLabel("Date de la simulation: jour " + simulation.getSimulationDate(), SwingConstants.HORIZONTAL);
		this.add(simulationDateLabel);
		
		//Affichage de l'arbre, ce qui permettra de sélectionner les catégories et produits qu'on souhaite afficher
		categoriesTree = new Arbre(app.getMagasin());
		this.add(categoriesTree.arbre);
		categoriesTree.arbre.addMouseListener(this);
		//Ajout du boutton permettant d'ajouter une catégorie dans le graphe
		addCategoryInGraphB = new JButton("Ajouter la catégorie");
		addCategoryInGraphB.addActionListener(this);
		addCategoryInGraphB.setEnabled(false);
		this.add(addCategoryInGraphB);
		//Ajoutt du boutton permettant la suprresion d'une catégorie du graphe
		removeCategoryFromGraphB = new JButton("Retirer la catégorie");
		removeCategoryFromGraphB.addActionListener(this);
		this.add(removeCategoryFromGraphB);
		
		//Affichage des statistiques
		graphe = new HistoryGraph();
		graphe.setEndDate(app.getSimulationDate());
		graphe.setVisible(true);
		graphe.setLocation(100, 100);
		//Ancienne version 		this.add(graphe);
		graphe.setVisible(true);
		graphScroll = new JScrollPane(graphe);
		graphScroll.setSize(200,400);
		graphScroll.setVisible(true);
		this.add(graphScroll);
		
		//Ajout du slider qui permettra le contrôle de la vitesse de simulation
		simulationSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 1,10,3);
		simulationSpeedSlider.addChangeListener(this);
		simulationSpeedSlider.setVisible(true);
		this.add(simulationSpeedSlider);
		//Ajout du texte indiquant la valeur de la vitesse de simulations
		simulationSpeedLabel = new JLabel(simulationSpeedSlider.getValue()+" jours simulés par seconde", SwingConstants.HORIZONTAL);
		this.add(simulationSpeedLabel);
	}
	
	/**
	 * @return the getBackButton
	 */
	public JButton getGetBackButton(){
		return getBackButton;
	}
	
	/**
	 * @return the startStopSimulationB
	 */
	public JButton getStartStopSimulationB() {
		return startStopSimulationB;
	}

	public void activate(){
		this.active = true;
		while(active){
			try {
				simulation.simulationStep();
				simulationDateLabel.setText("Date de la simulation: jour " + simulation.getSimulationDate());
			} catch (QuantityHigherThanAvailabilityException e1) {
				e1.printStackTrace();
			}
			graphe.setEndDate(app.getSimulationDate()-1);
			graphe.update(graphe.getGraphics());
			try {
				Thread.sleep((int)(1.0/(double)simulation.getSimulationSpeed()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getGetBackButton()){
			this.active = false;
			simulation.stopSimulation();	//On arrêt la simulation lorsqu'on sort du mode
			startStopSimulationB.setText("Lancer la simulation");
		}
		else if(e.getSource() == startStopSimulationB){
			boolean simIsActive = simulation.startPause();
			if(simIsActive){
				startStopSimulationB.setText("Mettre la simulation en pause");
				graphe.setNbDays(nbOfDaysToShow);
			}
			else{
				startStopSimulationB.setText("Lancer la simulation");
				graphe.setNbDays(Math.max(nbOfDaysToShow, 20));
			}
		}
		else if(e.getSource() == addCategoryInGraphB){
			Category selectedCategory = (Category)(((DefaultMutableTreeNode) categoriesTree.arbre.getLastSelectedPathComponent()).getUserObject());
			graphe.addCategoryToShow(selectedCategory);
		}
		else if(e.getSource() == removeCategoryFromGraphB){
			Category selectedCategory = (Category)(((DefaultMutableTreeNode) categoriesTree.arbre.getLastSelectedPathComponent()).getUserObject());
			graphe.removeCategory(selectedCategory);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == simulationSpeedSlider){
			simulation.setSimulationSpeed(simulationSpeedSlider.getValue());
			simulationSpeedLabel.setText(simulation.getSimulationSpeed()+" jours simulés par seconde.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(categoriesTree.arbre.getLastSelectedPathComponent()!=null){
			addCategoryInGraphB.setEnabled(true);
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
}