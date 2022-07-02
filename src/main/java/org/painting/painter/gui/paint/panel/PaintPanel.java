package org.painting.painter.gui.paint.panel;

import org.painting.painter.shape.PaintShape;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel{

    private List<PaintShape> paintShapes = new ArrayList<>();

    public PaintPanel() {
        setBackground(Color.WHITE);
        DrawMouseListener drawMouseListener = new DrawMouseListener(this::acceptPaintShape);
        addMouseListener(drawMouseListener);//MouseListener
        addMouseMotionListener(drawMouseListener);//MouseMotionListener
    }

    public List<PaintShape> getPaintShapes() {
        return paintShapes;
    }

    public void setPaintShapes(List<PaintShape> paintShapes) {
        this.paintShapes = paintShapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphic2D
        Graphics2D graphics2D = (Graphics2D) g;
        for (PaintShape paintShape : paintShapes) {
            graphics2D.setColor(paintShape.getColor());
            graphics2D.fill(paintShape.createShape());
        }
    }

    public void acceptPaintShape(PaintShape paintShape) {
        paintShapes.add(paintShape);
        repaint();
    }
}
