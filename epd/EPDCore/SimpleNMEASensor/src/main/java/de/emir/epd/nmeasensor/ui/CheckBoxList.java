package de.emir.epd.nmeasensor.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CheckBoxList extends JList<JCheckBox> {
	protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	public CheckBoxList() {
		setCellRenderer(new CellRenderer());

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int index = locationToIndex(e.getPoint());
				e.consume();
				if (index != -1) {
					JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
					checkbox.setSelected(!checkbox.isSelected());
					repaint();
				}
			}
		});

		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	protected class CellRenderer implements ListCellRenderer<JCheckBox> {
		public Component getListCellRendererComponent(JList list, JCheckBox value, int index, boolean isSelected,
				boolean cellHasFocus) {
            value.setEnabled(isEnabled());
			value.setFont(getFont());
			value.setFocusPainted(false);
//			checkbox.setBorderPainted(true);
//			checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
			return value;
		}
	}

	public void setSelected(String name, boolean value) {
		for (int i = 0; i < getModel().getSize(); i++) { 
			JCheckBox checkbox = getModel().getElementAt(i);
			if (checkbox.getText().equalsIgnoreCase(name)) {
				checkbox.setSelected(value);
			}
			repaint();
		}
	}
	
	public void setAllSelected(boolean value) {
		for (int i = 0; i < getModel().getSize(); i++) { 
			JCheckBox checkbox = getModel().getElementAt(i);
			checkbox.setSelected(value);
		}
		repaint();
	}
}