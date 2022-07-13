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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unmarshalling -> XML -> Shapes
 */
public class JAXBPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> read(String filename) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("org.painting.painter.xml.saver.jaxb");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            //zeko.vfs -> Shapes -> getShape();
            Shapes shapes = (Shapes) unmarshaller.unmarshal(new FileReader(filename));
            return shapes.getShapes().stream().map(this::toPaintShape).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    private PaintShape toPaintShape(Shape shape){
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
        return paintShape;
    }
}
