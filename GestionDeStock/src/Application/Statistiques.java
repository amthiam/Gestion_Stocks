package Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Statistiques extends Panel{

	private JLabel titre=new  JLabel("Statistiques",JLabel.CENTER);
	JLabel labelInfo = new JLabel("veuillez choisir le critère de tri");
	JLabel labelOrdre = new JLabel("veuillez choisir l'ordre");
	Font font = new Font("Arial",Font.BOLD,20);
	JButton simulationButton=new JButton();
	JButton RetourButton=new JButton("Retour");
	 String[] entetes = {"Id_Article", "Libellé", "Catégorie", "Prix unitaire", "nombre d'articles en stock","nombre d'articles vendus","chiffre d'affaires"};
	 Object[][] donnees={{"test ", "test"},
             {"test	", " test"},
             {"test", "test"},
             {"test", "test"},
             {"test", "test"},
             {"test", "test"},
             {"test", "test"},};
	 JTable tableau = new JTable(donnees, entetes);
	 JPanel panelTableau=new JPanel();
	 JComboBox combo = new JComboBox();
	 JRadioButton yesButton   = new JRadioButton("croissant"  , true);
	 JRadioButton noButton    = new JRadioButton("décroissant"   , false);

	 ButtonGroup bgroup = new ButtonGroup();
	
	public Statistiques(Application app){
		this.setLayout(null);
		titre.setFont(font);
		titre.setForeground(Color.blue);
		titre.setBounds(400, 00, 300, 100);;
		combo.addItem("prix");
		combo.addItem("quantité vendu");
		combo.addItem("nombre d'articles en stock");
		
		simulationButton.setText("mode simulation");
		simulationButton.setBounds(400, 500, 150, 20);
		RetourButton.setBounds(200, 500, 150, 20);
		
		tableau.getTableHeader().setBackground(Color.magenta);
		panelTableau.setBounds(20, 200, 600, 300);
		labelInfo.setBounds(50, 130, 200, 30);
		labelOrdre.setBounds(50,155,200,30);
		combo.setBounds(300, 125, 200, 20);
		panelTableau.add(tableau.getTableHeader(),BorderLayout.NORTH);
		panelTableau.add(tableau, BorderLayout.CENTER);
		
		 bgroup.add(yesButton);
		 bgroup.add(noButton);
		yesButton.setBounds(300, 155, 100, 20);
		noButton.setBounds(410, 155, 100, 20);
		
		this.add(panelTableau);
		this.add(simulationButton);
		this.add(titre);
		this.add(RetourButton);
		this.add(labelInfo);
		this.add(combo);
		this.add(labelOrdre);
		this.add(yesButton);
		this.add(noButton);
	}
	
}
