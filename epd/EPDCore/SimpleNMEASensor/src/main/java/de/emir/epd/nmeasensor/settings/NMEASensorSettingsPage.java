package de.emir.epd.nmeasensor.settings;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.emir.epd.nmeasensor.NMEASensorPlugin;
import de.emir.epd.nmeasensor.data.ReceiverType;
import de.emir.epd.nmeasensor.data.SentenceType;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.ui.CheckBoxList;
import de.emir.epd.nmeasensor.ui.ReceiversList;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;

public class NMEASensorSettingsPage extends AbstractSettingsPage {
	private JComboBox<ReceiverType> connectionTypeComboBox;
	private JLabel connectionTypeLabel;
	private JPanel nmeaSourcePanel;
	private JPanel receiverPanel;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private CheckBoxList nmeaFilterList;
	private JToolBar toolBar;
	private JPanel receiverListPanel;
	private JButton btnAdd;
	private JButton btnClone;
	private JButton btnRemove;

	private JPanel receiverDetailPanel;
	private JPanel nmeaFilterPanel;
	private JScrollPane receiverListScrollPane;
	private ReceiversList receiverList;
	private JPanel detailContainerPanel;

	private DefaultListModel<IProperty> receiverListModel;
	/** Has the current receiver been changed? */
	private boolean dirtyFlag = false;
	/** TextField to change the receivers name. */
	private JTextField labelTextField;
	/** Checkbox to enable/disable current receiver. */
	private JCheckBox chckbxActive;
	/** The currently selected property. */
	protected AbstractProperty<String> activeProperty;
    /** The currently active receiver editor. */
    protected AbstractReceiverSettings activeReceiverSettings;
	/** Panel containing TCP receiver settings. */
	private TCPReceiverSettings tcpReceiverPanel;
	/** Panel containing UDP receiver settings. */
	private UDPReceiverSettings udpReceiverPanel;
	/** Panel containing serial receiver settings. */
	private SerialReceiverSettings serialReceiverPanel;
	/** Panel containing file receiver settings. */
	private FileReceiverSettings fileReceiverPanel;
	/** Container for receiver detailled settings. */
	private CardLayout cLayout;
	private JLabel lblNmeaReceiver;
	private JCheckBox chckbxAll;
    
    private PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
    private IProperty<Integer> nmeaSources;
    
    private JSplitPane splitPane_1;

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {
        nmeaSources = context.getProperty(NMEASensorIds.NMEA_SENSOR_PROP, 0);
        printProperty(nmeaSources);

        nmeaSourcePanel = new JPanel();
        nmeaSourcePanel.setPreferredSize(new Dimension(512, 384));
        splitPane = new JSplitPane();

        receiverPanel = new JPanel();
        receiverPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        receiverPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        splitPane.setRightComponent(receiverPanel);

        receiverPanel.setBorder(null);
        receiverPanel.setLayout(new BorderLayout(0, 0));

        nmeaFilterPanel = new JPanel();
        nmeaFilterPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        nmeaFilterPanel.setBorder(
                new TitledBorder(null, "NMEA Sentences", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        nmeaFilterPanel.setLayout(new BorderLayout(0, 0));

        chckbxAll = new JCheckBox("All");
        chckbxAll.addFocusListener(new FocusAdapterImpl());
        nmeaFilterPanel.add(chckbxAll, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        nmeaFilterPanel.add(scrollPane);

        nmeaFilterList = new CheckBoxList();
        nmeaFilterList.addFocusListener(new FocusAdapterImpl());
        nmeaFilterList.setBorder(new EmptyBorder(0, 0, 0, 0));
        nmeaFilterList.setBackground(UIManager.getColor("CheckBox.background"));
        DefaultListModel listmodel = new DefaultListModel();
        nmeaFilterList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        nmeaFilterList.setModel(listmodel);
        for (SentenceType t : SentenceType.values()) {
            listmodel.addElement(new JCheckBox(t.name()));
        }
        scrollPane.setViewportView(nmeaFilterList);

        lblNmeaReceiver = new JLabel("NMEA Receiver");
        lblNmeaReceiver.setBorder(new EmptyBorder(0, 0, 8, 8));
        receiverPanel.add(lblNmeaReceiver, BorderLayout.NORTH);

        splitPane_1 = new JSplitPane();
        receiverPanel.add(splitPane_1, BorderLayout.CENTER);

        detailContainerPanel = new JPanel();
        detailContainerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        receiverDetailPanel = new JPanel();
        cLayout = new CardLayout(0, 0);
        receiverDetailPanel.setLayout(cLayout);
        connectionTypeLabel = new JLabel();

        connectionTypeLabel.setText("Connection Type");
        connectionTypeComboBox = new JComboBox<ReceiverType>();

        connectionTypeComboBox.setModel(new DefaultComboBoxModel<ReceiverType>(ReceiverType.values()));

        JLabel lblName = new JLabel("Name");

        labelTextField = new JTextField();
        labelTextField.addFocusListener(new FocusAdapterImpl());
        labelTextField.setColumns(10);
        labelTextField.setEditable(true);

        chckbxActive = new JCheckBox("Active");
        chckbxActive.addFocusListener(new FocusAdapterImpl());
        chckbxActive.setHorizontalTextPosition(SwingConstants.LEADING);

        GroupLayout gl_panel_6 = new GroupLayout(detailContainerPanel);
        gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_6.createSequentialGroup()
                        .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel_6.createSequentialGroup().addContainerGap()
                                        .addComponent(connectionTypeLabel).addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addComponent(connectionTypeComboBox, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel_6.createSequentialGroup().addGap(2)
                                        .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                                .addComponent(chckbxActive)
                                                .addGroup(gl_panel_6.createSequentialGroup().addComponent(lblName)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(labelTextField, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(gl_panel_6.createSequentialGroup().addContainerGap().addComponent(
                                        receiverDetailPanel, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))
                        .addGap(0)));
        gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_6.createSequentialGroup()
                        .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
                                labelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxActive)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE).addComponent(connectionTypeLabel)
                                .addComponent(connectionTypeComboBox, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(receiverDetailPanel, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)));

        detailContainerPanel.setLayout(gl_panel_6);
        splitPane_1.setLeftComponent(detailContainerPanel);
        splitPane_1.setRightComponent(nmeaFilterPanel);
        receiverListPanel = new JPanel();
        splitPane.setLeftComponent(receiverListPanel);
        receiverListPanel.setLayout(new BorderLayout(0, 0));

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        receiverListPanel.add(toolBar, BorderLayout.NORTH);

        btnAdd = new JButton("");
        btnAdd.setIcon(IconManager.getIcon(this, "/icons/emiricons/32/add.png"));
        toolBar.add(btnAdd);

        btnClone = new JButton("");
		btnClone.setIcon(IconManager.getIcon(this, "/icons/emiricons/32/content_copy.png"));
        toolBar.add(btnClone);

        btnRemove = new JButton("");
        btnRemove.setIcon(IconManager.getIcon(this, "/icons/emiricons/32/delete.png"));
        toolBar.add(btnRemove);

        receiverListScrollPane = new JScrollPane();
        receiverListPanel.add(receiverListScrollPane, BorderLayout.CENTER);

        receiverListModel = new DefaultListModel<>();
        nmeaSourcePanel.setLayout(new BorderLayout(0, 0));
        receiverList = new ReceiversList();
        receiverList.setModel(receiverListModel);
        receiverListScrollPane.setViewportView(receiverList);
        splitPane.setDividerLocation(0.25d);
        splitPane.setResizeWeight(0.2);
        nmeaSourcePanel.add(splitPane);
        init();
        return nmeaSourcePanel;
    }

	private void fillValues() {
        String name = (String) activeProperty.getValue();
        labelTextField.setText(name);
        IProperty<String> type = context.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_TYPE, "UDP");
        ReceiverType selectedType = ReceiverType.valueOf(type.getValue());
		connectionTypeComboBox.setSelectedItem(selectedType);
        
		IProperty<Boolean> active = context.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE,
				false);
		chckbxActive.setSelected(active.getValue());
		for (int i = 0; i < nmeaFilterList.getModel().getSize(); i++) {
			JCheckBox cb = nmeaFilterList.getModel().getElementAt(i);
			cb.setSelected(false);
		}
		IProperty<String> sentences = context
				.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_SENTENCES, "");
        List<String> splitSentences = Arrays.asList(sentences.getValue().split(","));
        List<SentenceType> sentenceTypes = new ArrayList<>();
		for (String sentence : splitSentences) {
            if (sentence.isEmpty() || sentence.isBlank()) continue;
			nmeaFilterList.setSelected(sentence, true);
            try {
                sentenceTypes.add(SentenceType.valueOf(sentence));
            } catch (IllegalArgumentException e) {
                ULog.warn("No SentenceType for name \"{}\", ignoring entry.", sentence);
                continue;
            }
		}
        chckbxAll.setSelected(sentenceTypes.containsAll(Arrays.asList(SentenceType.values())));
        chckbxAll.invalidate();
        nmeaFilterList.repaint();
		
		dirtyFlag = false;
	}
    
    /**
     * Get the qualified name path to the currently active sensor config property.
     * 
     * @return the qualified name
     */
    public String getNamePath() {
        return NMEASensorIds.NMEA_SENSOR_PROP + "." + activeProperty.getName();
    }
    
    /**
     * Get the currently active (selected) property containing a sensor config.
     * 
     * @return the currenytly active property
     */
    public AbstractProperty<String> getActiveProperty() {
        return this.activeProperty;
    }

    /**
     * Check wether a name is already used in the list of NMEA recievers.
     * 
     * @param name the name to search for
     * @return <CODE>true</CODE> if the nema is already in use
     */
	private boolean nameTaken(String name) {
		if (receiverListModel == null || receiverListModel.isEmpty()) {
			return false;
		}
		for (int i = 0; i < receiverListModel.getSize(); i++) {
            IProperty<?> prop = receiverListModel.get(i);
			if (prop.getValue().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Make sure the correct settings are updated and displayed when the selection of the receiver type changes.
	 */
	public void readValues() {
		String selected = ((ReceiverType) connectionTypeComboBox.getSelectedItem()).name();
		cLayout.show(receiverDetailPanel, selected);
		printProperty(nmeaSources);
		receiverList.repaint();
	}

	/**
	 * Initializes the settings page with configurations for the receivers from the property store and registers
	 * actionListeners for the most common UI components in the NMEASensor settings page.
	 */
	public void init() {
		receiverListModel.clear();
        if (nmeaSources.getSubProperties() != null) {
            receiverListModel.addAll(nmeaSources.getSubProperties());
        }

        // Type of receiver is changed
        connectionTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiverType selected = ((ReceiverType) connectionTypeComboBox.getSelectedItem());

                if (receiverList.getSelectedValue() != null) {
					IProperty<String> type = context
							.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_TYPE, "UDP");
                    type.setValue(selected.name());
                    receiverList.invalidate();

                    switch (selected) {
                        case UDP:
                            udpReceiverPanel = new UDPReceiverSettings(NMEASensorSettingsPage.this);
                            receiverDetailPanel.add(udpReceiverPanel, ReceiverType.UDP.name());
                            activeReceiverSettings = udpReceiverPanel;
                            break;
                        case TCP:
                            tcpReceiverPanel = new TCPReceiverSettings(NMEASensorSettingsPage.this);
                            receiverDetailPanel.add(tcpReceiverPanel, ReceiverType.TCP.name());
                            activeReceiverSettings = tcpReceiverPanel;
                            break;
                        case Serial:
                            serialReceiverPanel = new SerialReceiverSettings(NMEASensorSettingsPage.this);
                            receiverDetailPanel.add(serialReceiverPanel, ReceiverType.Serial.name());
                            activeReceiverSettings = serialReceiverPanel;
                            break;
                        case File:
                            fileReceiverPanel = new FileReceiverSettings(NMEASensorSettingsPage.this);
                            receiverDetailPanel.add(fileReceiverPanel, ReceiverType.File.name());
                            activeReceiverSettings = fileReceiverPanel;
                            break;
                    }
                    activeReceiverSettings.init();
                    receiverList.repaint();
                }
                cLayout.show(receiverDetailPanel, selected.name());
                dirtyFlag = true;
            }
        });
        
        // Change the selected sensor
		receiverList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
                if (activeReceiverSettings != null) activeReceiverSettings.finish();
				IProperty<String> prop = receiverList.getSelectedValue();
				if (prop == null) return;
                
				if (dirtyFlag) {
					readValues();
					dirtyFlag = false;
				}
				activeProperty = (AbstractProperty<String>) receiverList.getSelectedValue();
				fillValues();
			}
		});
        
        // Initially select the first item and load the values.
		receiverList.setSelectedIndex(0);
		if (receiverList.getSelectedValue() != null) {
			activeProperty = (AbstractProperty<String>) receiverList.getSelectedValue();
			fillValues();
		}
        
        // Change the selected sentences
		nmeaFilterList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				dirtyFlag = true;
				readValues();
				readSentenceFilter();
			}
		});
        
        // Select all (or none) NMEA sentences
        chckbxAll.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent arg0) {
				if (!chckbxAll.isSelected()) {
                    nmeaFilterList.setAllSelected(false);
					nmeaFilterList.getSelectionModel().clearSelection();
                } else {
                    nmeaFilterList.setAllSelected(true);
                    int size = nmeaFilterList.getModel().getSize() - 1;
					if (size >= 0) {
						nmeaFilterList.setSelectionInterval(0, size);
					}
				}
                nmeaFilterList.repaint();
                readSentenceFilter();
			}
		});
        
        // Active status is changed
		chckbxActive.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				if (activeProperty == null) {
					return;
				}
				IProperty<Boolean> active = context
						.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE, false);
                active.setValue(chckbxActive.isSelected());
				receiverList.repaint();
				dirtyFlag = true;
			}
		});
        
        // remove receiver
		btnRemove.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				int index = receiverList.getSelectedIndex();
				IProperty<String> item = receiverList.getSelectedValue();
				if (item == null) {
					return;
				}
				// deactivate before removal
				IProperty<Boolean> active = context
						.getProperty(getNamePath() + '.' + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE, false);
                active.setValue(false);
                NMEASensorPlugin.getNMEASensors().removeSensor(activeProperty.getName());
                NMEASensorPlugin.getNMEASensors().removeSensor(item.getName());
				
                ((AbstractProperty<Integer>) nmeaSources).removeChild(item);
                item.dispose();
				receiverListModel.remove(index);
				receiverList.setSelectedIndex(index > 1 ? (index - 1) : 0);
				receiverList.repaint();
				receiverList.invalidate();
				dirtyFlag = true;
			}
		});
        
        // clone a receiver
        btnClone.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                int index = receiverList.getSelectedIndex();
				IProperty<String> item = receiverList.getSelectedValue();
				if (item == null) {
					return;
				}
                
				String id =  UUID.randomUUID().toString();
				String name = "Copy of " + (String) item.getValue();
				while (nameTaken(name)) {
					name += "_1";
				}
				String namePath = NMEASensorIds.NMEA_SENSOR_PROP + "." + id;
                IProperty<String> newProp = context.getProperty(namePath, "nmeaSensor", name);
                
				receiverListModel.addElement(newProp);
				receiverList.setSelectedValue(newProp, true);
                
				receiverList.invalidate();
                activeProperty = (AbstractProperty<String>) newProp;
				dirtyFlag = true;
				fillValues();
			}
		});
        
        // add a new receiver
		btnAdd.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				int num = receiverList.getModel().getSize();
				String id =  UUID.randomUUID().toString();
				String name = "Receiver" + (num + 1);
				while (nameTaken(name)) {
					name += "_1";
				}
                String namePath = NMEASensorIds.NMEA_SENSOR_PROP + "." + id;
                IProperty<String> newProp = context.getProperty(namePath, "nmeaSensor", name);
                num++;
                receiverListModel.addElement(newProp);
                receiverList.invalidate();
				receiverList.setSelectedValue(newProp, true);
                activeProperty = (AbstractProperty<String>) newProp;
				dirtyFlag = true;
				fillValues();
			}
		});

		// register listener for name changes
		labelTextField.getDocument().addDocumentListener(new DocumentListener()
        {
			@Override
			public void insertUpdate(DocumentEvent evt) {
				nameUpdate();
			}

			@Override
			public void removeUpdate(DocumentEvent evt) {
				nameUpdate();
			}

			@Override
			public void changedUpdate(DocumentEvent evt) {
				nameUpdate();
			}
        });
	}
	
	/**
	 * This method updates the sensors name in the backed model and the property.
	 */
	private void nameUpdate() {
		if (activeProperty != null && activeProperty.getName().equals(labelTextField.getText())) {
			// name has not changed, do nothing.
			return;
		}
		if (activeReceiverSettings != null) activeReceiverSettings.finish();
		if (!nameTaken(labelTextField.getText()) && !labelTextField.getText().isEmpty()) {
			if (activeProperty != null) {
				int index = receiverListModel.indexOf(activeProperty);
				activeProperty.setValue(labelTextField.getText());
				receiverList.getModel().getElementAt(index).setValue(labelTextField.getText());
				receiverList.repaint();
				receiverList.revalidate();
			}
			dirtyFlag = true;
		}
	}
    
	@Override
	public boolean isDirty() {
        if (activeReceiverSettings != null && activeReceiverSettings.isDirty()) dirtyFlag = true;
		return dirtyFlag;
	}

	@Override
	public void finish() {
        if (activeReceiverSettings != null) {
        	activeReceiverSettings.finish();
        	readValues();
    		readSentenceFilter();
        }
        printProperty(nmeaSources);
        nmeaSources.setValue((int) Math.random());
        PropertyStore.save();
	}
	
	/**
	 * Collect the selected checkboxes from the sentence filter list and create a filter String from it.
	 */
	private void readSentenceFilter() {
		StringBuilder sbSentenceFilter = new StringBuilder();
		for (int i = 0; i < nmeaFilterList.getModel().getSize(); i++) {
			JCheckBox cbItem = nmeaFilterList.getModel().getElementAt(i);
			if (cbItem.isSelected()) {
				sbSentenceFilter.append(cbItem.getText()).append(",");
			}
		}
		IProperty<String> prop = context.getProperty(getNamePath() + "." + NMEASensorIds.NMEA_SENSOR_PROP_SENTENCES,
				"");
		prop.setValue(sbSentenceFilter.toString().substring(0, Math.max(sbSentenceFilter.lastIndexOf(","), 0)));
		
		// Force update.
		nmeaSources.setValue(nmeaSources.getValue() + 1);
	}

	public static void printProperty(IProperty<?> property) {
		if (property == null || property.getValue() == null) {
			ULog.warn("No subproperties found while loading NMEASensorSettings");
			return;
		}
        ULog.trace(property.getName() + ": " + property.getValue());
        if (property.getSubProperties() != null) {
            ULog.trace("{");
            for (IProperty<?> prop : property.getSubProperties()) {
                printProperty(prop);
            }
            ULog.trace("}");
        }
    }

    private class FocusAdapterImpl extends FocusAdapter {

        public FocusAdapterImpl() {
        }

        @Override
        public void focusLost(FocusEvent e) {
            readValues();
        }
    }
}
