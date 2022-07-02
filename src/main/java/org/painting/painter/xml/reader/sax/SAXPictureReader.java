package org.painting.painter.xml.reader.sax;

import org.painting.painter.gui.PaintWindow;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.reader.XMLPictureReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SAX parser pročitamo sliku medo.vfs i da kao output dobijemo
 * List<PaintShape> paintShapes.
 * </p>
 *
 * SAX parser
 * <li>{@link DefaultHandler}</li>
 * <li>{@link SAXParserFactory}</li>
 * <li>{@link SAXParser}</li>
 */
public class SAXPictureReader implements XMLPictureReader {

    @Override
    public List<PaintShape> read(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(filename, new PictureHandler(paintShapes));
        }catch (ParserConfigurationException|SAXException| IOException e){
            System.err.println(e.getMessage());
        }
        return paintShapes;
    }
}
