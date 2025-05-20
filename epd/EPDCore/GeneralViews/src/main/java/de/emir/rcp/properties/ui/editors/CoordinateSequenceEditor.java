package de.emir.rcp.properties.ui.editors;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CoordinateSequenceEditor extends AbstractPropertyEditor<CoordinateSequence> {
    private JPanel mEditor = null;
    private int mGridY = 1;
	private JTextField textField;
    private IPropertyEditor mPeCRS;
    private JLabel mLblCRS;
    private CoordinateImpl mNewCoordinate = new CoordinateImpl();
	private HashSet<PropertyChangeListener>		mListeners = new HashSet<>();

	/**
	 * Create the panel.
	 * @wbp.parser.entryPoint
	 */
    @Override
	public Component createComponent() {
        if (mEditor == null){
			mEditor = new JPanel();
            
            GridBagLayout gridBagLayout = new GridBagLayout();
            gridBagLayout.columnWidths = new int[]{0, 0, 0};
            gridBagLayout.rowHeights = new int[]{0, 0};
            gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};

            mEditor.setLayout(gridBagLayout);
            
            PropertyManager pmgr = PropertyManager.getInstance();
			init();
        }
        return mEditor;
	}
    
    public void init() {
        CoordinateSequence cs = getValue();
        List<Coordinate> lc = new ArrayList<>();
        for (int i = 0; i < cs.numCoordinates(); i++) {
            Coordinate c = cs.getCoordinate(i);
            c.setCrs(cs.getCrs());
            lc.add(c);
        }
        addCoordinates(lc);
    }

	public void clear() {
        if (mEditor != null) mEditor.removeAll();
		mGridY = 1;
	}
    
	public void addCoordinates(List<Coordinate> list) {
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
		        
        mLblCRS = new JLabel("CRS");
        mPeCRS = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(getValue(), SpatialPackage.Literals.CoordinateSequence_crs));
		mEditor.add(mLblCRS, gc_label); 
		mEditor.add(mPeCRS.getEditor(), gc_editor);
        mGridY++;
        
        for (int i = 0; i < list.size(); i++) {
            Coordinate c = list.get(i);

			gc_editor.gridy = mGridY;
			gc_label.gridy = mGridY;

            IProperty<Coordinate> prop = new GenericProperty<>("Coordinate [" + i + "]", "", true, c);
            CoordinateEditor editor = new CoordinateEditor();
            editor.setProperty(prop);

            Component editor_component = null;
			
            String name = prop.getName();
			JLabel lblName = new JLabel(name);
			
			String desc = prop.getDescription();
            if (editor != null) {
                editor_component = editor.getEditor(false);
            }
            if (editor != null && editor_component != null) {
                mEditor.add(lblName, gc_label);
                mEditor.add(editor_component, gc_editor);
//                if (!prop.isEditable()) {
//                    editor_component.setEnabled(false);
//                } else {
//                    editor.addPropertyChangeListener(this);
//                }
                mGridY++;
            }
        }
        
        GridBagConstraints gc_hlight = new GridBagConstraints();
		gc_hlight.gridx = 0;
		gc_hlight.gridy = mGridY;
		gc_hlight.fill = GridBagConstraints.BOTH;
		gc_hlight.anchor = GridBagConstraints.NORTHWEST;
		gc_hlight.insets = new Insets(0, 4, 0, 4);
		gc_hlight.weighty = .15;
        gc_hlight.gridwidth = GridBagConstraints.REMAINDER;
//        JPanel pnlHlight = new JPanel();
//        pnlHlight.setBackground(UIManager.getColor("List.selectionBackground"));
//        pnlHlight.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JSeparator sprtr = new JSeparator(); 
        sprtr.setOrientation(SwingConstants.HORIZONTAL);
        mEditor.add(sprtr, gc_hlight);
        
        mGridY++;
        
		IProperty<Coordinate> prop = new GenericProperty<>("Add Coordinate", "", true, mNewCoordinate);
        CoordinateEditor editor = new CoordinateEditor();
        editor.setProperty(prop);
        Component editor_component = editor.getEditor(false);
        
        gc_label.gridy = mGridY;
        gc_editor.gridy = mGridY;
        
        mEditor.add(new JLabel(prop.getName()), gc_label);
        mEditor.add(editor_component, gc_editor);

		JButton addButton = new JButton("+");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = mGridY;
		mEditor.add(addButton, gbc_btnNewButton);
		addButton.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				getValue().addCoordinate(mNewCoordinate.copy());
                clear();
                init();
                mEditor.updateUI();
			}
		});
        
		JButton delButton = new JButton("-");
		GridBagConstraints gbc_btnDelButton = new GridBagConstraints();
		gbc_btnDelButton.gridx = 2;
		gbc_btnDelButton.gridy = mGridY - 2;
		mEditor.add(delButton, gbc_btnDelButton);
		delButton.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getValue() != null && getValue().numCoordinates() > 0) {
                    getValue().removeCoordinate(getValue().numCoordinates() - 1);
                    clear();
                    init();
                    mEditor.updateUI();
                }
			}
		});
		
		mGridY++;
		
		GridBagConstraints gc_glue = new GridBagConstraints();
		gc_glue.gridx = 0;
		gc_glue.gridy = mGridY;
		gc_glue.fill = GridBagConstraints.BOTH;
		gc_glue.anchor = GridBagConstraints.CENTER;
		gc_glue.insets = new Insets(4, 4, 4, 4);
		gc_glue.weighty = Integer.MAX_VALUE;
		mEditor.add(Box.createVerticalGlue(), gc_glue);
		mEditor.updateUI();
	}

    @Override
    public void dispose() {
        
    }

}

