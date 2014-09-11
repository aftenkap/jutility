package org.jutility.io.xml;


import javafx.scene.paint.Color;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * The {@code XmlColorTypeAdapter} class provides an adapter for XML
 * serialization of {@link Color Colors}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class XmlColorTypeAdapter
        extends XmlAdapter<String, Color> {

    @Override
    public String marshal(Color color)
            throws Exception {

        if (color != null) {
            return color.toString();
        }
        return null;
    }

    @Override
    public Color unmarshal(String serializedColor)
            throws Exception {

        if (serializedColor != null) {
            Color color = Color.web(serializedColor);
            return color;
        }
        return null;
    }

}
