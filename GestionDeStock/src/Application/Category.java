package Application;
import java.util.LinkedList;
import java.util.List;


/**
* Classe représentant une catégorie et en même temps l'arbre de ses catégorie descendantes.
*
*/
public class Category {
        String name;
        List<Category> children;
        
        /**
         * Constructeur
         * @param n nom de la catégorie
         */
        public Category(String n){
                this.name = n;
                children = new LinkedList<Category>();
        }
        
        /**
         * Méthode permetant l'ajout d'une catégorie fille
         * @param c catégorie à ajouter
         */
        public void addChild(Category c){
                children.add(c);
        }
        
        /**
         * Méthode retirant la catégorie passée en paramètre de la liste des enfants.
         * @param cat catégorie à retirer de la liste des catégories filles
         */
        public void removeChild(Category cat){
                if(children.contains(cat)){
                        children.remove(cat);
                }
        }
        
        
        public String[] getLineInformation(){
        	return null;
        }
                
        public List<Product> getProducts(){
            LinkedList<Product> res = new LinkedList<Product>();
            if(!(this instanceof Product)){
                for(Category c:this.getChildren()){
                    res.addAll(c.getProducts());
                }
            }
            else{
                res.add((Product)this);
                
            }
            return res;
        }
        
        /**
         * Retourne la catégorie mère d'une catégorie passée en paramètre, en cherchant dans les descendants de la catégorie appelante.
         * @param cat la catégorie dont on cherche la catégorie mère
         * @return null si cat n'a pas été trouvée, renvoie la catégorie mère si cat a été trouvée.
         */
        public Category getParentCategory(Category cat){
                //cat est la catégorie dont le parent est recherché
                Category result;
                for(Category c:getChildren()){
                        if(c == cat){
                                return this; //Si cat est enfant direct on retourne cat
                        }
                        else{ //Sinon on cherche dans cet enfant
                                result = c.getParentCategory(cat); //On cherche cat dans c
                                if (result != null){ //La méthode renvoie null seulement si la catégorie n'a pas été trouvée.
                                        return result;
                                }
                        }
                }
                return null; //On renvoie null si la catégorie cat n'a pas été trouvée dans l'arbre défini par cat2
        }
        
        /**
         * Vérifie que la structure de l'arbre est correcte.
         * @return true si toutes les feuilles de l'arbre sont de type Product
         */
        public boolean checkCategoryStructure(){
                if(children.size() == 0){
                        if(this instanceof Product){
                                return true;
                        }
                        else{
                                return false;
                        }
                }
                else{
                        if(this instanceof Product){
                                return false;
                        }
                        else{
                                for(Category cat:getChildren()){
                                        if(!cat.checkCategoryStructure()){
                                                return false;
                                        }
                                }
                        }
                }
                return true;
        }
        
        /**
         * Renvoie la liste des descendants directs de la cattégorie appelante
         * @return la liste des descendants directs de la catégoorie appelante
         */
        public List<Category> getChildren(){
                return this.children;
        }
        
        /**
         * Définit le nom de la catégorie
         * @param n le nouveau nom de la catégorie
         */
        public void setName(String n){
                this.name = n;
        }
        
        /**
         * Renvoie le nom de la catégorie
         * @return le nom de la catégorie
         */
        public String getName(){
                return this.name;
        }
        
        /**
         * La méthode toString renvoie le nom de la catégorie (utile pour l'affichage de l'arbre)
         */
        public String toString(){
                return this.name;
        }
        
}