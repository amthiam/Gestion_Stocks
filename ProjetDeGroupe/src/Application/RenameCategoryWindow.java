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




public class RenameCategoryWindow extends JFrame implements ActionListener{


        /**
         * @param args
         */
        StructureManagerWindow manager;
    Category cat;
    JButton okButton, cancelButton;
    JTextField nameTF;
    JLabel nameLabel;
    
        public RenameCategoryWindow(StructureManagerWindow manager, Category cat){
                
                super();
                this.setTitle("Renommer une catégorie");
                this.setSize(500, 80);
                this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setVisible(true);
                
                
                this.manager = manager;
        this.cat = cat;
        
        JPanel panel = new JPanel();
        panel.setVisible(true);
        
        GridLayout grid = new GridLayout();
        grid.setColumns(2);
        grid.setRows(2);
        grid.setHgap(5);
        grid.setVgap(5);


        panel.setLayout(grid);


        
        // *************** Création des différents champs ***************** 
        nameLabel = new JLabel("Nom:");
        nameLabel.setVisible(true);
        panel.add(nameLabel);
        
        nameTF = new JTextField();
        nameTF.setPreferredSize(new Dimension(150,30));
        nameTF.setVisible(true);
        panel.add(nameTF);
        
        okButton = new JButton("Renommer");
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
                String name = nameTF.getText();
                if(name.length()==0){
                        JOptionPane.showMessageDialog(this, "Erreur: le nom du produit ne peut pas être vide.", "Erreur entrée utilisateur",
                                        JOptionPane.ERROR_MESSAGE);
                }
                else{
                       
                        try{


                                        cat.setName(name);
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

