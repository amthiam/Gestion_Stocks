import javax.swing.JFrame;




public class AddProductWindow extends JFrame {


        public AddProductWindow(){
                
                JFrame addProductWindow = new JFrame();
                addProductWindow.setTitle("Ajout d'un nouveau Produit");
                addProductWindow.setSize(500, 500);
                addProductWindow.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
                addProductWindow.setLocationRelativeTo(null);
                addProductWindow.setResizable(false);
                addProductWindow.setVisible(true);
                
                /*
                 * a completer avec
                 *                 - Zones de textes
                 *                                 Zone de texte pour le nom du nouveau produit
                 *                                 Noeud Parent a renseigner
                 *                 - Boutton Valider
                 */
        }
        
        
        /**
         * @param args
         */
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                AddProductWindow product = new AddProductWindow();
                
                
        }


}

