package com.example.agentapp.model.builder;

import com.example.agentapp.model.Pricelist;
import com.example.agentapp.model.VehicleDiscount;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PricelistBuilder {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private float price;
    private float priceByMile;
    private float priceCollision;
    private Long vehicleId;
    private VehicleDiscount vehicleDiscount;

    public PricelistBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public PricelistBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public PricelistBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public PricelistBuilder setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
        return this;
    }

    public PricelistBuilder setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
        return this;
    }

    public PricelistBuilder setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public PricelistBuilder setVehicleDiscount(VehicleDiscount vehicleDiscount) {
        this.vehicleDiscount = vehicleDiscount;
        return this;
    }

    public Pricelist createPriceList() {
        return new Pricelist(startDate, endDate, price, priceByMile, priceCollision, vehicleId, vehicleDiscount);
    }
}
