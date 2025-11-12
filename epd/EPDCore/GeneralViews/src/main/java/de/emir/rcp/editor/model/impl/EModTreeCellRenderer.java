package de.emir.rcp.editor.model.impl;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.emir.rcp.editor.model.ILabelProvider;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.ui.utils.treetable.umodel.UNode;

public class EModTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
	private static final long serialVersionUID = -4654323920505890844L;
	protected ILabelProvider mLabelProvider;
	private static final Logger LOG = LogManager.getLogger(EModTreeCellRenderer.class);
    
	public EModTreeCellRenderer(ILabelProvider lp) {
		mLabelProvider = lp;
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if (mLabelProvider != null && value instanceof UNode node) {
			Pointer ptr = node.getPointer();
			if (ptr != null) {
				String labelP = mLabelProvider.getLabel(ptr);
				if (labelP != null) {
					setText(labelP);
				}
				URL url = mLabelProvider.getIcon(ptr);
				if (url != null) {
					ImageIcon icon = IconManager.getIcon(url, IconManager.preferedMidIconSize() - 2);
					if (icon != null) {
						setIcon(icon);
					}
				}
				String toolTip = mLabelProvider.getTooltip(node.getPointer());
				if (toolTip != null) {
					setToolTipText(toolTip);
				}
			} else {
				LOG.debug("Pointer in UNode " + node.getPath().toString() + " points to NULL.");
			}
		}
		return c;
	}
}