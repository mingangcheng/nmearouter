//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.23 at 09:04:33 AM CET 
//


package com.aboni.nmea.router.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SerialAgent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SerialAgent">
 *   &lt;complexContent>
 *     &lt;extension base="{}AgentBase">
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" fixed="Serial" />
 *       &lt;attribute name="device" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bps" type="{http://www.w3.org/2001/XMLSchema}int" default="4800" />
 *       &lt;attribute name="inout" use="required" type="{}InOut" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SerialAgent")
public class SerialAgent
    extends AgentBase
{

    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "device", required = true)
    protected String device;
    @XmlAttribute(name = "bps")
    protected Integer bps;
    @XmlAttribute(name = "inout", required = true)
    protected InOut inout;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "Serial";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevice(String value) {
        this.device = value;
    }

    /**
     * Gets the value of the bps property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getBps() {
        if (bps == null) {
            return  4800;
        } else {
            return bps;
        }
    }

    /**
     * Sets the value of the bps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBps(Integer value) {
        this.bps = value;
    }

    /**
     * Gets the value of the inout property.
     * 
     * @return
     *     possible object is
     *     {@link InOut }
     *     
     */
    public InOut getInout() {
        return inout;
    }

    /**
     * Sets the value of the inout property.
     * 
     * @param value
     *     allowed object is
     *     {@link InOut }
     *     
     */
    public void setInout(InOut value) {
        this.inout = value;
    }

}
