package org.painting.painter.xml.saver.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "x", "y", "color", "type"
})
@XmlRootElement(name = "shape")
public class Shape {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private BigInteger x;

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private BigInteger y;

    @XmlElement(required = true)
    private String color;

    @XmlElement(required = true)
    private String type;


    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
