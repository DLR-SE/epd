/**
 * 
 */
package de.emir.epd.clock.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JLabel;

import de.emir.epd.clock.ids.ClockBasics;

/**
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class ClockLabel extends JLabel {
	private Graphics g;
	private TimerTask task;
	private ZoneId zone = ZoneId.systemDefault();
	
	public ClockLabel(ZoneId zone) {
		super();
		this.zone = zone;
		addComponentListener(new LabelListener());
		task = new TimerTask() {
	        public void run() {
	        	ClockLabel.this.setText(getCurrentTimeStamp());
	        	ClockLabel.this.repaint();
	        }
	    };
	    Timer timer = new Timer("Timer" + zone.getId());
	    long delay = 500L;
	    timer.scheduleAtFixedRate(task, 0, delay);	
	}

	private String getCurrentTimeStamp() {
		Instant now = Instant.now();
		StringBuilder sb = new StringBuilder("<html><body><div align=\"center\">");
		sb.append(now.atZone(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		sb.append("<br>");
	    sb.append(now.atZone(zone).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	    sb.append("</div></body></html>");
	    return sb.toString();
	}

	private class LabelListener extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			Rectangle componentBounds = ClockLabel.this.getBounds();
			int size = ClockBasics.MIN_CLOCK_SIZE;
			Font font = ClockLabel.this.getFont();
			Rectangle outerBounds = new Rectangle();
			while (size < ClockBasics.MAX_CLOCK_SIZE) {
				outerBounds.setSize(getTextSize(ClockLabel.this, font.deriveFont(font.getStyle(), size + 1)));
//				System.out.println(componentBounds + " " + outerBounds + " " + size);
				if (outerBounds.width > componentBounds.width || outerBounds.height > componentBounds.height) {
					break;
				}
				size++;
			}
			setFont(font.deriveFont(font.getStyle(), size));
			repaint();
		}
	}

	public Dimension getTextSize(JLabel label, Font font) {
		Dimension size = new Dimension();
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		//size.width = fm.stringWidth(" " + label.getText() + " ");
		size.width = fm.stringWidth(" 9999-99-99 ");
		size.height = fm.getHeight() * 2;
		return size;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
	}
}
