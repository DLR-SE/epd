package de.emir.epd.clock.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.UIManager;

import de.emir.epd.clock.ids.ClockBasics;
import de.emir.rcp.views.AbstractView;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ClockView extends AbstractView {

	private static final java.awt.Color CCTRL = UIManager.getColor("control");
	private static final java.awt.Color CFG = UIManager.getColor("Label.foreground");
	
	private static final ZoneId TZ_LOCAL = ZoneId.systemDefault();
	private static final ZoneId TZ_UTC = ZoneId.of("UTC");


	public ClockView(String id) {
		super(id);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createContent() {
		Container parent = this.getContentPane();
		JPanel p = new JPanel();
		parent.add(p);

		p.setLayout(new BorderLayout(0, 0));
		p.setMinimumSize(new Dimension(10, 10));
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(4, 12, 4, 12));
		parent.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{1, 0};
		gbl_panel.rowHeights = new int[]{1, 1};
		gbl_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_panel.rowWeights = new double[]{1.0, 1.0};
		panel.setLayout(gbl_panel);
		
		ClockLabel localTimeLabel = new ClockLabel(TZ_LOCAL/*"23:59:59"*/);
//		localTimeLabel.setBorder(new LineBorder(new Color(199, 21, 133)));
		localTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		localTimeLabel.setFont(UIManager.getFont("TextArea.font").deriveFont(Font.BOLD, ClockBasics.MIN_CLOCK_SIZE));
		localTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		localTimeLabel.setForeground(SystemColor.textHighlight);
		GridBagConstraints gbc_localTimeLabel = new GridBagConstraints();
		gbc_localTimeLabel.fill = GridBagConstraints.BOTH;
		gbc_localTimeLabel.weighty = 1.0;
		gbc_localTimeLabel.weightx = 1.0;
		gbc_localTimeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_localTimeLabel.gridx = 0;
		gbc_localTimeLabel.gridy = 0;
		panel.add(localTimeLabel, gbc_localTimeLabel);
		
		JLabel timezoneLabel = new JLabel(TZ_LOCAL.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
		timezoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_timezoneLabel = new GridBagConstraints();
		gbc_timezoneLabel.anchor = GridBagConstraints.WEST;
		gbc_timezoneLabel.insets = new Insets(0, 0, 5, 0);
		gbc_timezoneLabel.gridx = 1;
		gbc_timezoneLabel.gridy = 0;
		panel.add(timezoneLabel, gbc_timezoneLabel);
		
		ClockLabel utcTimeLabel = new ClockLabel(TZ_UTC/*"22:59:59"*/);
//		utcTimeLabel.setBorder(new LineBorder(new Color(0, 255, 0)));
		utcTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		utcTimeLabel.setFont(UIManager.getFont("TextArea.font").deriveFont(Font.BOLD, ClockBasics.MIN_CLOCK_SIZE));
		utcTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		utcTimeLabel.setForeground(SystemColor.textInactiveText);
		GridBagConstraints gbc_utcTimeLabel = new GridBagConstraints();
		gbc_utcTimeLabel.fill = GridBagConstraints.BOTH;
		gbc_utcTimeLabel.weighty = 1.0;
		gbc_utcTimeLabel.weightx = 1.0;
		gbc_utcTimeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_utcTimeLabel.gridx = 0;
		gbc_utcTimeLabel.gridy = 1;
		panel.add(utcTimeLabel, gbc_utcTimeLabel);
		
		JLabel utcLabel = new JLabel(TZ_UTC.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()));
		utcLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_utcLabel = new GridBagConstraints();
		gbc_utcLabel.anchor = GridBagConstraints.WEST;
		gbc_utcLabel.gridx = 1;
		gbc_utcLabel.gridy = 1;
		panel.add(utcLabel, gbc_utcLabel);

		return p;
	}

	@Override
	public void onClose() {
	}

	@Override
	public void onOpen() {
		
	}

}
