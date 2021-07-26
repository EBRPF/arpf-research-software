package org.rrcat.arpf.ui.api.model;

public class OrderReg {

    String customerRegistrationNo;
    String orderNumber;
    String nameOfOrganisation;

    public String getNameOfOrganisation() {
        return nameOfOrganisation;
    }

    public void setNameOfOrganisation(String nameOfOrganisation) {
        this.nameOfOrganisation = nameOfOrganisation;
    }

    String descrOfProducts;
    String materialOfProduct;
    String detailOfProduct;
    String purposeOfIrradiation;
    String requiredDose;
    String dimensionoOfProduct;
    String weightOfProduct;
    String totalBoxes;
    String anyOtherInfo_Order;
    String dateOfOrder;
    String ficComments;

    public String getCustomerRegistrationNo() {
        return customerRegistrationNo;
    }

    public void setCustomerRegistrationNo(String customerRegistrationNo) {
        this.customerRegistrationNo = customerRegistrationNo;
    }



    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getDimensionoOfProduct() {
        return dimensionoOfProduct;
    }

    public void setDimensionoOfProduct(String dimensionoOfProduct) {
        this.dimensionoOfProduct = dimensionoOfProduct;
    }

    public String getWeightOfProduct() {
        return weightOfProduct;
    }

    public void setWeightOfProduct(String weightOfProduct) {
        this.weightOfProduct = weightOfProduct;
    }

    public String getTotalBoxes() {
        return totalBoxes;
    }

    public void setTotalBoxes(String totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public String getAnyOtherInfo_Order() {
        return anyOtherInfo_Order;
    }

    public void setAnyOtherInfo_Order(String anyOtherInfo_Order) {
        this.anyOtherInfo_Order = anyOtherInfo_Order;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getFicComments() {
        return ficComments;
    }

    public void setFicComments(String ficComments) {
        this.ficComments = ficComments;
    }

    public OrderReg() {
    }
}
