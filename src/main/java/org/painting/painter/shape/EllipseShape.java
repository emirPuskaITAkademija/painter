package org.painting.painter.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipseShape extends PaintShape{
    public EllipseShape(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        Ellipse2D.Double ellipse2D = new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
        return ellipse2D;
    }
}
