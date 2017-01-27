//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.27 at 06:54:15 AM CET 
//


package com.aboni.nmea.router.conf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UdpAgent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UdpAgent">
 *   &lt;complexContent>
 *     &lt;extension base="{}AgentBase">
 *       &lt;sequence>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" fixed="UDP" />
 *       &lt;attribute name="port" type="{http://www.w3.org/2001/XMLSchema}int" default="4800" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UdpAgent", propOrder = {
    "to"
})
public class UdpAgent
    extends AgentBase
{

    @XmlElement(required = true)
    protected List<String> to;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "port")
    protected Integer port;

    /**
     * Gets the value of the to property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the to property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTo() {
        if (to == null) {
            to = new ArrayList<String>();
        }
        return this.to;
    }

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
            return "UDP";
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
     * Gets the value of the port property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPort() {
        if (port == null) {
            return  4800;
        } else {
            return port;
        }
    }

    /**
     * Sets the value of the port property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPort(Integer value) {
        this.port = value;
    }

}
