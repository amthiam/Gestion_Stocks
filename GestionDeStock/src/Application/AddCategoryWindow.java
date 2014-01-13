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


public class AddCategoryWindow extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	
    StructureManagerWindow manager;
    Category cat;
    JButton okButton, cancelButton;
    JTextField nameTF;
    JLabel nameLabel;
    
    
	public AddCategoryWindow(StructureManagerWindow manager, Category cat){
		super();
		this.manager = manager;
        this.cat = cat;
	    
        
        
		setTitle("Ajout d'une nouvelle Categorie");
		setSize(500, 200);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		
		
		GridLayout grid = new GridLayout();
	        grid.setColumns(3);
	        grid.setRows(2);
	        grid.setHgap(5);
	        grid.setVgap(5);
         
		JPanel panel = new JPanel();
	        panel.setVisible(true);
	        panel.setLayout(grid);
	
	        nameLabel = new JLabel("Nom:");
	        nameLabel.setVisible(true);
	        panel.add(nameLabel);
	        
	        nameTF = new JTextField();
	        nameTF.setPreferredSize(new Dimension(150,30));
	        nameTF.setVisible(true);
	        panel.add(nameTF);
	        
	        okButton = new JButton("Ajouter la categorie");
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
	                        JOptionPane.showMessageDialog(this, "Erreur: le nom de la cat�gorie ne peut pas �tre vide.", "Erreur entr�e utilisateur",
	                                        JOptionPane.ERROR_MESSAGE);
	                }
	                else{
	                        try{
	                                manager.addCategory(cat, new Category(name));
	                                manager.updateTree();
	                                dispose();
	                        }
	                        catch(NumberFormatException exception){
	                                JOptionPane.showMessageDialog(this, "Erreur: le prix et la quantit� doivent �tre de type num�rique.", "Erreur entr�e utilisateur",
	                                                                                        JOptionPane.ERROR_MESSAGE);
	                        }
	                }
	        }
	        else if(e.getSource() == cancelButton){
	                dispose();
	        }
	}
	
}
