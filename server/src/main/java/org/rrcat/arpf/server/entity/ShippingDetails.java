package org.rrcat.arpf.server.entity;

import org.dae.arpf.dto.DosimetryDTO;
import org.dae.arpf.dto.DosimetryDTOBuilder;
import org.dae.arpf.dto.ShippingDetailsDTO;
import org.dae.arpf.dto.ShippingDetailsDTOBuilder;
import org.rrcat.arpf.server.entity.embedable.Address;
import org.rrcat.arpf.server.repository.DosimetryRepository;
import org.rrcat.arpf.server.repository.OrderRPRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;

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

    public static ShippingDetailsDTO toDTO(final ShippingDetails info) {
        if (info == null) return null;
        return ShippingDetailsDTOBuilder.builder()
                .registrationNo(info.getDosimetryInfo().getRadiationProcessingData().getOrder().getRegistrationNo())
                .shippingAddress(Address.toDTO(info.getShippingAddress()))
                .shippingDate(info.getShippingDate())
                .shippedPackets(info.getShippedPackets())
                .shippingMedium(info.getShippingMedium())
                .gatePassImageKey(info.getGatePassImage().getId())
                .dosimetryReportImageKey(info.getDosimetryReportImage().getId())
                .build();
    }

    public static ShippingDetails fromDTO(final ShippingDetailsDTO dto, final DosimetryRepository dosimetryRepository, final UploadedImageRepository imageRepository) {
        final ShippingDetails details = new ShippingDetails();
        details.setDosimetryInfo(dosimetryRepository.findDosimetryInfoByRegistrationNo(dto.registrationNo()));
        details.setShippingDate(dto.shippingDate());
        details.setShippingMedium(dto.shippingMedium());
        details.setShippedPackets(dto.shippedPackets());
        details.setShippingAddress(Address.fromDTO(dto.shippingAddress()));
        details.setDosimetryReportImage(imageRepository.findUploadedImageById(dto.dosimetryReportImageKey()));
        details.setGatePassImage(imageRepository.findUploadedImageById(dto.gatePassImageKey()));
        return details;
    }
}
