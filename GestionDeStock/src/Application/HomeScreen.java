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
	
	
	public HomeScreen(Application app){
		this.app = app;
		active = false;
		//Creation des trois bouttons de lecran d'accueil
		buttonStructureManager = new JButton("Gestionnaire de categories et produits");
		buttonStructureManager.setVisible(true);
		buttonStructureManager.addActionListener(app);
		this.add(buttonStructureManager);
		
		buttonStats = new JButton("Statistiques");
		buttonStats.setVisible(true);
		buttonStats.addActionListener(app);
		this.add(buttonStats);
		
		loadB = new JButton("Charger les donnees");
		loadB.setVisible(true);
		loadB.addActionListener(this);
		this.add(loadB);
		
		saveB = new JButton("Sauvegarder les donnees");
		saveB.setVisible(true);
		saveB.addActionListener(this);
		this.add(saveB);
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
