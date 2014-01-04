/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
package Application;
import Entity.Categories;
import Entity.IMetierCategoriesImpl;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.spi.DirStateFactory;
public class Charge_Sauvegarde {
      static Integer id=0;
   public static List<Categories> conversion1(Category c, int i, int[] j){
       
    	//Fonction qui doit initialement être appelée avec i=1
    	int BG=i;
        List<Categories> l2=new ArrayList<Categories>();
        for(Category cat:c.getChildren()){
            l2.addAll(conversion1(cat, i+1, j));
            i=j[0];
        }
        j[0]=i+1;
        if(!(c instanceof Product)){
        	//c n'est pas un produit
            id++;
        	l2.add(new Categories(id,c.getName(),BG,j[0]));
        }
        else{
        	Product p = (Product)c;
        	double price = p.getPrice();
        	double q = p.getCurrentQuantity();
        	//p est un produit, il faut faire pareil que ci-dessus mais en appelant le constructeur de Categories qui donne un prix et une quantité.
                id++;
        	l2.add(new Categories(id,c.getName(), price, q, BG, j[0]));//A changer selon ton constructeur
        }
        
        return l2;
    }
    
    public Category conversion2(List<Categories> l2,int i, int[] k){
        String name;
        int BD=-1;
        Category result=null;
        for(Categories cat2:l2){
            if(cat2.getBg()==i){
                name=cat2.getNom();
                System.out.println(name);
                BD=cat2.getBd();
                if(BD-i>1){
                    result=new Category(name);
                }
                else{
                    double price=cat2.getPrix();
                    int q = cat2.getQuantite();
                    
                    result=new Product(name,price,(double)q);
                }
            }
            
        }
        
        
            int j=i+1;

            while(j<BD){
                System.out.println(j + "," + BD);
                result.addChild(conversion2(l2,j,k));
                System.out.println("k vaut "+ k[0]);
                j=k[0]+1;
            }
        k[0]=BD;
        System.out.println("---"+k[0]);
        return result;
    }
     public static void main(String[] args) {
      //test unitaire
    	 IMetierCategoriesImpl metier=new IMetierCategoriesImpl();
         List <Categories> produits=metier.getProduitParMotCle("asus") ;
         Charge_Sauvegarde ch=new Charge_Sauvegarde();
         int []IN= new int[1];
         if(produits!=null){ 
            System.out.println(ch.conversion2(produits, 1, IN).getName());
         }
         else{
             System.out.println("pas de produits");
         }
         
         
         Categories cat=new Categories();
       cat.setNom("lait");
       cat.setIdCategories(4);
       cat.setPrix(22.0);
       cat.setBd(4);
       cat.setBg(5);
       //cat.setQuantite(20);
       metier.insetionProduit(cat);
       
            
        
      
         }
        
     
     
        
            
    
    
    }
    

