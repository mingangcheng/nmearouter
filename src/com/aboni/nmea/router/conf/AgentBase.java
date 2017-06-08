//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.02 at 06:19:00 AM CEST 
//


package com.aboni.nmea.router.conf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AgentBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AgentBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterSource" type="{}FilterSet" minOccurs="0"/>
 *         &lt;element name="filterTarget" type="{}FilterSet" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgentBase", propOrder = {
    "qos",
    "filterSource",
    "filterTarget"
})
@XmlSeeAlso({
    SimulatorAgent.class,
    GPXPlayerAgent.class,
    SensorAgent.class,
    JSONAgent.class,
    TcpAgent.class,
    MWDAgent.class,
    SerialAgent.class,
    UdpAgent.class,
    TrackAgent.class,
    ConsoleAgent.class,
    MeteoAgent.class
})
public class AgentBase {

    protected String qos;
    protected FilterSet filterSource;
    protected FilterSet filterTarget;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "active")
    protected Boolean active;

    /**
     * Gets the value of the qos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQos() {
        return qos;
    }

    /**
     * Sets the value of the qos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQos(String value) {
        this.qos = value;
    }

    /**
     * Gets the value of the filterSource property.
     * 
     * @return
     *     possible object is
     *     {@link FilterSet }
     *     
     */
    public FilterSet getFilterSource() {
        return filterSource;
    }

    /**
     * Sets the value of the filterSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterSet }
     *     
     */
    public void setFilterSource(FilterSet value) {
        this.filterSource = value;
    }

    /**
     * Gets the value of the filterTarget property.
     * 
     * @return
     *     possible object is
     *     {@link FilterSet }
     *     
     */
    public FilterSet getFilterTarget() {
        return filterTarget;
    }

    /**
     * Sets the value of the filterTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterSet }
     *     
     */
    public void setFilterTarget(FilterSet value) {
        this.filterTarget = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isActive() {
        if (active == null) {
            return false;
        } else {
            return active;
        }
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

}
