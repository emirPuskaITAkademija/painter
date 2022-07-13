package org.painting.painter.xml.reader.jaxb;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.shape.RectangleShape;
import org.painting.painter.xml.reader.XMLPictureReader;
import org.painting.painter.xml.saver.jaxb.Shape;
import org.painting.painter.xml.saver.jaxb.Shapes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Unmarshalling -> XML -> Shapes
 */
public class JAXBPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> read(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("org.painting.painter.xml.saver.jaxb");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Shapes shapes = (Shapes) unmarshaller.unmarshal(new FileReader(filename));
            for (Shape shape : shapes.getShape()) {
                PaintShape paintShape;
                if (shape.getType().equalsIgnoreCase("Krug")) {
                    paintShape = new EllipseShape(
                            shape.getX().intValue(),
                            shape.getY().intValue(),
                            shape.getColor().equalsIgnoreCase("Plava") ? Color.BLUE : Color.RED);
                } else {
                    paintShape = new RectangleShape(
                            shape.getX().intValue(),
                            shape.getY().intValue(),
                            shape.getColor().equalsIgnoreCase("Plava") ? Color.BLUE : Color.RED
                    );
                }
                paintShapes.add(paintShape);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return paintShapes;
    }
}
