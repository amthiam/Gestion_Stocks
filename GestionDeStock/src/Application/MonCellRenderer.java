package Application;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * 
 * @author Imen
 * classe pour spécifier l'affichage d'une ligne du tableau
 */


public class MonCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable tableau, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(tableau, value,
				isSelected, hasFocus, row, column);

		if (row == MaxRow(2, tableau) )
			cell.setBackground(Color.BLUE);
		else
			cell.setBackground(Color.WHITE);
		return cell;
	}
	
	
	int   MaxRow( int column, JTable tableau){
		double max=0;
		int a=0;
		for(int i=0; i<tableau.getRowCount();i++){
		if(Double.parseDouble(tableau.getValueAt(i, column).toString())>max){
			max=Double.parseDouble(tableau.getValueAt(i, column).toString());
			a=i;
		}
		
		
		
	}

	return a;
	}
	
	
}