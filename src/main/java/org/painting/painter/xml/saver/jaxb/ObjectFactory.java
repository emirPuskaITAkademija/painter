package org.painting.painter.xml.saver.jaxb;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }


    public Shape createShape() {
        return new Shape();
    }


    public Shapes createShapes() {
        return new Shapes();
    }
}
