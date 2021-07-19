package org.rrcat.arpf.server.entity;

import org.rrcat.arpf.server.entity.embedable.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "shipping_details")
public final class ShippingDetails {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name ="shipping_pk")
    private Order order;

    private Date shippingDate;

    private String shippingMedium;

    @Embedded
    private Address shippingAddress;

    private Integer shippedPackets;

    @ManyToOne
    private UploadedImage gatePassImage;

    @ManyToOne
    private UploadedImage dosimetryReportImage;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingMedium() {
        return shippingMedium;
    }

    public void setShippingMedium(String shippingMedium) {
        this.shippingMedium = shippingMedium;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getShippedPackets() {
        return shippedPackets;
    }

    public void setShippedPackets(Integer shippedPackets) {
        this.shippedPackets = shippedPackets;
    }

    public UploadedImage getGatePassImage() {
        return gatePassImage;
    }

    public void setGatePassImage(UploadedImage gatePassImage) {
        this.gatePassImage = gatePassImage;
    }

    public UploadedImage getDosimetryReportImage() {
        return dosimetryReportImage;
    }

    public void setDosimetryReportImage(UploadedImage dosimetryReportImage) {
        this.dosimetryReportImage = dosimetryReportImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingDetails that = (ShippingDetails) o;
        return Objects.equals(getOrder(), that.getOrder()) && Objects.equals(getShippingDate(), that.getShippingDate()) && Objects.equals(getShippingMedium(), that.getShippingMedium()) && Objects.equals(getShippingAddress(), that.getShippingAddress()) && Objects.equals(getShippedPackets(), that.getShippedPackets()) && Objects.equals(getGatePassImage(), that.getGatePassImage()) && Objects.equals(getDosimetryReportImage(), that.getDosimetryReportImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getShippingDate(), getShippingMedium(), getShippingAddress(), getShippedPackets(), getGatePassImage(), getDosimetryReportImage());
    }
}
