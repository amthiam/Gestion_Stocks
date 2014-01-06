package Application;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class ChooseCategory extends JTree{
	Category rootCategory;
	DefaultTreeModel treeModel;
	
	public ChooseCategory(Category root){
		this.rootCategory = root;
		DefaultMutableTreeNode rootNode = buildTreeModel(rootCategory);
		treeModel = new DefaultTreeModel(rootNode);
	}
	
	public DefaultMutableTreeNode buildTreeModel(Category rootCat){
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(rootCat.getName());
		for(Category c:rootCat.getChildren()){
			node.add(buildTreeModel(c));
		}
		return node;
	}
}
