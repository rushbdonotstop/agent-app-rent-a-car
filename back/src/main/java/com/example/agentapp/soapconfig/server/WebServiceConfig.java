package com.example.agentapp.soapconfig.server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "location")
    public DefaultWsdl11Definition defaultWsdl11DefinitionLocation (XsdSchema locationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("LocationPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/location");
        wsdl11Definition.setSchema(locationSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema locationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/location/location.xsd"));
    }

    @Bean(name = "vehicle")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicle (XsdSchema vehicleSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehiclePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle");
        wsdl11Definition.setSchema(vehicleSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/vehicle/vehicle.xsd"));
    }

    @Bean(name = "vehicleImage")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleImage (XsdSchema vehicleImageSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleImagePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-image");
        wsdl11Definition.setSchema(vehicleImageSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleImageSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/vehicle/vehicle-image.xsd"));
    }

    @Bean(name = "vehicleMake")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleMake (XsdSchema vehicleMakeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleMakePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-make");
        wsdl11Definition.setSchema(vehicleMakeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleMakeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/catalogue/vehicle-make.xsd"));
    }

    @Bean(name = "vehicleModel")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleModel (XsdSchema vehicleModelSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleModelPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-model");
        wsdl11Definition.setSchema(vehicleModelSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleModelSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/catalogue/vehicle-model.xsd"));
    }

    @Bean(name = "vehicleTransmission")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleTransmission (XsdSchema vehicleTransmissionSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleTransmissionPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-transmission");
        wsdl11Definition.setSchema(vehicleTransmissionSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleTransmissionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/catalogue/vehicle-transmission.xsd"));
    }

    @Bean(name = "vehicleStyle")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleStyle (XsdSchema vehicleStyleSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleStylePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-style");
        wsdl11Definition.setSchema(vehicleStyleSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleStyleSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/catalogue/vehicle-style.xsd"));
    }

    @Bean(name = "vehicleFuelType")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleFuelType (XsdSchema vehicleFuelTypeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleFuelTypePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-fuel-type");
        wsdl11Definition.setSchema(vehicleFuelTypeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleFuelTypeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/catalogue/vehicle-fuel-type.xsd"));
    }

    @Bean(name = "pricelist")
    public DefaultWsdl11Definition defaultWsdl11DefinitionPricelist (XsdSchema pricelistSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PricelistPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/pricelist");
        wsdl11Definition.setSchema(pricelistSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema pricelistSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/pricelist/pricelist.xsd"));
    }

    @Bean(name = "vehicleDiscount")
    public DefaultWsdl11Definition defaultWsdl11DefinitionVehicleDiscount (XsdSchema vehicleDiscountSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("VehicleDiscountPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/vehicle-discount");
        wsdl11Definition.setSchema(vehicleDiscountSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema vehicleDiscountSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/pricelist/vehicle-discount.xsd"));
    }

    @Bean(name = "user")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUser (XsdSchema userSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user");
        wsdl11Definition.setSchema(userSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user.xsd"));
    }

    @Bean(name = "userDetails")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUserDetails (XsdSchema userDetailsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserDetailsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user-details");
        wsdl11Definition.setSchema(userDetailsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userDetailsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-details.xsd"));
    }

    @Bean(name = "userPrivilege")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUserPrivilege (XsdSchema userPrivilegeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserPrivilegePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user-privilege");
        wsdl11Definition.setSchema(userPrivilegeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userPrivilegeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-privilege.xsd"));
    }

    @Bean(name = "penalty")
    public DefaultWsdl11Definition defaultWsdl11DefinitionPenalty (XsdSchema penaltySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PenaltyPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user-penalty");
        wsdl11Definition.setSchema(penaltySchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema penaltySchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-penalty.xsd"));
    }

    @Bean(name = "bundle")
    public DefaultWsdl11Definition defaultWsdl11DefinitionBundle (XsdSchema bundleSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BundlePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/bundle");
        wsdl11Definition.setSchema(bundleSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema bundleSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/request/bundle.xsd"));
    }

    @Bean(name = "request")
    public DefaultWsdl11Definition defaultWsdl11DefinitionRequest (XsdSchema requestSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RequestPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/request");
        wsdl11Definition.setSchema(requestSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema requestSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/request/request.xsd"));
    }

}
