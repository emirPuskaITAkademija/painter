package org.painting.painter.xml.saver.jaxb;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.saver.XMLPictureSaver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.awt.Color;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.List;
/**
 * ORM -> Privilege, User, ..@Entity
 *
 * OXM -> Shapes, Shape -> ObjectFactory
 *
 * zeko.xml   -> picture.xsd
 *
 * -> OXM -> Shapes, Shape ObjectFactory(@XmlRegistry)
 *
 */
public class JAXBPictureSaver implements XMLPictureSaver {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try{
            ObjectFactory objectFactory = new ObjectFactory();
            //root -> shapes
            Shapes shapes = objectFactory.createShapes();
            paintShapes
                    .stream()
                    .map(paintShape->toShape(objectFactory, paintShape))
                    .forEach(shapes.getShapes()::add);
            JAXBContext jaxbContext = JAXBContext.newInstance("org.painting.painter.xml.saver.jaxb");
            //convertor shapes objekta u XML
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(shapes, new FileWriter(filename));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private Shape toShape(ObjectFactory objectFactory, PaintShape paintShape){
        Shape shape = objectFactory.createShape();
        shape.setX(BigInteger.valueOf(paintShape.getX()));
        shape.setY(BigInteger.valueOf(paintShape.getY()));
        shape.setColor(paintShape.getColor().equals(Color.BLUE)?"Plava":"Crvena");
        shape.setType((paintShape instanceof EllipseShape) ? "Krug":"Kvadrat");
        return shape;
    }
}
