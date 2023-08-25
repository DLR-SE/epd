package de.emir.epd.mapview.views.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class CustomGraphics2D {

	private AffineTransform transform = new AffineTransform();

	private Dimension size = new Dimension(1000, 1000);

	BufferedImage[][] buffers = new BufferedImage[10][10];
	Graphics2D[][] gMatrix = new Graphics2D[10][10];
	boolean[][] drawnBuffers = new boolean[10][10];

	int bufferWidth = 250;
	int bufferHeight = 250;

	private Color lastSetColor;

	private Graphics2D lastCreatedGraphics;

	private Stroke lastSetStroke;

	private Color lastSetBackground;

	public void setSize(Dimension size) {
		this.size = size;
		clearAll();
	}

	public void clearAll() {
		for (int x = 0; x < gMatrix.length; x++) {
			for (int y = 0; y < gMatrix[0].length; y++) {
				if (gMatrix[x][y] != null) {
					gMatrix[x][y].dispose();
				}
			}
		}

		int x = size.width / bufferWidth + 1;
		int y = size.height / bufferHeight + 1;

		buffers = new BufferedImage[x][y];
		gMatrix = new Graphics2D[x][y];
		drawnBuffers = new boolean[x][y];

		lastSetColor = null;
		lastSetBackground = null;
		lastSetStroke = null;
	}

	public void paintTo(Graphics2D g) {

		for (int x = 0; x < buffers.length; x++) {
			for (int y = 0; y < buffers[0].length; y++) {

				if (drawnBuffers[x][y] == true) {
					g.drawImage(buffers[x][y], x * bufferWidth, y * bufferHeight, null);
				}
			}
		}

	}

	public void draw(Shape s) {

		Rectangle2D b = s.getBounds2D();
		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				draw(xi, yi, s);
			}
		}
	}

	private void draw(int xi, int yi, Shape s) {

		checkExistance(xi, yi);

		gMatrix[xi][yi].draw(s);
		drawnBuffers[xi][yi] = true;

	}

	private void checkExistance(int xi, int yi) {
		if (gMatrix[xi][yi] != null) {
			return;
		}

		buffers[xi][yi] = createBufferedImage();
		gMatrix[xi][yi] = buffers[xi][yi].createGraphics();
		gMatrix[xi][yi].translate(-xi * bufferWidth, -yi * bufferHeight);
		gMatrix[xi][yi].setClip(xi * bufferWidth, yi * bufferHeight, bufferWidth, bufferHeight);
		gMatrix[xi][yi].setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		gMatrix[xi][yi].drawRect(xi * bufferWidth, yi * bufferHeight, bufferWidth - 1, bufferHeight - 1);
		
		if (lastSetColor != null) {
			gMatrix[xi][yi].setColor(lastSetColor);
		}

		if (lastSetBackground != null) {
			gMatrix[xi][yi].setBackground(lastSetBackground);
		}

		if (lastSetStroke != null) {
			gMatrix[xi][yi].setStroke(lastSetStroke);
		}

		lastCreatedGraphics = gMatrix[xi][yi];

	}

	/**
	 * Creates a buffer image with the optimal settings for the running system
	 * 
	 * @return
	 */
	private BufferedImage createBufferedImage() {

		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		return config.createCompatibleImage(size.width, size.height, Transparency.TRANSLUCENT);

	}

	public void resetDrawnBuffersState() {
		for (int x = 0; x < drawnBuffers.length; x++) {
			for (int y = 0; y < drawnBuffers[0].length; y++) {
				drawnBuffers[x][y] = false;
			}
		}
	}

	public AffineTransform getTransform() {
		return new AffineTransform(transform);
	}

	public void setTransform(AffineTransform t) {
		this.transform = t;
	}

	public void translate(double tx, double ty) {
		this.transform.translate(tx, ty);
	}

	public void setColor(Color c) {

		lastSetColor = c;

		for (int xi = 0; xi < gMatrix.length; xi++) {
			for (int yi = 0; yi < gMatrix[0].length; yi++) {
				if (gMatrix[xi][yi] != null) {
					gMatrix[xi][yi].setColor(c);
				}
			}
		}

	}

	public void scale(double sx, double sy) {
		this.transform.scale(sx, sy);

	}

	public void drawString(String s, int x, int y) {

		if (lastCreatedGraphics == null) {
			checkExistance(0, 0);
		}

		int stringWidth = lastCreatedGraphics.getFontMetrics().stringWidth(s);
		int stringHeight = lastCreatedGraphics.getFontMetrics().getHeight();

		Rectangle2D b = new Rectangle2D.Double(x, y, stringWidth, stringHeight);

		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawString(xi, yi, s, x, y);
			}
		}
	}

	private void drawString(int xi, int yi, String s, int x, int y) {

		checkExistance(xi, yi);

		gMatrix[xi][yi].drawString(s, x, y);
		drawnBuffers[xi][yi] = true;
	}

	public class Range {

		int startX;
		int startY;
		int endX;
		int endY;

		public Range(Rectangle2D bounds) {
			startX = (int) bounds.getX() / bufferWidth;
			endX = (int) (bounds.getX() + bounds.getWidth()) / bufferWidth;
			startY = (int) bounds.getY() / bufferHeight;
			endY = (int) (bounds.getY() + bounds.getHeight()) / bufferHeight;

			if (startX < 0) {
				startX = 0;
			}
			if (startY < 0) {
				startY = 0;
			}

			if (endX >= gMatrix.length) {
				endX = gMatrix.length - 1;
			}
			if (endY >= gMatrix[0].length) {
				endY = gMatrix[0].length - 1;
			}
		}
	}

	public FontMetrics getFontMetrics(Font font) {
		if (lastCreatedGraphics == null) {
			checkExistance(0, 0);
		}

		return lastCreatedGraphics.getFontMetrics(font);
	}

	public void setStroke(Stroke s) {
		lastSetStroke = s;

		for (int xi = 0; xi < gMatrix.length; xi++) {
			for (int yi = 0; yi < gMatrix[0].length; yi++) {
				if (gMatrix[xi][yi] != null) {
					gMatrix[xi][yi].setStroke(s);
				}
			}
		}

	}

	public void rotate(double theta) {
		transform.rotate(theta);

	}

	public void drawLine(int x1, int y1, int x2, int y2) {

		int bx = x1 < x2 ? x1 : x2;
		int by = y1 < y2 ? y1 : y2;

		int bWidth = Math.abs(x2 - x1);
		int bHeight = Math.abs(y2 - y1);

		Rectangle2D b = new Rectangle2D.Double(bx, by, bWidth, bHeight);

		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawLine(xi, yi, x1, y1, x2, y2);
			}
		}
	}

	private void drawLine(int xi, int yi, int x1, int y1, int x2, int y2) {
		checkExistance(xi, yi);
		gMatrix[xi][yi].drawLine(x1, y1, x2, y2);
		drawnBuffers[xi][yi] = true;
	}

	public void drawPolygon(Polygon p) {

		Rectangle2D b = p.getBounds2D();
		Range r = new Range(b);

		for (int x = r.startX; x <= r.endX; x++) {
			for (int y = r.startY; y <= r.endY; y++) {
				draw(x, y, p);
			}
		}

	}

	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

		Rectangle2D b = new Rectangle2D.Double(x, y, width, height);

		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawArc(xi, yi, x, y, width, height, startAngle, arcAngle);
			}
		}

	}

	private void drawArc(int xi, int yi, int x, int y, int width, int height, int startAngle, int arcAngle) {
		checkExistance(xi, yi);
		gMatrix[xi][yi].drawArc(x, y, width, height, startAngle, arcAngle);
		drawnBuffers[xi][yi] = true;
	}

	public FontMetrics getFontMetrics() {
		if (lastCreatedGraphics == null) {
			checkExistance(0, 0);
		}

		return lastCreatedGraphics.getFontMetrics();
	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

		Rectangle2D b = new Rectangle2D.Double(x, y, width, height);

		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawRoundRect(xi, yi, x, y, width, height, arcWidth, arcHeight);
			}
		}

	}

	private void drawRoundRect(int xi, int yi, int x, int y, int width, int height, int arcWidth, int arcHeight) {
		checkExistance(xi, yi);
		gMatrix[xi][yi].drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		drawnBuffers[xi][yi] = true;
	}

	public void drawOval(int x, int y, int width, int height) {

		Rectangle2D b = new Rectangle2D.Double(x, y, width, height);

		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawOval(xi, yi, x, y, width, height);
			}
		}

	}

	private void drawOval(int xi, int yi, int x, int y, int width, int height) {
		checkExistance(xi, yi);
		gMatrix[xi][yi].drawOval(x, y, width, height);
		drawnBuffers[xi][yi] = true;
	}

	public void fillPolygon(Polygon p) {
		Rectangle2D b = p.getBounds2D();
		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				fill(xi, yi, p);
			}
		}

	}

	private void fill(int xi, int yi, Shape s) {
		checkExistance(xi, yi);

		gMatrix[xi][yi].fill(s);
		drawnBuffers[xi][yi] = true;

	}

	public void fill(Shape s) {

		Rectangle2D b = s.getBounds2D();
		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				fill(xi, yi, s);
			}
		}
	}

	public void setBackground(Color c) {

		lastSetBackground = c;

		for (int xi = 0; xi < gMatrix.length; xi++) {
			for (int yi = 0; yi < gMatrix[0].length; yi++) {
				if (gMatrix[xi][yi] != null) {
					gMatrix[xi][yi].setBackground(c);
				}
			}
		}

	}

	public void drawImage(Image image, int sx1, int sy1, int sx2, int sy2, int dx1, int dy1, int dx2, int dy2,
			ImageObserver observer) {
		
		int bx = dx1 < dx2 ? dx1 : dx2;
		int by = dy1 < dy2 ? dy1 : dy2;

		int bWidth = Math.abs(dx2 - dx1);
		int bHeight = Math.abs(dy2 - dy1);
		
		Rectangle2D b = new Rectangle2D.Double(bx, by, bWidth, bHeight);
		Range r = new Range(b);

		for (int xi = r.startX; xi <= r.endX; xi++) {
			for (int yi = r.startY; yi <= r.endY; yi++) {
				drawImage(xi, yi, image, sx1, sy1, sx2, sy2, dx1, dy1, dx2, dy2, observer);
			}
		}
	}

	private void drawImage(int xi, int yi, Image img, int sx1, int sy1, int sx2, int sy2, int dx1, int dy1, int dx2,
			int dy2, ImageObserver observer) {
		checkExistance(xi, yi);
		gMatrix[xi][yi].drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		drawnBuffers[xi][yi] = true;
	}

}
