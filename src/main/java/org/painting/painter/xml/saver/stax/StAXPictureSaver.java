package org.painting.painter.xml.saver.stax;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.saver.XMLPictureSaver;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.awt.Color;
import java.io.FileWriter;
import java.util.List;

/**
 * <li>{@link XMLOutputFactory}</li>
 * <li>{@link XMLStreamWriter}</li>
 */
public class StAXPictureSaver implements XMLPictureSaver {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(filename));
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("shapes");
            for (PaintShape paintShape : paintShapes) {
                streamWriter.writeStartElement("shape");

                streamWriter.writeStartElement("x");
                streamWriter.writeCharacters(paintShape.getX() + "");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("y");
                streamWriter.writeCharacters(paintShape.getY() + "");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("color");
                streamWriter.writeCharacters((paintShape.getColor().equals(Color.BLUE)) ? "Plava" : "Crvena");
                streamWriter.writeEndElement();
                streamWriter.writeStartElement("type");
                streamWriter.writeCharacters((paintShape instanceof EllipseShape) ? "Krug" : "Kvadrat");
                streamWriter.writeEndElement();

                streamWriter.writeEndElement();
            }
            streamWriter.writeEndElement();//shapes
            streamWriter.writeEndDocument();
            streamWriter.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
