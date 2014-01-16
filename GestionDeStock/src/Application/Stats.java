package Application;
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
	JButton getBackButton, startStopSimulationB, endSimulationB, addCategoryInGraphB, removeCategoryFromGraphB,vente,plusB,moinsB;
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
	JPanel jpanelEchelle = new JPanel();
       // JPanel jpanelVente= new JPanel();
        JLabel titre=new  JLabel("Simulation de Ventes",JLabel.CENTER);
        Font font = new Font("Gabriola",Font.BOLD,50);
	
	/**
	 * Constructeur. Construit les boutons, l'arbre de type Arbre, le graphe de type HistoryGraph, et plus g�n�ralement l'interface graphique.
	 * D�finit les listeners des diff�rents objets de l'interface.
	 * @param app Objet Application appelant
	 */
	public Stats(Application app){
		this.setLayout(null);
		this.setBackground(Color.white);
		active = false;
		this.app = app;
		jpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"test"));
		jpanelVitesse.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Cont�ler la simulation"));
		jpanelVitesse.setBounds(400, 525, 600,75);
		jpanel.setBackground(Color.white);
		jpanelArbre.setBackground(Color.white);
		jpanelVitesse.setBackground(Color.white);
		jpanelRetour.setBackground(Color.white);
		jpanelRetour.setBounds(20,550,250,50);
               // jpanelVente.setBounds(-30, 500, 350, 150);
                
		jpanel.setBounds(20,400,250,125);
		jpanelArbre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"L'arbre du produits "));
		jpanelArbre.setBounds(20,100,250, 300);
		jpanelEchelle.setBackground(Color.white);
		jpanelEchelle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Echelle "));
		jpanelEchelle.setBounds(1025,240,80,100);
		this.add(jpanelArbre);
		this.add(jpanelVitesse);
		this.add(jpanel);
		this.add(jpanelRetour);
		this.add(jpanelEchelle);
               // this.add(jpanelVente);
		simulation = app.getSim();
		//Ajout du boutton de retour
		getBackButton = new JButton("Retour");
		getBackButton.setVisible(true);
		getBackButton.addActionListener(app);
		getBackButton.addActionListener(this);
		jpanelRetour.add(getBackButton);
		//Ajout des boutons d�marer/stopper/Arr�ter simulation
		startStopSimulationB = new JButton("Lancer la simulation");
		startStopSimulationB.setVisible(true);
		startStopSimulationB.addActionListener(this);
		jpanelVitesse.add(startStopSimulationB); //.setBounds(20,20,40,20);;
		jlabVitesse.setText("Vitesse du simulation :");
		jpanelVitesse.add(jlabVitesse);
		//Ajout du texte indiquant la date de la simulation
		simulationDateLabel = new JLabel("Date de la simulation: jour " + simulation.getSimulationDate(), SwingConstants.HORIZONTAL);
		jpanel.add(simulationDateLabel);
		
		//Affichage de l'arbre, ce qui permettra de s�lectionner les cat�gories et produits qu'on souhaite afficher
		categoriesTree = new Arbre(app.getMagasin());
		jpanelArbre.add(categoriesTree.arbre);
		categoriesTree.arbre.addMouseListener(this);
		//Ajout du boutton permettant d'ajouter une cat�gorie dans le graphe
		addCategoryInGraphB = new JButton("Ajouter la cat�gorie");
		addCategoryInGraphB.addActionListener(this);
		addCategoryInGraphB.setEnabled(false);
		jpanel.add(addCategoryInGraphB);
		//Ajout du boutton permettant la suprresion d'une cat�gorie du graphe
		
                removeCategoryFromGraphB = new JButton("Retirer la cat�gorie");
		removeCategoryFromGraphB.addActionListener(this);
		jpanel.add(removeCategoryFromGraphB);
                
                 //Ajout du bouton permettant la réalisation d'une vente
                vente = new JButton("Realiser vente");
		vente.addActionListener(this);
                vente.setEnabled(false);
                vente.addActionListener(this);
		
		//vente.add(addCategoryInGraphB);
                jpanelRetour.add(vente);
        //ajout de titre 
                titre.setFont(font);
                this.add(titre);
                titre.setBounds(100	, 10, 800, 100);
                
		//Ajout des boutons radios permettant de choisir entre le mode quantit� et chiffre d'affaire
		JPanel radioPanel = new JPanel();
		radioGroup = new ButtonGroup();
		quantitiesButton = new JRadioButton("Quantit�s");
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
		
		  // Ajout du bouton plusB qui permet de reguler l'echelle
                plusB= new JButton("+");
                plusB.addActionListener(this);
                plusB.setEnabled(true);
                plusB.setVisible(true);
                jpanelEchelle.add(plusB);
                
                // Ajout du bouton moinsB qui permet de diminuser l'echelle
                moinsB=new JButton("-");
                moinsB.addActionListener(this);
                moinsB.setEnabled(true);
                moinsB.setVisible(true);
                jpanelEchelle.add(moinsB);
		//jpanel.add(radioPanel);
	
		
		//Affichage des statistiques
		
		graphe = new HistoryGraph();
		graphe.setEndDate(app.getSimulationDate());
		graphe.setVisible(true);
		//Ancienne version 		this.add(graphe);
		graphe.setVisible(true);
		graphScroll = new JScrollPane(graphe);
		graphScroll.setBounds(300, 100,700, 420);
		graphScroll.setVisible(true);
		this.add(graphScroll);
		
		//Ajout du slider qui permettra le contr�le de la vitesse de simulation
		simulationSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 1,10,3);
		simulationSpeedSlider.addChangeListener(this);
		simulationSpeedSlider.setVisible(true);
		jpanelVitesse.add(simulationSpeedSlider);
		//Ajout du texte indiquant la valeur de la vitesse de simulations
		simulationSpeedLabel = new JLabel(simulationSpeedSlider.getValue()+" jours simul�s par seconde", SwingConstants.HORIZONTAL);
		jpanelVitesse.add(simulationSpeedLabel);
	}
	
	/**
	 * Renvoie la r�f�rence du bouton de retour. Utilis� par la classe application qui est client de ce bouton.
	 * @return the getBackButton
	 */
	public JButton getGetBackButton(){
		return getBackButton;
	}
	
	/**
	 * Renvoie la r�f�rence du bouton 
	 * @return the startStopSimulationB
	 */
	public JButton getStartStopSimulationB() {
		return startStopSimulationB;
	}
	
	/**
	 *Fonction d'activation appel�e lorsque l'utilisateur bascule sur l'interface "Stats Screen", via l'interface "Home Screen". N�cessaire
	 *pour g�rer la simulation et la mise � jour du graphe, mais aussi mettre � jour l'arbre des cat�gories qui peut avoir �t� modifi� via le
	 *structure manager.
	 */
	public void activate(){
		this.active = true;
                categoriesTree.setRootCategory(app.getMagasin());
		categoriesTree.updateTree();
		addCategoryInGraphB.setEnabled(false);
		removeCategoryFromGraphB.setEnabled(false);
                app.getSim().setListOfProducts(app.getMagasin().getProducts());
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
	 * Cette m�thode g�re les diff�rents clics sur les boutons de l'interface. Notamment:
	 * -lancement/arr�t de la simulation
	 * -ajout/retrait de la courbe d'une cat�gorie/d'un produit dans le graphe.
	 * -bouton retour (pour g�rer les actions � effectuer lors du retour tel que la mise en pause de la simulation).
	 * @param e �v�nement de type ActionEvent ayant g�n�r� un appel � la m�thode.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getGetBackButton()){
			this.active = false;
			simulation.stopSimulation();	//On arr�t la simulation lorsqu'on sort du mode
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
                else if(e.getSource() == vente){
                   	Product selectedProduit = (Product)(((DefaultMutableTreeNode) categoriesTree.arbre.getLastSelectedPathComponent()).getUserObject());
                       new Vendre(selectedProduit);   
                }
                 else if(e.getSource() == plusB){
                	graphe.setEchelle(graphe.getEchelle()+100);
                }
				
                else if(e.getSource() == moinsB){
                	if (graphe.getEchelle()>100)
                	{
                		graphe.setEchelle(graphe.getEchelle()-100);
                	}
                }
	}

	@Override
	/**
	 * gestion de l'�v�nement g�n�r� par le slider, pour g�rer la modification de la vitesse de simulation.
	 * @param e �v�nement source ayant g�n�r� l'appel � la m�thode
	 */
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == simulationSpeedSlider){
			simulation.setSimulationSpeed(simulationSpeedSlider.getValue());
			simulationSpeedLabel.setText(simulation.getSimulationSpeed()+" jours simul�s par seconde.");
		}
	}

	@Override
	/**
	 * gestion du clic de souris. Notamment, gestion de l'activation des boutons d'ajouter et retrait d'une courbe d'une cat�gorie dans le graphe,
	 * selon qu'une cat�gorie est effectivement s�lectionn�e ou non.
	 * @param e �v�nement source
	 */
	public void mouseClicked(MouseEvent e) {
		if(categoriesTree.arbre.getLastSelectedPathComponent()!=null){
			addCategoryInGraphB.setEnabled(true);
			removeCategoryFromGraphB.setEnabled(true);
                        vente.setEnabled(true);
		}
		else{
			addCategoryInGraphB.setEnabled(false);
			removeCategoryFromGraphB.setEnabled(false);
                        vente.setEnabled(false);
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
