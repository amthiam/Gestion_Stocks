import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class AddProductWindow extends JFrame implements ActionListener{
		StructureManagerWindow manager;
		Category cat;
		JButton okButton, cancelButton;
		JTextField nameTF, priceTF, quantityTF;
		JLabel nameLabel, priceLabel, quantityLabel;

        public AddProductWindow(StructureManagerWindow manager, Category cat){
        		super();
        		//This window is used to add a product in the category cat
                this.manager = manager;
                this.cat = cat;
        		
                setTitle("Ajout d'un nouveau Produit");
                setSize(500, 130);
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setVisible(true);
                setAlwaysOnTop(true);
                
                JPanel panel = new JPanel();
                panel.setVisible(true);
                
                nameLabel = new JLabel("Nom:");
                nameLabel.setVisible(true);
                panel.add(nameLabel);
                
                nameTF = new JTextField();
                nameTF.setPreferredSize(new Dimension(150,30));
                nameTF.setVisible(true);
                panel.add(nameTF);
                
                priceLabel = new JLabel("Prix:");
                priceLabel.setVisible(true);
                panel.add(priceLabel);
                
                priceTF = new JTextField();
                priceTF.setPreferredSize(new Dimension(150,30));
                priceTF.setVisible(true);
                panel.add(priceTF);
                
                quantityLabel = new JLabel("Quantité");
                quantityLabel.setVisible(true);
                panel.add(quantityLabel);
                
                quantityTF = new JTextField();
                quantityTF.setPreferredSize(new Dimension(150,30));
                quantityTF.setVisible(true);
                panel.add(quantityTF);
                
                
                
                okButton = new JButton("Ajouter le produit");
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
					JOptionPane.showMessageDialog(this, "Erreur: le nom du produit ne peut pas être vide.", "Erreur entrée utilisateur",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					Double price, quantity;
					try{
						price = Double.parseDouble(priceTF.getText());
						quantity = Double.parseDouble(quantityTF.getText());
						manager.addProduct(cat, new Product(name, price, quantity));
						manager.updateTree();
						dispose();
					}
					catch(NumberFormatException exception){
						JOptionPane.showMessageDialog(this, "Erreur: le prix et la quantité doivent être de type numérique.", "Erreur entrée utilisateur",
													JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if(e.getSource() == cancelButton){
				dispose();
			}
		}
}

