package de.emir.epd.clock.view;

import java.awt.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import de.emir.epd.clock.ids.ClockBasics;

/**
 * Class for visualizing time labels. These update their time automatically. Used in the ClockView.
 *
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 */
public class ClockLabel extends JLabel {
    private Graphics g;
    private ZoneId zone;
	private boolean useLineBreak = true;

    /**
     * Creates a new clock label for the given timezone.
     *
     * @param zone Zone to use for label.
     */
    public ClockLabel(ZoneId zone) {
        super();
        this.zone = zone;
        TimerTask task = new TimerTask() {
            public void run() {
                String text = getCurrentTimeStamp();
                SwingUtilities.invokeLater(() -> ClockLabel.this.setText(text));
            }
        };
        new Timer("Timer" + zone.getId()).scheduleAtFixedRate(task, 0, 500L);
    }

    /**
     * Formats the current time to a formatted string.
     *
     * @return Formatted string based on current time.
     */
    private String getCurrentTimeStamp() {
        Instant now = Instant.now();
        String date = now.atZone(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.atZone(zone).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if (useLineBreak) {
            return "<html><body><div align=\"right\">" + date + "<br>" + time + "</div></body></html>";
        } else {
            return "<html><body><div align=\"right\">" + date + "&nbsp;&nbsp;" + time + "</div></body></html>";
        }
    }

    /**
     * Calculates the best size of the clock label.
     *
     * @param availableWidth  Available width in container.
     * @param availableHeight Available height in container.
     * @return Best size for the label in relation to min and max size configured in ClockBasics.
     */
    public int computeBestSize(int availableWidth, int availableHeight) {
        Font baseFont = getFont();
        int bestSize = ClockBasics.MIN_CLOCK_SIZE;
        for (int size = ClockBasics.MIN_CLOCK_SIZE; size <= ClockBasics.MAX_CLOCK_SIZE; size++) {
            Font f = baseFont.deriveFont(baseFont.getStyle(), (float) size);
            Dimension singleLine = measure(f, false);
            Dimension twoLines = measure(f, true);
            boolean fitsSingle = singleLine.width <= availableWidth && singleLine.height <= availableHeight;
            boolean fitsTwo = twoLines.width <= availableWidth && twoLines.height <= availableHeight;
            if (!fitsSingle && !fitsTwo) break;
            bestSize = size;
        }
        return bestSize;
    }

    /**
     * Applies the calculated layout size to the label. If the width allows it, the label will be displayed in a
     * single line. Else it will be split in two lines.
     *
     * @param availableWidth  Available width in container.
     * @param availableHeight Available height in container.
     * @param forcedSize Forced font size.
     */
    public void applyLayout(int availableWidth, int availableHeight, int forcedSize) {
        Font baseFont = getFont();
        Font f = baseFont.deriveFont(baseFont.getStyle(), (float) forcedSize);
        Dimension single = measure(f, false);
        useLineBreak = !(single.width <= availableWidth && single.height <= availableHeight);
        if (!f.equals(getFont())) {
            setFont(f);
        }
        setText(getCurrentTimeStamp());
    }

    /**
     * Measures the size of the date and time strings based on the given font.
     * @param font Font to use for calculation.
     * @param lineBreak True if line break should be used.
     * @return Dimension of the maximum space the label occupies.
     */
    private Dimension measure(Font font, boolean lineBreak) {
        FontMetrics fm;
        if (g != null) {
            fm = g.getFontMetrics(font);
        } else {
            fm = new Canvas().getFontMetrics(font);
        }
        int dateWidth = fm.stringWidth(" 9999-99-99 ");
        int timeWidth = fm.stringWidth(" 99:99:99 ");
        int fontHeight = fm.getHeight();
        if (lineBreak) {
            return new Dimension(Math.max(dateWidth, timeWidth), fontHeight * 2);
        } else {
            return new Dimension(dateWidth + timeWidth, fontHeight);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1, 1);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(1, 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
    }
    
    public void setZone(ZoneId zone) {
		this.zone = zone;
	}
}