import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;



/**
 * @author Arthur et Imen
 * 
 */
public class Stats extends Panel implements ActionListener, ChangeListener, MouseListener {
	private static final Color Blue = Color.blue;
	Application app;
	Simulation simulation;
	HistoryGraph graphe;
	Arbre categoriesTree;
	JButton getBackButton, startStopSimulationB, endSimulationB, addCategoryInGraphB, removeCategoryFromGraphB;
	ButtonGroup radioGroup;
	JRadioButton quantitiesButton, benefitButton;
	JSlider simulationSpeedSlider;
	JLabel simulationSpeedLabel, simulationDateLabel;
	JScrollPane graphScroll;
	boolean active;
	int nbOfDaysToShow = 20;
	JPanel jpanel=new JPanel();
	JPanel jpanelVitesse= new JPanel();
	JPanel jpanelArbre= new JPanel();  
	JLabel jlabVitesse=new JLabel();
	JPanel jpanelRetour= new JPanel();
	
	
	/**
	 * Constructeur. Construit les boutons, l'arbre de type Arbre, le graphe de type HistoryGraph, et plus généralement l'interface graphique.
	 * Définit les listeners des différents objets de l'interface.
	 * @param app Objet Application appelant
	 */
	public Stats(Application app){
		this.setLayout(null);
		active = false;
		this.app = app;
		jpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"test"));
		jpanelVitesse.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Contôler la simulation"));
		jpanelVitesse.setBounds(400, 505, 600,75);
		jpanelRetour.setBounds(50,525,250,150);
		jpanel.setBounds(20,375,250,125);
		jpanelArbre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"L'arbre du produits "));
		jpanelArbre.setBounds(20,65,250, 300);
		this.add(jpanelArbre);
		this.add(jpanelVitesse);
		this.add(jpanel);
		this.add(jpanelRetour);
		simulation = app.getSim();
		//Ajout du boutton de retour
		getBackButton = new JButton("Retour");
		getBackButton.setVisible(true);
		getBackButton.addActionListener(app);
		getBackButton.addActionListener(this);
		jpanelRetour.add(getBackButton);
		//Ajout des boutons démarer/stopper/Arrêter simulation
		startStopSimulationB = new JButton("Lancer la simulation");
		startStopSimulationB.setVisible(true);
		startStopSimulationB.addActionListener(this);
		jpanelVitesse.add(startStopSimulationB); //.setBounds(20,20,40,20);;
		jlabVitesse.setText("Vitesse du simulation :");
		jpanelVitesse.add(jlabVitesse);
		//Ajout du texte indiquant la date de la simulation
		simulationDateLabel = new JLabel("Date de la simulation: jour " + simulation.getSimulationDate(), SwingConstants.HORIZONTAL);
		jpanel.add(simulationDateLabel);
		
		//Affichage de l'arbre, ce qui permettra de sélectionner les catégories et produits qu'on souhaite afficher
		categoriesTree = new Arbre(app.getMagasin());
		jpanelArbre.add(categoriesTree.arbre);
		categoriesTree.arbre.addMouseListener(this);
		//Ajout du boutton permettant d'ajouter une catégorie dans le graphe
		addCategoryInGraphB = new JButton("Ajouter la catégorie");
		addCategoryInGraphB.addActionListener(this);
		addCategoryInGraphB.setEnabled(false);
		jpanel.add(addCategoryInGraphB);
		//Ajout du boutton permettant la suprresion d'une catégorie du graphe
		removeCategoryFromGraphB = new JButton("Retirer la catégorie");
		removeCategoryFromGraphB.addActionListener(this);
		jpanel.add(removeCategoryFromGraphB);
		//Ajout des boutons radios permettant de choisir entre le mode quantité et chiffre d'affaire
		JPanel radioPanel = new JPanel();
		radioGroup = new ButtonGroup();
		quantitiesButton = new JRadioButton("Quantités");
		quantitiesButton.setActionCommand("quantities");
		quantitiesButton.setSelected(true);
		quantitiesButton.addActionListener(this);
		benefitButton = new JRadioButton("Chiffre d'affaire");
		benefitButton.setActionCommand("benefit");
		benefitButton.addActionListener(this);
		radioGroup.add(quantitiesButton);
		radioGroup.add(benefitButton);
		radioPanel.add(quantitiesButton);
		radioPanel.add(benefitButton);
		jpanel.add(radioPanel);
		
		//Affichage des statistiques
		
		graphe = new HistoryGraph();
		graphe.setEndDate(app.getSimulationDate());
		graphe.setVisible(true);
		//Ancienne version 		this.add(graphe);
		graphe.setVisible(true);
		graphScroll = new JScrollPane(graphe);
		graphScroll.setBounds(300, 75,500, 420);
		graphScroll.setVisible(true);
		this.add(graphScroll);
		
		//Ajout du slider qui permettra le contrôle de la vitesse de simulation
		simulationSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 1,10,3);
		simulationSpeedSlider.addChangeListener(this);
		simulationSpeedSlider.setVisible(true);
		jpanelVitesse.add(simulationSpeedSlider);
		//Ajout du texte indiquant la valeur de la vitesse de simulations
		simulationSpeedLabel = new JLabel(simulationSpeedSlider.getValue()+" jours simulés par seconde", SwingConstants.HORIZONTAL);
		jpanelVitesse.add(simulationSpeedLabel);
	}
	
	/**
	 * Renvoie la référence du bouton de retour. Utilisé par la classe application qui est client de ce bouton.
	 * @return the getBackButton
	 */
	public JButton getGetBackButton(){
		return getBackButton;
	}
	
	/**
	 * Renvoie la référence du bouton 
	 * @return the startStopSimulationB
	 */
	public JButton getStartStopSimulationB() {
		return startStopSimulationB;
	}
	
	/**
	 *Fonction d'activation appelée lorsque l'utilisateur bascule sur l'interface "Stats Screen", via l'interface "Home Screen". Nécessaire
	 *pour gérer la simulation et la mise à jour du graphe, mais aussi mettre à jour l'arbre des catégories qui peut avoir été modifié via le
	 *structure manager.
	 */
	public void activate(){
		this.active = true;
		categoriesTree.updateTree();
		addCategoryInGraphB.setEnabled(false);
		removeCategoryFromGraphB.setEnabled(false);
		while(active){
			simulation.simulationStep();
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
	/**
	 * Cette méthode gère les différents clics sur les boutons de l'interface. Notamment:
	 * -lancement/arrêt de la simulation
	 * -ajout/retrait de la courbe d'une catégorie/d'un produit dans le graphe.
	 * -bouton retour (pour gérer les actions à effectuer lors du retour tel que la mise en pause de la simulation).
	 * @param e évènement de type ActionEvent ayant généré un appel à la méthode.
	 */
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
	/**
	 * gestion de l'évènement généré par le slider, pour gérer la modification de la vitesse de simulation.
	 * @param e évènement source ayant généré l'appel à la méthode
	 */
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == simulationSpeedSlider){
			simulation.setSimulationSpeed(simulationSpeedSlider.getValue());
			simulationSpeedLabel.setText(simulation.getSimulationSpeed()+" jours simulés par seconde.");
		}
	}

	@Override
	/**
	 * gestion du clic de souris. Notamment, gestion de l'activation des boutons d'ajouter et retrait d'une courbe d'une catégorie dans le graphe,
	 * selon qu'une catégorie est effectivement sélectionnée ou non.
	 * @param e évènement source
	 */
	public void mouseClicked(MouseEvent e) {
		if(categoriesTree.arbre.getLastSelectedPathComponent()!=null){
			addCategoryInGraphB.setEnabled(true);
			removeCategoryFromGraphB.setEnabled(true);
		}
		else{
			addCategoryInGraphB.setEnabled(false);
			removeCategoryFromGraphB.setEnabled(false);
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