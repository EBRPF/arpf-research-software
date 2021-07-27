package org.rrcat.arpf.ui.api.model;

public class OrderDosimetry {

//OrderDetail
    String orderNumber;
    String nameOfOrganisation;
    String descrOfProducts;
    String materialOfProduct;
    String detailOfProduct;
    String purposeOfIrradiation;
    String modeOfIrradiation;
    String requiredDose;
    String dosimeterUsed;
    String dosimeterLocation;
    String dateOfOrder;
//Radiation Processing Data
    String startTime;
    String completionTime;
    String beamEnergy;
    String beamCurrent;
    String prr;
    String scanWidth;
    String scanCurrentTime;
    String conveyorSpeed;
    String doseRate;
    String sTosDistance;
    String otherParamaters;
    String operatorRemarks;
//Dosimetry Data

    String dosimetryResults;


    public String getModeOfIrradiation() {
        return modeOfIrradiation;
    }

    public void setModeOfIrradiation(String modeOfIrradiation) {
        this.modeOfIrradiation = modeOfIrradiation;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNameOfOrganisation() {
        return nameOfOrganisation;
    }

    public void setNameOfOrganisation(String nameOfOrganisation) {
        this.nameOfOrganisation = nameOfOrganisation;
    }

    public String getDescrOfProducts() {
        return descrOfProducts;
    }

    public void setDescrOfProducts(String descrOfProducts) {
        this.descrOfProducts = descrOfProducts;
    }

    public String getMaterialOfProduct() {
        return materialOfProduct;
    }

    public void setMaterialOfProduct(String materialOfProduct) {
        this.materialOfProduct = materialOfProduct;
    }

    public String getDetailOfProduct() {
        return detailOfProduct;
    }

    public void setDetailOfProduct(String detailOfProduct) {
        this.detailOfProduct = detailOfProduct;
    }

    public String getPurposeOfIrradiation() {
        return purposeOfIrradiation;
    }

    public void setPurposeOfIrradiation(String purposeOfIrradiation) {
        this.purposeOfIrradiation = purposeOfIrradiation;
    }

    public String getRequiredDose() {
        return requiredDose;
    }

    public void setRequiredDose(String requiredDose) {
        this.requiredDose = requiredDose;
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

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
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

    public String getPrr() {
        return prr;
    }

    public void setPrr(String prr) {
        this.prr = prr;
    }

    public String getScanWidth() {
        return scanWidth;
    }

    public void setScanWidth(String scanWidth) {
        this.scanWidth = scanWidth;
    }

    public String getScanCurrentTime() {
        return scanCurrentTime;
    }

    public void setScanCurrentTime(String scanCurrentTime) {
        this.scanCurrentTime = scanCurrentTime;
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

    public String getsTosDistance() {
        return sTosDistance;
    }

    public void setsTosDistance(String sTosDistance) {
        this.sTosDistance = sTosDistance;
    }

    public String getOtherParamaters() {
        return otherParamaters;
    }

    public void setOtherParamaters(String otherParamaters) {
        this.otherParamaters = otherParamaters;
    }

    public String getOperatorRemarks() {
        return operatorRemarks;
    }

    public void setOperatorRemarks(String operatorRemarks) {
        this.operatorRemarks = operatorRemarks;
    }

    public String getDosimetryResults() {
        return dosimetryResults;
    }

    public void setDosimetryResults(String dosimetryResults) {
        this.dosimetryResults = dosimetryResults;
    }

    public OrderDosimetry() {
    }
}
