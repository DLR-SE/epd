package de.emir.epd.nmeasensor.settings;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slf4j.Logger;

import de.emir.epd.nmeasensor.data.ReceiverProperty;
import de.emir.epd.nmeasensor.data.ReceiverProperty.ReceiverType;
import de.emir.epd.nmeasensor.data.SentenceType;
import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.ui.CheckBoxList;
import de.emir.epd.nmeasensor.ui.ReceiversList;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class NMEASensorSettingsPage extends AbstractSettingsPage {

	private JList<String> list;

	private PropertySpinnerWidget portSpinner;

	private JComboBox<ReceiverType> connectionTypeComboBox;
	private JLabel connectionTypeLabel;
	private JPanel nmeaSourcePanel;
	private JPanel receiverPanel;
	private JLabel label;
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

	private DefaultListModel<ReceiverProperty> receiverListModel;
	/** Has the current receiver been changed? */
	private boolean dirtyFlag = false;
	/** TextField to change the receivers name. */
	private JTextField labelTextField;
	/** Checkbox to enable/disable current receiver. */
	private JCheckBox chckbxActive;
	/** The currently selected detailled reciever property. */
	protected ReceiverProperty receiverProperty;
	/** The currently selected property. */
	protected ReceiverProperty activeProperty;
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
    
    private List<ReceiverProperty> subProps;
    IProperty<List<Map<String, Object>>> nmeaSources;
    IProperty<Long> lastUpdate;
    
    public static Logger LOG = ULog.getLogger(NMEASensorSettingsPage.class);

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {
		PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);
		nmeaSources = context.getProperty(NMEASensorIds.NMEA_SENSOR_PROP, new ArrayList<Map<String, Object>>());
        lastUpdate = context.getProperty(NMEASensorIds.NMEA_SENSOR_PROP_NMEA_SOURCE, 0L);
        printProperty(nmeaSources);
		java.awt.GridBagConstraints gridBagConstraints;

		nmeaSourcePanel = new JPanel();
		nmeaSourcePanel.setPreferredSize(new Dimension(512, 384));
		splitPane = new JSplitPane();

		receiverPanel = new JPanel();
		receiverPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		receiverPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		splitPane.setRightComponent(receiverPanel);

		receiverPanel.setBorder(null);
		receiverPanel.setLayout(new BorderLayout(0, 0));

		detailContainerPanel = new JPanel();
		detailContainerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		receiverDetailPanel = new JPanel();
		cLayout = new CardLayout(0, 0);
		receiverDetailPanel.setLayout(cLayout);
		receiverPanel.add(detailContainerPanel, BorderLayout.CENTER);
		connectionTypeLabel = new JLabel();

		connectionTypeLabel.setText("Connection Type");
		connectionTypeComboBox = new JComboBox<ReceiverProperty.ReceiverType>();

		connectionTypeComboBox.setModel(
				new DefaultComboBoxModel<ReceiverProperty.ReceiverType>(ReceiverProperty.ReceiverType.values()));
		tcpReceiverPanel = new TCPReceiverSettings(this);
		receiverDetailPanel.add(tcpReceiverPanel, ReceiverProperty.ReceiverType.TCP.name());
		udpReceiverPanel = new UDPReceiverSettings(this);
		receiverDetailPanel.add(udpReceiverPanel, ReceiverProperty.ReceiverType.UDP.name());
		serialReceiverPanel = new SerialReceiverSettings(this);
		receiverDetailPanel.add(serialReceiverPanel, ReceiverProperty.ReceiverType.Serial.name());
		fileReceiverPanel = new FileReceiverSettings(this);
		receiverDetailPanel.add(fileReceiverPanel, ReceiverProperty.ReceiverType.File.name());

		JLabel lblName = new JLabel("Name");

		labelTextField = new JTextField();
		labelTextField.addFocusListener(new FocusAdapterImpl());
		labelTextField.setColumns(10);

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

		nmeaFilterPanel = new JPanel();
		nmeaFilterPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		nmeaFilterPanel.setBorder(
				new TitledBorder(null, "NMEA Sentences", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		receiverPanel.add(nmeaFilterPanel, BorderLayout.EAST);
		nmeaFilterPanel.setLayout(new BorderLayout(0, 0));

		chckbxAll = new JCheckBox("All");
		chckbxAll.addFocusListener(new FocusAdapterImpl());
		chckbxAll.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent arg0) {
				if (!chckbxAll.isSelected()) {
					nmeaFilterList.getSelectionModel().clearSelection();
                    nmeaFilterList.setAllSelected(false);
                    
				} else {
					int size = nmeaFilterList.getModel().getSize() - 1;
					if (size >= 0) {
						nmeaFilterList.setSelectionInterval(0, size);
					}
                    nmeaFilterList.setAllSelected(true);
				}
                nmeaFilterList.repaint();
                readValues();
			}
		});
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

		receiverListPanel = new JPanel();
		splitPane.setLeftComponent(receiverListPanel);
		receiverListPanel.setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		receiverListPanel.add(toolBar, BorderLayout.NORTH);

		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(NMEASensorSettingsPage.class.getResource("/icons/emiricons/32/add.png")));
		toolBar.add(btnAdd);

		btnClone = new JButton("");
		btnClone.setIcon(new ImageIcon(NMEASensorSettingsPage.class.getResource("/icons/emiricons/32/content_copy.png")));
		toolBar.add(btnClone);

		btnRemove = new JButton("");
		btnRemove.setIcon(new ImageIcon(NMEASensorSettingsPage.class.getResource("/icons/emiricons/32/delete.png")));
		toolBar.add(btnRemove);

		receiverListScrollPane = new JScrollPane();
		receiverListPanel.add(receiverListScrollPane, BorderLayout.CENTER);

		receiverListModel = new DefaultListModel();
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
		tcpReceiverPanel.init(receiverProperty);
		udpReceiverPanel.init(receiverProperty);
		serialReceiverPanel.init(receiverProperty);
		fileReceiverPanel.init(receiverProperty);

		labelTextField.setText(receiverProperty.getLabel());
		connectionTypeComboBox
				.setSelectedItem(receiverProperty.getReceiverType() != null ? receiverProperty.getReceiverType()
						: ReceiverProperty.ReceiverType.UDP);
		chckbxActive.setSelected(receiverProperty.isActive());
		for (int i = 0; i < nmeaFilterList.getModel().getSize(); i++) {
			JCheckBox cb = nmeaFilterList.getModel().getElementAt(i);
			cb.setSelected(false);
		}

		for (SentenceType type : receiverProperty.getNmeaSentences()) {
			nmeaFilterList.setSelected(type.name(), true);
		}
        chckbxAll.setSelected(receiverProperty.getNmeaSentences().containsAll(Arrays.asList(SentenceType.values())));
        nmeaFilterList.repaint();
		
		dirtyFlag = false;
	}

	private static boolean nameTaken(String name, List<ReceiverProperty> properties) {
		if (properties == null || properties.size() == 0) {
			return false;
		}
		for (ReceiverProperty prop : properties) {
			if (prop == null) continue;
			if (prop.getLabel().equals(name))
				return true;
		}
		return false;
	}

	public void readValues() {
		if (receiverProperty == null || activeProperty == null)
			return;
		String selected = ((ReceiverProperty.ReceiverType) connectionTypeComboBox.getSelectedItem()).name();
		cLayout.show(receiverDetailPanel, selected);
		receiverProperty.setReceiverType((ReceiverProperty.ReceiverType) connectionTypeComboBox.getSelectedItem());
		if ( !labelTextField.getText().isEmpty() && !nameTaken(labelTextField.getText(), subProps)) {
			receiverProperty.setLabel(labelTextField.getText());
		}
		receiverProperty.setActive(chckbxActive.isSelected());
		receiverProperty.getNmeaSentences().clear();
		for (int i = 0; i < nmeaFilterList.getModel().getSize(); i++) {
			JCheckBox cb = nmeaFilterList.getModel().getElementAt(i);
			if (cb.isSelected()) {
				receiverProperty.getNmeaSentences().add(SentenceType.valueOf(cb.getText()));
			}
		}
		activeProperty = receiverProperty;
		printProperty(nmeaSources);
		
		receiverList.repaint();
		
		// Clear sources and write them all again from maps
		nmeaSources.getValue().clear();
		for (ReceiverProperty rp : subProps) {
			nmeaSources.getValue().add(rp.toMap());
		}
		nmeaSources.setValue(nmeaSources.getValue());
	}

	public void init() {
		receiverListModel.clear();
		if (nmeaSources.getValue() == null) {
			nmeaSources.setValue(new ArrayList<Map<String, Object>>());
		}
		subProps = new ArrayList<ReceiverProperty>();
		for (Map<String, Object> rpmap : nmeaSources.getValue()) {
			subProps.add(new ReceiverProperty(rpmap));
		}
		if (subProps != null) {
			for (ReceiverProperty prop : subProps) {
				if (prop == null) continue;
				receiverListModel.addElement(prop);
			}
		}
		connectionTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				readValues();
				String selected = ((ReceiverProperty.ReceiverType) connectionTypeComboBox.getSelectedItem()).name();
				cLayout.show(receiverDetailPanel, selected);
				if(receiverProperty != null) {
					receiverProperty
							.setReceiverType((ReceiverProperty.ReceiverType) connectionTypeComboBox.getSelectedItem());
					receiverProperty.setLabel(labelTextField.getText());
				}
				dirtyFlag = true;
			}
		});
		receiverList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ReceiverProperty prop = (ReceiverProperty) receiverList.getSelectedValue();
				if (prop == null || !(prop instanceof ReceiverProperty))
					return;
				if (dirtyFlag) {
					readValues();
					dirtyFlag = false;
				}
				receiverProperty = (ReceiverProperty) prop;
				activeProperty = prop;
				fillValues();
			}
		});
		nmeaFilterList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for (int i = 0; i < nmeaFilterList.getModel().getSize(); i++) {
					JCheckBox cb = nmeaFilterList.getModel().getElementAt(i);
					if (cb.isSelected()) {
						if(receiverProperty != null) {
							receiverProperty.getNmeaSentences().add(SentenceType.valueOf(cb.getText()));
						}
					}
				}
				dirtyFlag = true;
				readValues();
			}
		});
		chckbxActive.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				if (receiverProperty == null)
					return;
				readValues();
				receiverList.repaint();
				dirtyFlag = true;
			}
		});
		receiverList.setSelectedIndex(0);
		if (receiverList.getSelectedValue() != null
				&& receiverList.getSelectedValue() instanceof ReceiverProperty) {
			receiverProperty = (ReceiverProperty) receiverList.getSelectedValue();
			activeProperty = receiverList.getSelectedValue();
			fillValues();
		}
        
        // remove receiver
		btnRemove.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				int index = receiverList.getSelectedIndex();
				ReceiverProperty item = (ReceiverProperty) receiverList.getSelectedValue();
				if (item == null)
					return;

				nmeaSources.getValue().remove(index);
				receiverListModel.clear();
				subProps = new ArrayList<ReceiverProperty>();
				for (Map<String, Object> rpmap : nmeaSources.getValue()) {
					subProps.add(new ReceiverProperty(rpmap));
				}
				if (subProps != null) {
					for (ReceiverProperty prop : subProps) {
						receiverListModel.addElement(prop);
					}
				}
				receiverList.setSelectedIndex(index > 1 ? (index - 1) : 0);
				receiverList.repaint();
				receiverList.invalidate();
				dirtyFlag = true;
				nmeaSources.setValue(nmeaSources.getValue());
			}
		});
        
        // clone a receiver
        btnClone.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                int index = receiverList.getSelectedIndex();
				ReceiverProperty item = (ReceiverProperty) receiverList.getSelectedValue();
				if (item == null)
					return;
                
				ReceiverProperty rp = new ReceiverProperty((ReceiverProperty) item);

				int num = 0;
				if (subProps != null) {
					for (ReceiverProperty prop : subProps) {
						if (prop != null) num++;
					}
				}
				String name = "Copy of " + rp.getLabel();
				while (nameTaken(name, subProps)) {
					name += "_1";
				}
				rp.setLabel(name);
				dirtyFlag = true;

				subProps.add(rp);
                
				receiverListModel.addElement(rp);
				receiverList.setSelectedValue(rp, true);
				receiverList.invalidate();
                receiverProperty = rp;
                activeProperty = rp;
				fillValues();
				dirtyFlag = true;
			}
		});
        
        // add a new receiver
		btnAdd.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				ReceiverProperty rp = new ReceiverProperty();

				int num = 0;
				subProps = new ArrayList<ReceiverProperty>();
				for (Map<String, Object> rpmap : nmeaSources.getValue()) {
					subProps.add(new ReceiverProperty(rpmap));
				}
				if (subProps != null) {
					for (ReceiverProperty prop : subProps) {
						if (prop != null) num++;
					}
				}
				String name = "Receiver" + (num + 1);
				while (nameTaken(name, subProps)) {
					name += "_1";
				}

				rp.setActive(false);
				rp.setLabel(name);

				dirtyFlag = true;
				subProps = new ArrayList<ReceiverProperty>();
				for (Map<String, Object> rpmap : nmeaSources.getValue()) {
					subProps.add(new ReceiverProperty(rpmap));
				}
				subProps.add(rp);

                receiverListModel.addElement(rp);
				receiverList.setSelectedValue(rp, true);
                
				receiverList.invalidate();
                receiverProperty = rp;
                activeProperty = rp;
				dirtyFlag = true;
				nmeaSources.setValue(nmeaSources.getValue());
				fillValues();
			}
		});
        
		labelTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				readValues();
				receiverList.repaint();
				dirtyFlag = true;
			}
		});

		labelTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				readValues();
				receiverList.invalidate();
				dirtyFlag = true;
			}
		});
		labelTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {

			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!nameTaken(labelTextField.getText(), subProps)
						&& !labelTextField.getText().isEmpty()) {
					readValues();
					if(receiverProperty != null) {
						receiverProperty.setLabel(labelTextField.getText());
					}
					receiverList.repaint();
					dirtyFlag = true;
				}
			}
		});
	}

	@Override
	public boolean isDirty() {
		return dirtyFlag || tcpReceiverPanel.isDirty() || udpReceiverPanel.isDirty() || serialReceiverPanel.isDirty()
				|| fileReceiverPanel.isDirty();
	}

	@Override
	public void finish() {
		readValues();
        lastUpdate.setValue(System.currentTimeMillis());
        printProperty(nmeaSources);
	}

	public static void printProperty(IProperty<List<Map<String, Object>>> property) {
		if (property == null || property.getValue() == null) {
			LOG.warn("No subproperties found while loading NMEASensorSettings");
			return;
		}
		List<Map<String, Object>> subProps = property.getValue();
		for (Map<String, Object> prop : subProps) {
			ReceiverProperty rp = new ReceiverProperty(prop);
			
			if (rp == null || !(rp instanceof ReceiverProperty)) { 
				LOG.warn("Found wrong type in subproperties", prop);
				continue;
			}

			LOG.debug(rp.toString());
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
