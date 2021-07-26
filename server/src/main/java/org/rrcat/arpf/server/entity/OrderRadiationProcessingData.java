package org.rrcat.arpf.server.entity;

import org.dae.arpf.dto.OrderDTO;
import org.dae.arpf.dto.OrderDTOBuilder;
import org.dae.arpf.dto.OrderRadiationProcessingDTO;
import org.dae.arpf.dto.OrderRadiationProcessingDTOBuilder;
import org.rrcat.arpf.server.repository.CustomerRepository;
import org.rrcat.arpf.server.repository.OrderRepository;
import org.rrcat.arpf.server.repository.UploadedImageRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "order_radiation_processing")
public final class OrderRadiationProcessingData {

    @Id
    @Column(name = "order_pk")
    private Integer registrationNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_pk")
    private Order order;

    @Column(name = "dosimeter_used")
    private String dosimeterUsed;

    @Column(name = "dosimeter_location")
    private String dosimeterLocation;

    @Column(name = "processing_date")
    private Date processingDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "beam_energy")
    private String beamEnergy;

    @Column(name = "beam_current")
    private String beamCurrent;

    @Column(name = "prr")
    private String PRR;

    @Column(name = "scan_width")
    private Float scanWidth;

    @Column(name = "scan_current_and_time")
    private String scanCurrentAndTime;

    @Column(name = "conveyor_speed")
    private String conveyorSpeed;

    @Column(name = "dode_rate")
    private String doseRate;

    @Column(name = "source2surface_dimension")
    private String sourceToSurfaceDimension;

    @Column(name = "related_machine_params")
    private String relatedMachineParams;

    @Column(name = "operator_remarks")
    private String operatorRemarks;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.registrationNo = order.getRegistrationNo();
    }

    public String getDosimeterUsed() {
        return dosimeterUsed;
    }

    public void setDosimeterUsed(String dosimeterUsed) {
        this.dosimeterUsed = dosimeterUsed;
    }

    public String getDosimeterLocation() {
        return dosimeterLocation;
    }

    public void setDosimeterLocation(String dosimeterLocation) {
        this.dosimeterLocation = dosimeterLocation;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBeamEnergy() {
        return beamEnergy;
    }

    public void setBeamEnergy(String beamEnergy) {
        this.beamEnergy = beamEnergy;
    }

    public String getBeamCurrent() {
        return beamCurrent;
    }

    public void setBeamCurrent(String beamCurrent) {
        this.beamCurrent = beamCurrent;
    }

    public String getPRR() {
        return PRR;
    }

    public void setPRR(String PRR) {
        this.PRR = PRR;
    }

    public Float getScanWidth() {
        return scanWidth;
    }

    public void setScanWidth(Float scanWidth) {
        this.scanWidth = scanWidth;
    }

    public String getScanCurrentAndTime() {
        return scanCurrentAndTime;
    }

    public void setScanCurrentAndTime(String scanCurrentAndTime) {
        this.scanCurrentAndTime = scanCurrentAndTime;
    }

    public String getConveyorSpeed() {
        return conveyorSpeed;
    }

    public void setConveyorSpeed(String conveyorSpeed) {
        this.conveyorSpeed = conveyorSpeed;
    }

    public String getDoseRate() {
        return doseRate;
    }

    public void setDoseRate(String doseRate) {
        this.doseRate = doseRate;
    }

    public String getSourceToSurfaceDimension() {
        return sourceToSurfaceDimension;
    }

    public void setSourceToSurfaceDimension(String sourceToSurfaceDimension) {
        this.sourceToSurfaceDimension = sourceToSurfaceDimension;
    }

    public String getRelatedMachineParams() {
        return relatedMachineParams;
    }

    public void setRelatedMachineParams(String relatedMachineParams) {
        this.relatedMachineParams = relatedMachineParams;
    }

    public String getOperatorRemarks() {
        return operatorRemarks;
    }

    public void setOperatorRemarks(String operatorRemarks) {
        this.operatorRemarks = operatorRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRadiationProcessingData that = (OrderRadiationProcessingData) o;
        return Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getDosimeterUsed(), that.getDosimeterUsed()) &&
                Objects.equals(getDosimeterLocation(), that.getDosimeterLocation()) &&
                Objects.equals(getProcessingDate(), that.getProcessingDate()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getBeamEnergy(), that.getBeamEnergy()) &&
                Objects.equals(getBeamCurrent(), that.getBeamCurrent()) &&
                Objects.equals(getPRR(), that.getPRR()) &&
                Objects.equals(getScanWidth(), that.getScanWidth()) &&
                Objects.equals(getScanCurrentAndTime(), that.getScanCurrentAndTime()) &&
                Objects.equals(getConveyorSpeed(), that.getConveyorSpeed()) &&
                Objects.equals(getDoseRate(), that.getDoseRate()) &&
                Objects.equals(getSourceToSurfaceDimension(), that.getSourceToSurfaceDimension()) &&
                Objects.equals(getRelatedMachineParams(), that.getRelatedMachineParams()) &&
                Objects.equals(getOperatorRemarks(), that.getOperatorRemarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getDosimeterUsed(), getDosimeterLocation(), getProcessingDate(), getStartTime(), getEndTime(), getBeamEnergy(), getBeamCurrent(), getPRR(), getScanWidth(), getScanCurrentAndTime(), getConveyorSpeed(), getDoseRate(), getSourceToSurfaceDimension(), getRelatedMachineParams(), getOperatorRemarks());
    }

    public static OrderRadiationProcessingDTO toDTO(final OrderRadiationProcessingData order) {
        if (order == null) return null;
        return OrderRadiationProcessingDTOBuilder.builder()
                .registrationNo(order.getOrder().getRegistrationNo())
                .beamCurrent(order.getBeamCurrent())
                .beamEnergy(order.getBeamEnergy())
                .dosimeterUsed(order.getDosimeterUsed())
                .dosimeterLocation(order.getDosimeterLocation())
                .conveyorSpeed(order.getConveyorSpeed())
                .doseRate(order.getDoseRate())
                .endTime(order.getEndTime())
                .operatorRemarks(order.getOperatorRemarks())
                .relatedMachineParams(order.getRelatedMachineParams())
                .scanCurrentAndTime(order.getScanCurrentAndTime())
                .sourceToSurfaceDimension(order.getSourceToSurfaceDimension())
                .processingDate(order.getProcessingDate())
                .PRR(order.getPRR())
                .scanWidth(order.getScanWidth())
                .startTime(order.getStartTime())
                .build();
    }

    public static OrderRadiationProcessingData fromDTO(final OrderRadiationProcessingDTO dto, final OrderRepository orderRepository) {
        final OrderRadiationProcessingData orp = new OrderRadiationProcessingData();
        final Order order = orderRepository.findOrderByRegistrationNo(dto.registrationNo());
        orp.setOrder(order);
        orp.setBeamCurrent(dto.beamCurrent());
        orp.setBeamEnergy(dto.beamEnergy());
        orp.setProcessingDate(dto.processingDate());
        orp.setDoseRate(dto.doseRate());
        orp.setConveyorSpeed(dto.conveyorSpeed());
        orp.setEndTime(dto.endTime());
        orp.setConveyorSpeed(dto.conveyorSpeed());
        orp.setDosimeterLocation(dto.dosimeterLocation());
        orp.setDosimeterUsed(dto.dosimeterUsed());
        orp.setOperatorRemarks(dto.operatorRemarks());
        orp.setStartTime(dto.startTime());
        return orp;
    }
}
