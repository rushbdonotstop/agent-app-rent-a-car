//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.20 at 10:55:26 PM CEST 
//


package com.example.agentapp.xmlmodel.catalogue.vehicle_transmission;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://rentacar.com/vehicle-transmission}vehicleTransmission"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vehicleTransmission"
})
@XmlRootElement(name = "createVehicleTransmission")
public class CreateVehicleTransmission {

    @XmlElement(required = true)
    protected VehicleTransmission vehicleTransmission;

    /**
     * Gets the value of the vehicleTransmission property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleTransmission }
     *     
     */
    public VehicleTransmission getVehicleTransmission() {
        return vehicleTransmission;
    }

    /**
     * Sets the value of the vehicleTransmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleTransmission }
     *     
     */
    public void setVehicleTransmission(VehicleTransmission value) {
        this.vehicleTransmission = value;
    }

}
