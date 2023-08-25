package de.emir.epd.routemanager.graphics;

import java.awt.Shape;

import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;

public interface IGraphic<T extends IGraphic> extends Shape {
    T paint(BufferingGraphics2D g, IDrawContext c);
}
