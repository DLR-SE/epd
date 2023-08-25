package de.emir.epd.mapview.views.map;

import de.emir.epd.mapview.views.map.drawcalls.*;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BufferingGraphics2D {

	private List<IDrawCall> calls = new ArrayList<>();

	private Graphics2D graphic;
	
	public BufferingGraphics2D() {
		BufferedImage b = new BufferedImage(1, 1, ColorSpace.TYPE_RGB);
		graphic = b.createGraphics();
	}

	protected void paintTo(Graphics2D g) {
		for (IDrawCall call : calls) {
			call.run(g);
		}
	}

	public void draw(Shape s) {
		calls.add(new DrawShape(s));
	}

	public AffineTransform getTransform() {
		return graphic.getTransform();
	}

	public void setTransform(AffineTransform t) {
		calls.add(new SetTransform(t));
		graphic.setTransform(t);
	}

	public void translate(double tx, double ty) {
		calls.add(new Translate(tx, ty));
		graphic.translate(tx, ty);
	}

	public void setColor(Color c) {

		calls.add(new SetColor(c));
		graphic.setColor(c);

	}

	public void scale(double sx, double sy) {

		calls.add(new Scale(sx, sy));
		graphic.scale(sx, sy);
	}

	public void drawString(String s, float x, float y) {
		calls.add(new DrawString(s, x, y));

	}

	public FontMetrics getFontMetrics(Font font) {
		return graphic.getFontMetrics(font);
	}

	public void setStroke(Stroke s) {
		calls.add(new SetStroke(s));
	}
	
	public void setPaint(Paint p) {
		calls.add(new SetPaint(p));
	}

	public void rotate(double theta) {
		calls.add(new Rotate(theta));
		graphic.rotate(theta);
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		calls.add(new DrawLine(x1, y1, x2, y2));
	}

	public void drawPolygon(Polygon p) {
		calls.add(new DrawPolygon(p));
	}

	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		calls.add(new DrawArc(x, y, width, height, startAngle, arcAngle));
	}

	public FontMetrics getFontMetrics() {
		return graphic.getFontMetrics();
	}

	public void fillOval(int x, int y, int width, int height) {
		calls.add(new FillOval(x, y, width, height));
	}
	
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		calls.add(new FillRoundRectangle(x, y, width, height, arcWidth, arcHeight));
	}

	public void drawOval(int x, int y, int width, int height) {
		calls.add(new DrawOval(x, y, width, height));
	}

	public void fillPolygon(Polygon p) {
		calls.add(new FillPolygon(p));
	}

	public void fill(Shape s) {
		calls.add(new FillShape(s));
	}

	public void setBackground(Color c) {
		calls.add(new SetBackground(c));
	}

	public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		calls.add(new DrawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2));
	}

	public Color getColor() {
		return graphic.getColor();
	}

	public void setFont(Font font) {
		calls.add(new SetFont(font));
	}

	public Graphics getGraphic() {
		return graphic;
	}
	
	public void dispose() {
		if(graphic != null) {
			graphic.dispose();
		}
	}

	public void drawImage(Image image, int x, int y) {
		calls.add(new DrawImageXY(image, x, y));
		
	}

	public void drawRect(int x, int y, int width, int height) {
		calls.add(new DrawRect(x, y, width, height));
		
	}

}
