//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.19 at 12:15:17 AM CEST 
//


package com.example.agentapp.xmlmodel.bundle;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rentacar.bundle package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rentacar.bundle
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bundle }
     * 
     */
    public Bundle createBundle() {
        return new Bundle();
    }

    /**
     * Create an instance of {@link GetAllBundle }
     * 
     */
    public GetAllBundle createGetAllBundle() {
        return new GetAllBundle();
    }

    /**
     * Create an instance of {@link Bundle.Requests }
     * 
     */
    public Bundle.Requests createBundleRequests() {
        return new Bundle.Requests();
    }

    /**
     * Create an instance of {@link GetBundleById }
     * 
     */
    public GetBundleById createGetBundleById() {
        return new GetBundleById();
    }

    /**
     * Create an instance of {@link GetBundle }
     * 
     */
    public GetBundle createGetBundle() {
        return new GetBundle();
    }

}
