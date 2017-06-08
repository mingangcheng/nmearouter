//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.02 at 06:19:00 AM CEST 
//


package com.aboni.nmea.router.conf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Router complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Router">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Log" type="{}Log"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="SerialAgent" type="{}SerialAgent"/>
 *           &lt;element name="JSONAgent" type="{}JSONAgent"/>
 *           &lt;element name="TcpAgent" type="{}TcpAgent"/>
 *           &lt;element name="UdpAgent" type="{}UdpAgent"/>
 *           &lt;element name="ConsoleAgent" type="{}ConsoleAgent"/>
 *           &lt;element name="TrackAgent" type="{}TrackAgent"/>
 *           &lt;element name="SensorAgent" type="{}SensorAgent"/>
 *           &lt;element name="SimulatorAgent" type="{}SimulatorAgent"/>
 *           &lt;element name="MeteoAgent" type="{}MeteoAgent"/>
 *           &lt;element name="MWDAgent" type="{}MWDAgent"/>
 *           &lt;element name="GPXPlayerAgent" type="{}GPXPlayerAgent"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Router", propOrder = {
    "log",
    "serialAgentOrJSONAgentOrTcpAgent"
})
public class Router {

    @XmlElement(name = "Log", required = true)
    protected Log log;
    @XmlElements({
        @XmlElement(name = "SerialAgent", type = SerialAgent.class),
        @XmlElement(name = "JSONAgent", type = JSONAgent.class),
        @XmlElement(name = "TcpAgent", type = TcpAgent.class),
        @XmlElement(name = "UdpAgent", type = UdpAgent.class),
        @XmlElement(name = "ConsoleAgent", type = ConsoleAgent.class),
        @XmlElement(name = "TrackAgent", type = TrackAgent.class),
        @XmlElement(name = "SensorAgent", type = SensorAgent.class),
        @XmlElement(name = "SimulatorAgent", type = SimulatorAgent.class),
        @XmlElement(name = "MeteoAgent", type = MeteoAgent.class),
        @XmlElement(name = "MWDAgent", type = MWDAgent.class),
        @XmlElement(name = "GPXPlayerAgent", type = GPXPlayerAgent.class)
    })
    protected List<AgentBase> serialAgentOrJSONAgentOrTcpAgent;

    /**
     * Gets the value of the log property.
     * 
     * @return
     *     possible object is
     *     {@link Log }
     *     
     */
    public Log getLog() {
        return log;
    }

    /**
     * Sets the value of the log property.
     * 
     * @param value
     *     allowed object is
     *     {@link Log }
     *     
     */
    public void setLog(Log value) {
        this.log = value;
    }

    /**
     * Gets the value of the serialAgentOrJSONAgentOrTcpAgent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serialAgentOrJSONAgentOrTcpAgent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSerialAgentOrJSONAgentOrTcpAgent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SerialAgent }
     * {@link JSONAgent }
     * {@link TcpAgent }
     * {@link UdpAgent }
     * {@link ConsoleAgent }
     * {@link TrackAgent }
     * {@link SensorAgent }
     * {@link SimulatorAgent }
     * {@link MeteoAgent }
     * {@link MWDAgent }
     * {@link GPXPlayerAgent }
     * 
     * 
     */
    public List<AgentBase> getSerialAgentOrJSONAgentOrTcpAgent() {
        if (serialAgentOrJSONAgentOrTcpAgent == null) {
            serialAgentOrJSONAgentOrTcpAgent = new ArrayList<AgentBase>();
        }
        return this.serialAgentOrJSONAgentOrTcpAgent;
    }

}
