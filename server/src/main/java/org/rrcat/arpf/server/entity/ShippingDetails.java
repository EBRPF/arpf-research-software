package org.rrcat.arpf.server.entity;

import org.rrcat.arpf.server.entity.embedable.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "shipping_details")
public final class ShippingDetails {
    @Id
    @Column(name = "order_pk")
    private Integer registrationNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_pk")
    private DosimetryInfo dosimetryInfo;

    @Column(name = "shipping_date")
    private Date shippingDate;

    @Column(name = "shipping_medium")
    private String shippingMedium;

    @Embedded
    private Address shippingAddress;

    @Column(name = "shipped_packets")
    private Integer shippedPackets;

    @ManyToOne
    private UploadedImage gatePassImage;

    @ManyToOne
    private UploadedImage dosimetryReportImage;

    public DosimetryInfo getDosimetryInfo() {
        return dosimetryInfo;
    }

    public void setDosimetryInfo(DosimetryInfo dosimetryInfo) {
        this.dosimetryInfo = dosimetryInfo;
        this.registrationNo = dosimetryInfo.getRadiationProcessingData().getOrder().getRegistrationNo();
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
        return Objects.equals(getDosimetryInfo(), that.getDosimetryInfo()) && Objects.equals(getShippingDate(), that.getShippingDate()) && Objects.equals(getShippingMedium(), that.getShippingMedium()) && Objects.equals(getShippingAddress(), that.getShippingAddress()) && Objects.equals(getShippedPackets(), that.getShippedPackets()) && Objects.equals(getGatePassImage(), that.getGatePassImage()) && Objects.equals(getDosimetryReportImage(), that.getDosimetryReportImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDosimetryInfo(), getShippingDate(), getShippingMedium(), getShippingAddress(), getShippedPackets(), getGatePassImage(), getDosimetryReportImage());
    }
}
