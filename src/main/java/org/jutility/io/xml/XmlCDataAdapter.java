/**
 * 
 */
package org.jutility.io.xml;


import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * @author Peter J. Radics
 * 
 */
public class XmlCDataAdapter
        extends XmlAdapter<String, String> {



    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public String unmarshal(String value)
            throws Exception {

        if (XmlCData.isCDATA(value)) {

            return XmlCData.fromCDATA(value);
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public String marshal(String value)
            throws Exception {

        if (!XmlCData.isCDATA(value)) {

            return XmlCData.toCDATA(value);
        }
        return value;
    }

}
