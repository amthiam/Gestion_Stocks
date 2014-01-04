package Entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class IMetierCategoriesImpl implements IMetierCategories{

	@Override
	public void addProduit(Categories p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categories> getProduitParMotCle(String mc) {
		List<Categories> lesproduits=new ArrayList<Categories>();
		Connection con=SingletonConnection.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from categories");
			
			ResultSet r=ps.executeQuery();
			while(r.next())
			{
				Categories p=new Categories();
				p.setIdCategories((r.getInt("idCategories")));
				p.setNom(r.getString("nom"));
				p.setPrix(r.getDouble("prix"));
				p.setBd(r.getInt("BD"));
				p.setBg(r.getInt("BG"));
				p.setQuantite(r.getInt("Quantite"));
				lesproduits.add(p);
			}
		
			 
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesproduits;
	}

	public void insetionProduit(Categories c){
		Connection con=SingletonConnection.getConnection();
		try {
			 Statement st1 = (Statement) con.createStatement();
		        st1.execute("INSERT into categories VALUES("+c.getIdCategories()+",'"+c.getNom()+"',"+c.getPrix()+","+c.getQuantite()+","+c.getBg()+","+c.getBd()+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprimerProduits(){
		Connection con=SingletonConnection.getConnection();
		try {
			 Statement st1 = (Statement) con.createStatement();
		        st1.execute("delete  from categories ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
