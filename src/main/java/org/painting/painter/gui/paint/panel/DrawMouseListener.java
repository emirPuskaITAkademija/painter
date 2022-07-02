package org.painting.painter.gui.paint.panel;

import org.painting.painter.gui.PaintWindow;
import org.painting.painter.gui.paint.settings.PaintSettingsPanel;
import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.shape.RectangleShape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class DrawMouseListener extends MouseAdapter {

    //Depend upon abstraction not upon concrete implementation
    private final Consumer<PaintShape> paintShapeConsumer;

    public DrawMouseListener(Consumer<PaintShape> paintShapeConsumer){
        this.paintShapeConsumer = paintShapeConsumer;
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        onMouseActionListener(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        onMouseActionListener(mouseEvent);
    }

    private void onMouseActionListener(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        PaintSettingsPanel settingsPanel = PaintWindow.instance().getPaintSettingsPanel();
        boolean isCircleSelected = settingsPanel.getCircleRadioButton().isSelected();
        boolean isBlueColorSelected = settingsPanel.getBlueRadioButton().isSelected();
        Color color = isBlueColorSelected ? Color.BLUE : Color.RED;
        PaintShape paintShape = isCircleSelected ?
                new EllipseShape(x, y, color) : new RectangleShape(x, y, color);

        paintShapeConsumer.accept(paintShape);
    }
}
