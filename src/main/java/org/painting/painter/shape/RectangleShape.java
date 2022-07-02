package org.painting.painter.shape;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Kvadratića -> Square -> RectangleShape a = b
 */
public class RectangleShape extends PaintShape{
    public RectangleShape(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public Shape createShape() {
        Rectangle2D.Double rectangle2D = new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
        return rectangle2D;
    }
}
