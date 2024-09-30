package de.emir.rcp.views.properties;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.ViewManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.DelegateReadOnlyProperty;
import de.emir.rcp.properties.ui.editors.StringEditor;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;

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

	private int mGridY = 1;
	private JTextField textField;
	private static Icon HINT_ICON = IconManager.getIcon(JPropertySheet.class,
            "icons/emiricons/32/lightbulb.png", IconManager.preferedSmallIconSize());
    private static Icon HELP_ICON = IconManager.getIcon(JPropertySheet.class,
            "icons/emiricons/32/info.png", IconManager.preferedSmallIconSize());
    private boolean showHints = false;

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
		mGridY = 1;
	}


	public void setInput(UObject uobj) {
		addProperties(PropertyManager.getInstance().collectProperties(uobj).values());
	}
	public void addProperties(Collection<IProperty> list) {
		int fntSz = UIManager.getFont("Label.font").getSize();
		GridBagConstraints gc_label = new GridBagConstraints();
		gc_label.gridx = 0;
		gc_label.gridy = mGridY;
		gc_label.fill = GridBagConstraints.NONE;
		gc_label.anchor = GridBagConstraints.EAST;
		gc_label.insets = new Insets(4, 4, 4, 4);
		gc_label.weighty = 1;

		GridBagConstraints gc_editor = new GridBagConstraints();
		gc_editor.gridx = 1;
		gc_editor.gridy = mGridY;
		gc_editor.fill = GridBagConstraints.BOTH;
		gc_editor.anchor = GridBagConstraints.NORTHWEST;
		gc_editor.insets = new Insets(4, 4, 4, 0);
		gc_editor.weighty = 1;
		
		GridBagConstraints gc_hint = new GridBagConstraints();
		gc_hint.gridx = 1;
		gc_hint.gridwidth = 1;
		gc_hint.gridy = mGridY + 1;
		gc_hint.fill = GridBagConstraints.HORIZONTAL;
		gc_hint.anchor = GridBagConstraints.CENTER;
		gc_hint.insets = new Insets(0, 4, 4, 4);
		gc_hint.weighty = 1;
		
		GridBagConstraints gc_hintbutton = new GridBagConstraints();
		gc_hintbutton.gridx = 2;
		gc_hintbutton.gridwidth = 1;
		gc_hintbutton.gridy = 0;
		gc_hintbutton.fill = GridBagConstraints.HORIZONTAL;
		gc_hintbutton.anchor = GridBagConstraints.CENTER;
		gc_hintbutton.insets = new Insets(0, 0, 0, 0);
		gc_hintbutton.weighty = 1;
		
		JToggleButton toggleHints = new JToggleButton(HELP_ICON);
		toggleHints.setToolTipText("Toggle Hints");
        toggleHints.setSelected(showHints);
		toggleHints.addActionListener((ActionEvent e) -> {
            showHints = !showHints;
            clear();
            addProperties(list);
        });
        add(toggleHints, gc_hintbutton);

		for (IProperty prop : list){
			gc_editor.gridy = mGridY;
			gc_label.gridy = mGridY;
            gc_hint.gridy = mGridY + 1;

			String name = prop.getName();
			JLabel lblName = new JLabel(name);
			
			String desc = prop.getDescription();
            boolean hasHint = false;
			if (desc != null && !desc.isEmpty() && !desc.isBlank()) {
				if (desc.startsWith("<html>") == false)
					desc = "<html>" + desc + "</html>";
				lblName.setToolTipText(desc);
                hasHint = true;
			}

            //TODO: support more than one editor
            IPropertyEditor editor = PropertyManager.getInstance().getFirstEditor(prop);
            if (editor == null) { //an backup if no editor could be found
                editor = new StringEditor();
                editor.setProperty(new DelegateReadOnlyProperty(prop));
            }
            Component editor_component = null;

            if (editor != null) {
                editor_component = editor.getEditor();
            }
            if (editor != null && editor_component != null) {
                add(lblName, gc_label);
                add(editor_component, gc_editor);
                if (!prop.isEditable()) {
                    editor_component.setEnabled(false);
                } else {
                    editor.addPropertyChangeListener(this);
                }
                mGridY++;
            }
            if (hasHint && toggleHints.isSelected()) {
                JLabel lblHint = new JLabel(desc);
    			lblHint.setFont(UIManager.getFont("Label.font").deriveFont(fntSz - 1f).deriveFont(Font.ITALIC));
        		lblHint.setForeground(UIManager.getColor("textInactiveText"));
                lblHint.setVisible(true);
                lblHint.setIcon(HINT_ICON);
                add(lblHint, gc_hint);
                mGridY++;
            }
        }
		gc_label.gridy = mGridY;

		GridBagConstraints gc_glue = new GridBagConstraints();
		gc_glue.gridx = 0;
		gc_glue.gridy = mGridY;
		gc_glue.fill = GridBagConstraints.BOTH;
		gc_glue.anchor = GridBagConstraints.CENTER;
		gc_glue.insets = new Insets(4, 4, 4, 4);
		gc_glue.weighty = Integer.MAX_VALUE;
		add(Box.createVerticalGlue(), gc_glue);
		updateUI();
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//just forward the property change events
		firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
	}





}
