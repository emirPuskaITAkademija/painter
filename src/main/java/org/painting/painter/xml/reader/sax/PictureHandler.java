package org.painting.painter.xml.reader.sax;

import org.painting.painter.gui.PaintWindow;
import org.painting.painter.gui.paint.panel.PaintPanel;
import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.shape.RectangleShape;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//PaintShape -> listu paint shapes -> paintPanel
// x, y, Color, Ellipse ili Rectangle
public class PictureHandler extends DefaultHandler {

    private final List<PaintShape> paintShapes;

    private int x;
    private boolean xOpen = false;
    private int y;
    private boolean yOpen = false;
    private String color;
    private boolean colorOpen = false;
    private String shapeType;
    private boolean shapeTypeOpen = false;

    public PictureHandler(List<PaintShape> paintShapes){
        this.paintShapes = paintShapes;
    }

    //<color>
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("x".equals(qName)){
            xOpen = true;
        }else if("y".equals(qName)){
            yOpen = true;
        }else if("color".equals(qName)){
            colorOpen = true;
        }else if("type".equals(qName)){
            shapeTypeOpen = true;
        }
    }

    //Plava ili Crvena
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(xOpen){
            x = Integer.parseInt(new String(ch, start, length));
            xOpen = false;
        }else if(yOpen){
            y = Integer.parseInt(new String(ch, start, length));
            yOpen = false;
        }else if(colorOpen){
            color = new String(ch, start, length);
            colorOpen = false;
        }else if(shapeTypeOpen){
            shapeType = new String(ch, start, length);
            shapeTypeOpen = false;
        }
    }

    //</color>
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("shape".equals(qName)){
            Color colorAwt = "PLAVA".equalsIgnoreCase(color)? Color.BLUE:Color.RED;
            PaintShape paintShape;
            if(shapeType.equalsIgnoreCase("KRUG")){
                paintShape = new EllipseShape(x, y, colorAwt);
            }else{
                paintShape = new RectangleShape(x, y, colorAwt);
            }
            paintShapes.add(paintShape);
        }
    }
}
