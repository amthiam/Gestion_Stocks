package Application;
import Entity.Categories;
import Entity.IMetierCategoriesImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class HomeScreen extends Panel implements ActionListener{
	Application app;
	boolean active;
	JButton buttonStructureManager;
	JButton buttonStats;
	JButton loadB, saveB;
	JLabel titre=new  JLabel("Logiciel de gestion \n de stock",JLabel.CENTER);
	Font font = new Font("Gabriola",Font.BOLD,60);
	JPanel panel=new JPanel();
	ImageIcon image1 = new ImageIcon( new ImageIcon( "image.png").getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT));
	JLabel image=new JLabel(image1);
	//ImageIcon icon1 = new ImageIcon(new ImageIcon("C://Users//Imen//Desktop//imageStock.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	//JLabel icon= new JLabel(icon1);
	public HomeScreen(Application app){
		this.app = app;
		active = false;
		this.setLayout(null);
		this.setBackground(Color.white);
		
		titre.setBounds(150, 30, 800, 70);
		titre.setFont(font);
		this.add(titre);
		
		
		//Creation des trois bouttons de lecran d'accueil
		JLabel lab=new JLabel("Gérer les categories et les produits");
		buttonStructureManager = new JButton();
		buttonStructureManager.setVisible(true);
		buttonStructureManager.addActionListener(app);
		buttonStructureManager.add(lab);
		//buttonStructureManager.add(icon);
		//this.add(buttonStructureManager);
		
		buttonStats = new JButton("Statistiques");
		buttonStats.setVisible(true);
		buttonStats.addActionListener(app);
		//this.add(buttonStats);
		
		loadB = new JButton("Charger les donnees");
		loadB.setVisible(true);
		loadB.addActionListener(this);
		//this.add(loadB);
		
		saveB = new JButton("Sauvegarder les donnees");
		saveB.setVisible(true);
		saveB.addActionListener(this);
		//this.add(saveB);
		
		
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),"choisir l'action à effectuer"));
		panel.add(buttonStructureManager);
		panel.add(buttonStats);
		panel.add(loadB);
		panel.add(saveB);
		panel.setBounds(150,500, 800, 75);
		
		image.setBounds(150, 125, 800, 400);
		this.add(image);
		this.add(panel);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		IMetierCategoriesImpl metier=new IMetierCategoriesImpl();
		if(e.getSource()==loadB){
			
                   Charge_Sauvegarde cs = new Charge_Sauvegarde();
          int[] IN=new int[1];
            List <Categories> produits=metier.getProduitParMotCle("asus") ;
          
            if(produits.size()!=0){
            	 System.out.println(produits.size());
                Category res = cs.conversion2(produits,1,IN);
                
               
                if(res.checkCategoryStructure()){
                    app.setMagasin(res);
                    buttonStats.setEnabled(true);
                }
            }
            else{
                System.out.println("la base est vide");
                
            }
		}
		else if(e.getSource()==saveB){
                     List <Categories> produits=metier.getProduitParMotCle("asus") ;
                     if(produits !=null){
                     for(int i=1;i<produits.size()+1;i++){
                        
                            metier.supprimerProduits();
                      
                         
                     }
                     }
                    Category categoryToSave = app.getMagasin();
                    Charge_Sauvegarde cs=new Charge_Sauvegarde();
                    int[] k=new int[1];
                    List<Categories> l=cs.conversion1(categoryToSave, 1, k);
                    for(Categories c:l){
                        
                         metier.insetionProduit(c);
                    }
                    Charge_Sauvegarde.id=0;
	                  
                       
                        
                              
			//Ici il faut appeler conversion 1 avec comme argument categoryToSave et ensuite utiliser ta fonction create dont
			//tu m'as parle
		}
		
	}
}
