package de.emir.rcp.properties.ui.editors;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.fife.ui.autocomplete.AutoCompletion;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.ModelPath;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.util.PointerStringAutocompletion;
import de.emir.rcp.util.WidgetUtils;
import de.emir.rcp.util.WidgetUtils.ICompletionProvider;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class ModelPathEditor extends AbstractPropertyEditor<ModelPath> {

	
	private JPanel mEditor = null;
	
	@Override
	public void dispose() {

	}

	@Override
	protected Component createComponent() {
		if (mEditor == null){
			mEditor = new JPanel();
			
			IProperty prop = PropertyManager.getInstance().createProperty(PointerOperations.create(getValue(), CorePackage.Literals.ModelPath_rootInstance));
			if (prop instanceof AssociationProperty && getProperty() instanceof AssociationProperty && ((AssociationProperty)getProperty()).getValueHint() != null)
				((AssociationProperty)prop).setValueHint(((AssociationProperty)getProperty()).getValueHint());
			IPropertyEditor rootEditor = PropertyManager.getInstance().getFirstEditor(prop);
						
			String ptrStr = getValue() != null ? getValue().getPointerString() : null;
			
			
			JTextField tf = new JTextField(ptrStr, 20);		
			PointerStringAutocompletion provider = new PointerStringAutocompletion();
			UObject uobj = getValue().getRootInstance();
			provider.setRootElement(uobj);
			AutoCompletion ac = new AutoCompletion(provider);
			ac.setAutoActivationDelay(100);
			ac.setAutoCompleteSingleChoices(true);
			ac.setAutoActivationEnabled(true);
			ac.setShowDescWindow(true);
			ac.setParameterAssistanceEnabled(true);
			
			ac.install(tf);	
			tf.addFocusListener(new FocusAdapter() {
				String txt;
				@Override
				public void focusGained(FocusEvent e) {
					txt = tf.getText();
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					if (txt != tf.getText() && getProperty().isEditable())
						getValue().setPointerString(tf.getText());
				}
			});
			tf.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (getProperty().isEditable())
						getValue().setPointerString(tf.getText());
				}
			});
			tf.setEnabled(getProperty().isEditable());
			
			
			mEditor.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = gbc.gridy = 0;
			mEditor.add(new JLabel("Root"), gbc); 
			gbc.gridy = 1;
			mEditor.add(new JLabel("Pointer String"), gbc); 
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			mEditor.add(rootEditor.getEditor(), gbc);
			gbc.gridy = 1;
			mEditor.add(tf, gbc);
		}
		return mEditor;
	}
}
