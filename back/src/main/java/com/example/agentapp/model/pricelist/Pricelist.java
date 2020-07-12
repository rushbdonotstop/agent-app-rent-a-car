package com.example.agentapp.model.pricelist;

import com.example.agentapp.model.builders.PricelistBuilder;

import javax.persistence.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Entity
public class Pricelist implements Comparable<Pricelist>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "price_by_mile", nullable = false)
    private float priceByMile;

    @Column(name = "price_collision", nullable = false)
    private float priceCollision;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @OneToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private VehicleDiscount vehicleDiscount;

    public Pricelist() {
    }

    public Pricelist(LocalDateTime startDate, LocalDateTime endDate, float price, float priceByMile, float priceCollision, Long vehicleId, VehicleDiscount vehicleDiscount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscount = vehicleDiscount;
    }

    public static PricelistBuilder builder() {
        return new PricelistBuilder();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceByMile() {
        return priceByMile;
    }

    public void setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
    }

    public float getPriceCollision() {
        return priceCollision;
    }

    public void setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDiscount getVehicleDiscount() {
        return vehicleDiscount;
    }

    public void setVehicleDiscount(VehicleDiscount vehicleDiscount) {
        this.vehicleDiscount = vehicleDiscount;
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceByMile=" + priceByMile +
                ", priceCollision=" + priceCollision +
                ", vehicleId=" + vehicleId +
                ", vehicleDiscount=" + vehicleDiscount +
                '}';
    }

    @Override
    public int compareTo(Pricelist o) {
        return this.startDate.compareTo(o.startDate);
    }

    public com.example.agentapp.xmlmodel.pricelist.Pricelist toXML(Pricelist pricelist) throws DatatypeConfigurationException {
        com.example.agentapp.xmlmodel.pricelist.Pricelist pricelistXML = new com.example.agentapp.xmlmodel.pricelist.Pricelist();
        pricelistXML.setId(pricelist.getId());
        pricelistXML.setPrice(pricelist.getPrice());
        pricelistXML.setPriceByMile(pricelist.getPriceByMile());
        pricelistXML.setPriceCollision(pricelist.getPriceCollision());

        LocalDate date = pricelist.getStartDate().toLocalDate();
        GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        pricelistXML.setStartDate(xcal);

        date = pricelist.getEndDate().toLocalDate();
        gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        pricelistXML.setEndDate(xcal);

        com.example.agentapp.xmlmodel.pricelist.vehicle_discount.VehicleDiscount vehicleDiscountXML = new com.example.agentapp.xmlmodel.pricelist.vehicle_discount.VehicleDiscount();

        vehicleDiscountXML.setDiscount(pricelist.getVehicleDiscount().getDiscount());
        vehicleDiscountXML.setId(pricelist.getVehicleDiscount().getId());
        vehicleDiscountXML.setNumDays(pricelist.getVehicleDiscount().getNumDays());

        pricelistXML.setVehicleDiscount(vehicleDiscountXML);

        return pricelistXML;
    }
}
