package org.rrcat.arpf.server.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public final class DosimetryInfo {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name ="dosimetry_pk")
    private OrderRadiationProcessingData radiationProcessingData;

    private Date measurementDate;

    private String measurement;

    @ManyToOne
    private UploadedImage beforeImage;

    @ManyToOne
    private UploadedImage afterImage;

    private boolean dosimetryDone;

    public OrderRadiationProcessingData getRadiationProcessingData() {
        return radiationProcessingData;
    }

    public void setRadiationProcessingData(OrderRadiationProcessingData radiationProcessingData) {
        this.radiationProcessingData = radiationProcessingData;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public UploadedImage getBeforeImage() {
        return beforeImage;
    }

    public void setBeforeImage(UploadedImage beforeImage) {
        this.beforeImage = beforeImage;
    }

    public UploadedImage getAfterImage() {
        return afterImage;
    }

    public void setAfterImage(UploadedImage afterImage) {
        this.afterImage = afterImage;
    }

    public boolean isDosimetryDone() {
        return dosimetryDone;
    }

    public void setDosimetryDone(boolean dosimetryDone) {
        this.dosimetryDone = dosimetryDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DosimetryInfo that = (DosimetryInfo) o;
        return isDosimetryDone() == that.isDosimetryDone() && Objects.equals(getRadiationProcessingData(), that.getRadiationProcessingData()) && Objects.equals(getMeasurementDate(), that.getMeasurementDate()) && Objects.equals(getMeasurement(), that.getMeasurement()) && Objects.equals(getBeforeImage(), that.getBeforeImage()) && Objects.equals(getAfterImage(), that.getAfterImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRadiationProcessingData(), getMeasurementDate(), getMeasurement(), getBeforeImage(), getAfterImage(), isDosimetryDone());
    }


}
