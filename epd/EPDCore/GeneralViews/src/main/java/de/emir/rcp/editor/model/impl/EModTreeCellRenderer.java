package de.emir.rcp.editor.model.impl;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import de.emir.rcp.editor.model.ILabelProvider;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.ui.utils.treetable.umodel.UNode;

public class EModTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4654323920505890844L;
	
	
	protected ILabelProvider mLabelProvider;
	
	public EModTreeCellRenderer(ILabelProvider lp) {
		mLabelProvider = lp;
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if (mLabelProvider != null && value instanceof UNode) {
			UNode node = (UNode)value;
			Pointer ptr = node.getPointer();
			if (ptr != null) {
				String labelP = mLabelProvider.getLabel(ptr);
				if (labelP != null)
					setText(labelP);
				Object obj = ptr.getValue();
				URL url = mLabelProvider.getIcon(obj);
				if (url != null) {
					ImageIcon icon = IconManager.getIcon(url, IconManager.preferedSmallIconSize());
					if (icon != null)
						setIcon(icon);
				}
				String toolTip = mLabelProvider.getTooltip(node.getPointer());
				if (toolTip != null)
					setToolTipText(toolTip);
			}else {
				System.out.println("Null Value");
			}
		}
		return c;
	}

}