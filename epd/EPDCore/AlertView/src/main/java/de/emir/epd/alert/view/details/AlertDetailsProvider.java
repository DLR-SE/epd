package de.emir.epd.alert.view.details;

import de.emir.epd.alert.manager.Alert;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public abstract class AlertDetailsProvider extends AbstractAlertDetailsProvider {
    public AlertDetailsProvider(String id) {
        super(id);
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Container create() {
        Alert alert = getAlert();

        JPanel container = new JPanel();
        container.setBorder(new TitledBorder(null, alert.getId(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        container.setLayout(gridBagLayout);

        JLabel lblText = new JLabel(alert.getState().getIcon());
        lblText.setText("Status: " + alert.getState().getLabel());
        GridBagConstraints gbc_lblText = new GridBagConstraints();
        gbc_lblText.insets = new Insets(0, 0, 5, 5);
        gbc_lblText.gridx = 1;
        gbc_lblText.gridy = 1;
        container.add(lblText, gbc_lblText);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

        JLabel lblChanged = new JLabel("Last Update: " + alert.getDateTime().format(formatter));
        GridBagConstraints gbc_lblChanged = new GridBagConstraints();
        gbc_lblChanged.gridwidth = 2;
        gbc_lblChanged.insets = new Insets(0, 0, 5, 5);
        gbc_lblChanged.fill = GridBagConstraints.BOTH;
        gbc_lblChanged.gridx = 1;
        gbc_lblChanged.gridy = 2;
        container.add(lblChanged, gbc_lblChanged);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(getDetailedText());
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridwidth = 2;
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 1;
        gbc_textArea.gridy = 3;
        container.add(textArea, gbc_textArea);

        return container;
    }

    protected abstract String getDetailedText();
}
