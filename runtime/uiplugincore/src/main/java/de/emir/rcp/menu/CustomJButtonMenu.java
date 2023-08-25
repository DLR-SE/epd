package de.emir.rcp.menu;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolTip;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.menu.ep.util.PopupMenuSeparatorVisibilityHandler;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class CustomJButtonMenu extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 478835743022944654L;
	private JPopupMenu popupMenu;
	private Boolean showDev;
	private String fullPath;
	private AbstractUIPlugin provider;
	
	private Polygon arrowPolygon;

	public CustomJButtonMenu(String label, ImageIcon icon, String tooltip, String fullPath, AbstractUIPlugin provider) {
		super(label);

		this.fullPath = fullPath;
		this.provider = provider;

		setToolTipText(tooltip);

		if (icon != null) {
			setIcon(icon);
		}

		setFocusPainted(false);

		popupMenu = new JPopupMenu();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				CustomJButtonMenu.this.setCursor(Cursor.getDefaultCursor());

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				CustomJButtonMenu.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

		});
		
		// Get some space for arrow down
		if(label != null) {
			setText(label + "   ");
		} else {
			setText("   ");
		}
		
		

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Component[] components = popupMenu.getComponents();

				for (int i = 0; i < components.length; i++) {
					if (components[i] instanceof JSeparator) {
						components[i].setVisible(i > 0 && i < components.length - 1);
					}
				}

				popupMenu.show(CustomJButtonMenu.this, 0, CustomJButtonMenu.this.getBounds().height);

			}
		});
		// To remove all unnecessary Separators
		popupMenu.addPopupMenuListener(new PopupMenuSeparatorVisibilityHandler(popupMenu));
		PropertyContext propContext = PropertyStore.getContext(Basic.DEV_PROP_CTX);
		IProperty<Boolean> property = propContext.getProperty(Basic.PROP_DEV_SHOW_MENU_CONTRIBUTIONS, false);
		showDev = property.getValue();

		if (showDev == true && tooltip == null) {
			setToolTipText("");
		}
		
		arrowPolygon = new Polygon();
		arrowPolygon.addPoint(0, 0);
		arrowPolygon.addPoint(7, 0);
		arrowPolygon.addPoint(3, 4);
		arrowPolygon.addPoint(0, 0);

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Rectangle b = getBounds();
		
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform transform = g2.getTransform();
		transform.translate(b.width - 13, b.height/2 - 2);
		g2.setTransform(transform);
		g2.setColor(getForeground());
		g2.fillPolygon(arrowPolygon);
		
	}

	@Override
	public JToolTip createToolTip() {

		if (showDev == true) {
			return new CustomDevTooltip(fullPath, provider);
		} else {
			return super.createToolTip();
		}

	}

	@Override
	public void setIcon(Icon defaultIcon) {

		ImageIcon icon = (ImageIcon) defaultIcon;
		icon.getImage();

		super.setIcon(defaultIcon);
	}

	public void addToPopup(JComponent c) {
		popupMenu.add(c);

	}
	
}
