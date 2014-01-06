package Application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;








public class ModifyProductWindow extends JFrame implements ActionListener{
        
                StructureManagerWindow manager;
                Product produit;
                JButton okButton, cancelButton;
                JTextField nameTF, priceTF, quantityTF;
                JLabel nameLabel, priceLabel, quantityLabel;


        public ModifyProductWindow(StructureManagerWindow manager, Product produit){
                        super();
                        //This window is used to modify a product in the category cat
                this.manager = manager;
                this.produit = produit;
                        
                setTitle("Modification du produit");
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
                
                nameLabel = new JLabel("Nom:");
                nameLabel.setVisible(true);
                
                panel.add(nameLabel);
                
                nameTF = new JTextField();
                nameTF.setPreferredSize(new Dimension(150,30));
                nameTF.setVisible(true);
                nameTF.setText(produit.getName());
                panel.add(nameTF);
                
                priceLabel = new JLabel("Prix:");
                priceLabel.setVisible(true);
                panel.add(priceLabel);
                
                priceTF = new JTextField();
                priceTF.setPreferredSize(new Dimension(150,30));
                priceTF.setVisible(true);
                priceTF.setText(String.valueOf(produit.getPrice()));
                
                panel.add(priceTF);
                
                quantityLabel = new JLabel("Quantite");
                quantityLabel.setVisible(true);
                panel.add(quantityLabel);
                
                quantityTF = new JTextField();
                quantityTF.setPreferredSize(new Dimension(150,30));
                quantityTF.setVisible(true);
                quantityTF.setText(String.valueOf(produit.getCurrentQuantity()));
                panel.add(quantityTF);
                
                
                
                okButton = new JButton("Enregistrer les modifs");
                okButton.setVisible(true);
                okButton.addActionListener(this);
                panel.add(okButton);
                
                cancelButton = new JButton("Annuler");
                cancelButton.setVisible(true);
                cancelButton.addActionListener(this);
                panel.add(cancelButton);
                
                getContentPane().add(panel);
        }


                @Override
                public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == okButton){
                                String name = nameTF.getText();
                                if(name.length()==0){
                                        JOptionPane.showMessageDialog(this, "Erreur: le nom du produit ne peut pas etre vide.", "Erreur entree utilisateur",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                                else{
                                        Double price, quantity;
                                        try{
                                                price = Double.parseDouble(priceTF.getText());
                                                quantity = Double.parseDouble(quantityTF.getText());
                                               
                                                produit.setName(name);
                                                produit.setPrice(price);
                                                produit.setCurrentQuantity(quantity);
                                                manager.updateTree();
                                                dispose();
                                        }
                                        catch(NumberFormatException exception){
                                                JOptionPane.showMessageDialog(this, "Erreur: le prix et la quantite doivent etre de type numerique.", "Erreur entree utilisateur",
                                                                                                        JOptionPane.ERROR_MESSAGE);
                                        }
                                }
                        }
                        else if(e.getSource() == cancelButton){
                                dispose();
                        }
        }
}

