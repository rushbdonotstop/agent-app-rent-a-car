//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.19 at 12:24:49 AM CEST 
//


package com.example.agentapp.xmlmodel.pricelist.vehicle_discount;

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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="num_days" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "id",
    "numDays",
    "discount"
})
@XmlRootElement(name = "vehicleDiscount")
public class VehicleDiscount {

    protected long id;
    @XmlElement(name = "num_days")
    protected int numDays;
    protected int discount;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the numDays property.
     * 
     */
    public int getNumDays() {
        return numDays;
    }

    /**
     * Sets the value of the numDays property.
     * 
     */
    public void setNumDays(int value) {
        this.numDays = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     */
    public void setDiscount(int value) {
        this.discount = value;
    }

    @Override
    public String toString() {
        return "VehicleDiscount{" +
                "id=" + id +
                ", numDays=" + numDays +
                ", discount=" + discount +
                '}';
    }
}