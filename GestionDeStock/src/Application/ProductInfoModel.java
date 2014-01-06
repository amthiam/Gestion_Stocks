package Application;
import javax.swing.table.AbstractTableModel;


public class ProductInfoModel extends AbstractTableModel {
	protected Object[] productData;
	protected String[] titles;
	
	public ProductInfoModel(String[] titles){
		this.titles = titles;
	}
	
	public void setProductData(Object[] pData){
		productData = pData;
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return productData[arg1];
	}
	
	public String getColumnName(int col){ 
		return titles[col]; 
	}

}
