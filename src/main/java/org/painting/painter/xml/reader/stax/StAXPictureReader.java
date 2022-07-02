package org.painting.painter.xml.reader.stax;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.shape.RectangleShape;
import org.painting.painter.xml.reader.XMLPictureReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.awt.Color;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * MOD ČITANJA:
 * <li>{@link XMLInputFactory}</li>
 * <li>{@link XMLStreamReader}</li>
 */
public class StAXPictureReader implements XMLPictureReader {
    @Override
    public List<PaintShape> read(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try {
            //kreirati objekat koji će se povezati na nekakav input(XML fajl)
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            //Kursor
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileReader(filename));
            boolean openX = false;
            String x = null;
            boolean openY = false;
            String y = null;
            boolean openColor = false;
            String color = null;
            boolean openType = false;
            String type = null;
            while (xmlStreamReader.hasNext()) {
                int typeOfElement = xmlStreamReader.next();
                switch (typeOfElement) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("x".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                            openX = true;
                        } else if ("y".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                            openY = true;
                        } else if ("color".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                            openColor = true;
                        } else if ("type".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                            openType = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (openX) {
                            x = xmlStreamReader.getText();
                        } else if (openY) {
                            y = xmlStreamReader.getText();
                        } else if (openColor) {
                            color = xmlStreamReader.getText();
                        } else if (openType) {
                            type = xmlStreamReader.getText();
                        }
                        break;
                        case XMLStreamConstants.END_ELEMENT:
                            if ("x".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                                openX = false;
                            } else if ("y".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                                openY = false;
                            } else if ("color".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                                openColor = false;
                            } else if ("type".equalsIgnoreCase(xmlStreamReader.getName().toString())) {
                                openType = false;
                            }else if("shape".equalsIgnoreCase(xmlStreamReader.getName().toString())){
                                Color colorAwt = "plava".equalsIgnoreCase(color)?Color.BLUE:Color.RED;
                                PaintShape paintShape;
                                if("krug".equalsIgnoreCase(type)){
                                    paintShape = new EllipseShape(Integer.parseInt(x), Integer.parseInt(y), colorAwt);
                                }else{
                                    paintShape = new RectangleShape(Integer.parseInt(x), Integer.parseInt(y), colorAwt);
                                }
                                paintShapes.add(paintShape);
                            }
                            break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return paintShapes;
    }
}
