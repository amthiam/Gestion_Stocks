package Application;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class DeleteCategoryWindow extends JFrame implements ActionListener{


        /**
         * @param args
         */
        
        StructureManagerWindow manager;
    Category cat;
    JButton okButton, cancelButton;
    JTextField nameTF;
    JLabel nameLabel;
        
        public DeleteCategoryWindow(StructureManagerWindow manager, Category cat){
                
        this.manager = manager;
        this.cat = cat;
                
                setTitle("Suppression d'une categorie");
                setSize(500, 150);
                setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);
                setVisible(true);
                
                JPanel panel = new JPanel();
        panel.setVisible(true);
        
        nameLabel = new JLabel("Etes-vous sur de vouloir supprimer cette categorie ? ");
        nameLabel.setVisible(true);
        panel.add(nameLabel);
        
       
        
        okButton = new JButton("Oui");
        okButton.setVisible(true);
        okButton.addActionListener(this);
        panel.add(okButton);
        
        cancelButton = new JButton("Annuler");
        cancelButton.setVisible(true);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);
        
        getContentPane().add(panel);
        
        
        }
        public static void main(String[] args) {
                // TODO Auto-generated method stub


        }
        @Override
        public void actionPerformed(ActionEvent e) {
                
                // TODO Auto-generated method stub
                if(e.getSource() == okButton){


                    try{
                                    /*
                                     * TODO
                                     * A completer
                                     * */
                                    Category parent = (manager.arbre.rootCategory.getParentCategory((Category)cat));
                                    parent.removeChild((Category)cat);
                            
                            
                            manager.updateTree();
                            dispose();
                    }
                    catch(NumberFormatException exception){
                                    //TODO
                                    // A modifier egalement
                            
                            JOptionPane.showMessageDialog(this, "Erreur: le prix et la quantité doivent être de type numérique.", "Erreur entrée utilisateur",
                                                                                    JOptionPane.ERROR_MESSAGE);
                    }
        }
                
        else if(e.getSource() == cancelButton){
            dispose();
        }
                
                
        }


}

