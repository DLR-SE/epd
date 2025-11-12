package de.emir.rcp.views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import de.emir.rcp.commands.basics.ExternalBrowserCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.runtime.epf.ClasspathEntry;
import de.emir.tuml.ucore.runtime.UCoreExtensionManager;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {
    private static final String URL_DLR = "https://www.dlr.de/se";
    private static final String URL_EMARITIME = "https://emaritime.de";
    private static final String URL_DIGITAL_INCUBATOR = "https://digitalincubator.maritimeconnectivity.net/";
    static final Logger LOG = LogManager.getLogger(AboutDialog.class);

    public AboutDialog() {
        init();
    }

    protected void init() {
        setTitle("Help");
        ImageIcon logo = getDLRLogo();
        if (logo != null){
            setIconImage(logo.getImage());
        }
        setAlwaysOnTop(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setSize(new Dimension(450, 290));
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(8, 8, 8, 8));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(gridBagLayout);   
        
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel(getContent());
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 5;
        gbc_lblNewLabel.gridheight = 2;
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblDlr = new JLabel(asURLReference(URL_DLR));
        lblDlr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblDlr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
        			ExternalBrowserCommand browseDLRCmd = new ExternalBrowserCommand(new URI(URL_DLR));
        			browseDLRCmd.execute();
        		} catch (URISyntaxException e) {
        			LOG.error("Not a valid URI.", e);
        		}
            }
        });
        GridBagConstraints gbc_lblDlr = new GridBagConstraints();
        gbc_lblDlr.anchor = GridBagConstraints.WEST;
        gbc_lblDlr.gridwidth = 5;
        gbc_lblDlr.insets = new Insets(0, 0, 5, 0);
        gbc_lblDlr.gridx = 0;
        gbc_lblDlr.gridy = 4;
        panel.add(lblDlr, gbc_lblDlr);

        JLabel lblDlr_1 = new JLabel(asURLReference(URL_EMARITIME));
        lblDlr_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblDlr_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
        			ExternalBrowserCommand browseDLRCmd = new ExternalBrowserCommand(new URI(URL_EMARITIME));
        			browseDLRCmd.execute();
        		} catch (URISyntaxException e) {
        			LOG.error("Not a valid URI.", e);
        		}
            }
        });
        GridBagConstraints gbc_lblDlr_1 = new GridBagConstraints();
        gbc_lblDlr_1.anchor = GridBagConstraints.WEST;
        gbc_lblDlr_1.gridwidth = 5;
        gbc_lblDlr_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblDlr_1.gridx = 0;
        gbc_lblDlr_1.gridy = 5;
        panel.add(lblDlr_1, gbc_lblDlr_1);

        JLabel lblDlr_1_1 = new JLabel(asURLReference(URL_DIGITAL_INCUBATOR));
        lblDlr_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblDlr_1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
        			ExternalBrowserCommand browseDLRCmd = new ExternalBrowserCommand(new URI(URL_DIGITAL_INCUBATOR));
        			browseDLRCmd.execute();
        		} catch (URISyntaxException e) {
        			LOG.error("Not a valid URI.", e);
        		}
            }
        });
        GridBagConstraints gbc_lblDlr_1_1 = new GridBagConstraints();
        gbc_lblDlr_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblDlr_1_1.gridwidth = 5;
        gbc_lblDlr_1_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblDlr_1_1.gridx = 0;
        gbc_lblDlr_1_1.gridy = 6;
        panel.add(lblDlr_1_1, gbc_lblDlr_1_1);

        JLabel lblLogo = new JLabel(getDLRLogo());
        GridBagConstraints gbc_lblLogo = new GridBagConstraints();
        gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
        gbc_lblLogo.gridx = 0;
        gbc_lblLogo.gridy = 7;
        panel.add(lblLogo, gbc_lblLogo);

        JLabel lblLogo_1 = new JLabel(getDigitalIncubatorLogo());
        GridBagConstraints gbc_lblLogo_1 = new GridBagConstraints();
        gbc_lblLogo_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblLogo_1.gridx = 2;
        gbc_lblLogo_1.gridy = 7;
        panel.add(lblLogo_1, gbc_lblLogo_1);
        
        JButton btnNewButton = new JButton("License");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		LicenseDialog lic = new LicenseDialog();
        		lic.setVisible(true);
        	}
        });
        
        JButton btnExtensions = new JButton("Extensions");
        btnExtensions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StringBuilder sb = new StringBuilder();
        		for (Object o : UCoreExtensionManager.getExtensions(Object.class)) {
        			String entry = o.getClass().getName();
        			if (o instanceof ClasspathEntry cpe && cpe.getPluginClass() != null) {
        				entry = cpe.getCoordinate() + " - " + cpe.getPluginClass();
        			} else if (o instanceof ClasspathEntry cpe && cpe.getPluginClass() == null) {
        				continue;
        			}
        			LOG.info(" " + entry);
        			sb.append(entry).append("\n");
        		}
        		final JTextArea textArea = new JTextArea(20, 80);
        	    textArea.setText(sb.toString());
        	    textArea.setEditable(false);
        	    final JScrollPane scrollPane = new JScrollPane(textArea);
        		JOptionPane.showMessageDialog(panel, scrollPane, "Loaded Extensions", JOptionPane.PLAIN_MESSAGE);
        	}
        });
        GridBagConstraints gbc_btnExtensions = new GridBagConstraints();
        gbc_btnExtensions.anchor = GridBagConstraints.SOUTHEAST;
        gbc_btnExtensions.insets = new Insets(0, 0, 5, 5);
        gbc_btnExtensions.gridx = 3;
        gbc_btnExtensions.gridy = 7;
        panel.add(btnExtensions, gbc_btnExtensions);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.SOUTHEAST;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 7;
        panel.add(btnNewButton, gbc_btnNewButton);
        setLocationRelativeTo(PlatformUtil.getWindowManager().getMainWindow());
    }

    protected String asURLReference(String url){
        return "<html><a href='" +
                url +
                "'>" +
                url +
                "</a></html>";
    }

    protected ImageIcon getDLRLogo() {
        ResourceManager resourceManager = ResourceManager.get(AboutDialog.class);
        ImageIcon icon = resourceManager.getImageIcon("branding/DLR-Programm_Icon.png");
        if (icon != null) {
            return new ImageIcon(
                    icon.getImage()
                            .getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
            );
        } else {
            return null;
        }
    }

    protected ImageIcon getDigitalIncubatorLogo() {
        ResourceManager resourceManager = ResourceManager.get(AboutDialog.class);
        ImageIcon icon = resourceManager.getImageIcon("branding/digital_incubator.png");
        if (icon != null) {
            return new ImageIcon(
                    icon.getImage()
                            .getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
            );
        } else {
            return null;
        }
    }

    protected String getContent() {
        return "<html>" +
                "<h1 style=\"text-align: center\">" +
                "eMaritime Prototype Display" +
                "</h1>" +
                "<p>" +
                "The EPD is an open source prototype platform for the development and testing of ECDIS functions. " +
                "It was developed at the German Aerospace Center and is part of the Digital Incubator Initiative." +
                "</p>" +
                "</html>";
    }
}
