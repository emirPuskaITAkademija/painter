package org.painting.painter.xml.saver.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "shape"
})
@XmlRootElement(name = "shapes")
public class Shapes {
    @XmlElement(required = true)
    private List<Shape> shape;

    public List<Shape> getShapes() {
        if(shape == null){
            shape = new ArrayList<>();
        }
        return shape;
    }
}
