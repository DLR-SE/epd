package de.emir.rcp.views.properties;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.DelegateReadOnlyProperty;
import de.emir.rcp.properties.ui.editors.StringEditor;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.prop.IProperty;

/**
 * The JPropertySheet displays all given Properties (see addProperties() and setInput()) 
 * with their respective (default) editor. 
 * The JPropertySheet also acts as a PropertyChangeListener, that forwards PropertyChangeEvents from one editor
 * to all registered listener
 * @author sschweigert
 *
 */
public class JPropertySheet extends JPanel implements PropertyChangeListener 
{

	private int mGridY = 0;
	private JTextField textField;
	
	private HashSet<PropertyChangeListener>		mListeners = new HashSet<>();
	
	/**
	 * Create the panel.
	 */
	public JPropertySheet() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		setLayout(gridBagLayout);
	}
	
	

	
	
	public void clear() {
		this.removeAll();
		mGridY = 0;
	}

	
	public void setInput(UObject uobj) {
		addProperties(PropertyManager.getInstance().collectProperties(uobj).values());
	}
	public void addProperties(Collection<IProperty> list) {
		GridBagConstraints gc_label = new GridBagConstraints();
		gc_label.gridx = 0;
		gc_label.gridy = mGridY;
		gc_label.fill = GridBagConstraints.NONE;
		gc_label.anchor = GridBagConstraints.WEST;
		gc_label.insets = new Insets(1, 1, 1, 10);
		gc_label.weighty = 1;
		
		GridBagConstraints gc_editor = new GridBagConstraints();
		gc_editor.gridx = 1;
		gc_editor.gridy = mGridY;
		gc_editor.fill = GridBagConstraints.BOTH;
		gc_editor.anchor = GridBagConstraints.NORTHWEST;
		gc_editor.weighty = 1;
		
		for (IProperty prop : list){
			gc_editor.gridy = mGridY;
			gc_label.gridy = mGridY;
			
			String name = prop.getName();
			JLabel lblName = new JLabel(name);
			String desc = prop.getDescription();
			if (desc != null) {
				if (desc.startsWith("<html>") == false)
					desc = "<html>" + desc + "</html>";
				lblName.setToolTipText(desc);
			}
			
			
			//TODO: support more than one editor
			IPropertyEditor editor = PropertyManager.getInstance().getFirstEditor(prop);
			if (editor == null){ //an backup if no editor could be found
				editor = new StringEditor();
				editor.setProperty(new DelegateReadOnlyProperty(prop));
			}
			Component editor_component = null;
			
			if (editor != null){
				editor_component = editor.getEditor();
			}
			if (editor != null && editor_component != null){
				add(lblName, gc_label);
				add(editor_component, gc_editor);
				if (!prop.isEditable())
					editor_component.setEnabled(false);
				else
					editor.addPropertyChangeListener(this);
				mGridY++;
			}
		}
		gc_label.gridy = mGridY;
		add(Box.createVerticalGlue(), gc_label);
		updateUI();
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//just forward the property change events
		firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
	}





}
