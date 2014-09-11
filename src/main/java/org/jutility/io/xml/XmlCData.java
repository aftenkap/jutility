/**
 * 
 */
package org.jutility.io.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * @author Peter J. Radics
 * 
 */
@XmlRootElement()
@XmlType(name = "CDATA")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlCData {

    /**
     * The CDATA prefix.
     */
    public static String CDATA_PREFIX = "<![CDATA[";
    /**
     * The CDATA suffix.
     */
    public static String CDATA_SUFFIX = "]]>";


    @XmlJavaTypeAdapter(value = XmlCDataAdapter.class)
    @XmlValue
    private String       value;

    /**
     * Returns the content of the CDATA section.
     * 
     * @return the content of the CDATA section.
     */
    public String getValue() {

        return this.value;
    }


    /**
     * Sets the content of the CDATA section.
     * 
     * @param value
     *            the content of the CDATA section.
     */
    public void setValue(String value) {

        this.value = value;
    }


    /**
     * Creates a new instance of the {@link XmlCData} class.
     */
    public XmlCData() {

        this(null);
    }

    /**
     * Creates a new instance of the {@link XmlCData} class with the provided
     * value.
     * 
     * @param value
     *            the content of the CDATA section.
     */
    public XmlCData(String value) {

        this.value = value;
    }

    /**
     * Determines if the string is valid CDATA.
     * 
     * @param string
     *            the string to check.
     * @return {@code true}, if the string is valid CDATA; {@code false}
     *         otherwise.
     */
    public static boolean isCDATA(String string) {

        if (string == null) {

            return false;
        }

        boolean startsWith = string.startsWith(CDATA_PREFIX);
        boolean endsWith = string.endsWith(CDATA_SUFFIX);
        boolean oneStart = string.lastIndexOf(CDATA_PREFIX) == 0;
        boolean oneEnd = string.indexOf(CDATA_SUFFIX) == (string.length() - CDATA_SUFFIX
                .length());

        return (startsWith && endsWith && oneStart && oneEnd);
    }

    /**
     * Converts a string into a valid CDATA string.
     * 
     * @param string
     *            the string to convert.
     * @return a valid CDATA string.
     */
    public static String toCDATA(String string) {

        if (isCDATA(string)) {

            return string;
        }

        String cdata = CDATA_PREFIX + string + CDATA_SUFFIX;

        if (isCDATA(cdata)) {

            return cdata;
        }

        throw new IllegalArgumentException("String \"" + string
                + "\" contains invalid characters!");
    }

    /**
     * Converts a CDATA string into a string.
     * 
     * @param cdata
     *            the CDATA string.
     * @return the content of the CDATA section.
     */
    public static String fromCDATA(String cdata) {

        if (isCDATA(cdata)) {

            return cdata.substring(CDATA_PREFIX.length(), cdata.length()
                    - CDATA_SUFFIX.length());
        }
        else {

            throw new IllegalArgumentException("String \"" + cdata
                    + "\" is not valid CDATA!");
        }

    }

}
