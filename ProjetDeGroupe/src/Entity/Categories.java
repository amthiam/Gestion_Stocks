package Entity;

import java.io.Serializable;

public class Categories implements Serializable {
	 private static final long serialVersionUID = 1L;
	  
	
	    private Integer idCategories;
	 
	    private String nom;
	  
	    private Double prix;

	    private Integer quantite;
	   
	    private Integer bd;
	    
	    private Integer bg;
	    public Categories() {
	    }

	    public Categories(Integer idCategories) {
	        this.idCategories = idCategories;
	    }
	 public Categories(Integer id,String c,Double prix,double q,Integer bg,Integer bd) {
	         
	        this.nom=c;
	        this.bg=bg;
	        this.bd=bd;
	        this.prix=prix;
	        this.quantite=(int)q;
	        this.idCategories=id;
	        
	    }
	    public Categories(Integer id,String c,Integer i,Integer j) {
	       
	        this.idCategories=id;
	        this.nom=c;
	        this.bg=i;
	        this.bd=j;
	        
	    }
	 
	    public Integer getIdCategories() {
	        return idCategories;
	    }

	    public void setIdCategories(Integer idCategories) {
	        this.idCategories = idCategories;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public Double getPrix() {
	        return prix;
	    }

	    public void setPrix(Double prix) {
	        this.prix = prix;
	    }

	    public Integer getQuantite() {
	        return quantite;
	    }

	    public void setQuantite(Integer quantite) {
	        this.quantite = quantite;
	    }

	    public Integer getBd() {
	        return bd;
	    }

	    public void setBd(Integer bd) {
	        this.bd = bd;
	    }

	    public Integer getBg() {
	        return bg;
	    }

	    public void setBg(Integer bg) {
	        this.bg = bg;
	    }

}
