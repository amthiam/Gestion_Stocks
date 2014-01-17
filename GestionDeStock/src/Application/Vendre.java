package Application;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amadou
 */
public class Vendre extends JFrame implements ActionListener{
    Product produit;
    JLabel QuantiteLabel;
    JTextField nameTF;
    JButton okButton, cancelButton;
    
    public Vendre(Product c){
        super();
        produit=c;
        
        
        setTitle("Vente du produit");
                setSize(500, 130);
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setVisible(true);
                setAlwaysOnTop(true);
                
                JPanel panel = new JPanel();
                panel.setVisible(true);
                
                GridLayout grid = new GridLayout();
                grid.setColumns(2);
                grid.setRows(4);
                grid.setHgap(5);
                grid.setVgap(5);


                panel.setLayout(grid);
                
                QuantiteLabel = new JLabel("Quantite à vendre");
                QuantiteLabel.setVisible(true);
                
                panel.add(QuantiteLabel);
                
                nameTF = new JTextField();
                nameTF.setPreferredSize(new Dimension(150,30));
                nameTF.setVisible(true);
                panel.add(nameTF);
                
                okButton = new JButton("Confirmer");
                okButton.setVisible(true);
                okButton.addActionListener(this);
                panel.add(okButton);
                
                cancelButton = new JButton("Annuler");
                cancelButton.setVisible(true);
                cancelButton.addActionListener(this);
                panel.add(cancelButton);
                
                getContentPane().add(panel);
    }
    
    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == okButton){
                                 double quantite = Double.parseDouble(nameTF.getText());
                                if(quantite>produit.getCurrentQuantity()){
                                        JOptionPane.showMessageDialog(this, "Erreur: la quantite selectionnee est inferieure a la quantite disponible", "Erreur entree utilisateur",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                                else{
  
                                        try{
                                                
                                                produit.setCurrentQuantity(produit.getCurrentQuantity() - quantite);
                                                produit.setSoldQ(produit.getSoldQ() + quantite);
             
                                                dispose();
                                        }
                                        catch(NumberFormatException exception){
                                                JOptionPane.showMessageDialog(this, "Erreur: la quantite à vendre doit etre de type numerique.", "Erreur entree utilisateur",
                                                                                                        JOptionPane.ERROR_MESSAGE);
                                        }
                                }
                        }
                        else if(e.getSource() == cancelButton){
                                dispose();
                        }
        }
}
