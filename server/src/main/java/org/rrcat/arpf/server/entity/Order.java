package org.rrcat.arpf.server.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order")
public final class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_pk")
    private Integer registrationNo;

    @ManyToOne
    @JoinColumn(name="customer_fk", referencedColumnName = "customer_pk", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "product_desc")
    private String productDescription;

    @Column(name = "product_material")
    private String productMaterial;

    @Column(name = "product_details")
    private String productDetails;

    @Column(name = "irradiation_purpose")
    private String irradiationPurpose;

    @Column(name = "irradiation_mode")
    private String irradiationMode;

    @Column(name = "required_dose")
    private String requiredDose;

    @Column(name = "product_dimensions")
    private String productDimensions;

    @Column(name = "product_weight")
    private String productWeight;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "extra_info")
    private String extraInfo;

    @Column(name = "receipt_date")
    private Date receiptDate;

    @OneToOne
    private UploadedImage image;

    @Column(name = "comments")
    private String comments;

    @Column(name = "order_registered")
    private boolean registered;

    public Integer getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(Integer registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getIrradiationPurpose() {
        return irradiationPurpose;
    }

    public void setIrradiationPurpose(String irradiationPurpose) {
        this.irradiationPurpose = irradiationPurpose;
    }

    public String getIrradiationMode() {
        return irradiationMode;
    }

    public void setIrradiationMode(String irradiationMode) {
        this.irradiationMode = irradiationMode;
    }

    public String getRequiredDose() {
        return requiredDose;
    }

    public void setRequiredDose(String requiredDose) {
        this.requiredDose = requiredDose;
    }

    public String getProductDimensions() {
        return productDimensions;
    }

    public void setProductDimensions(String productDimensions) {
        this.productDimensions = productDimensions;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public UploadedImage getImage() {
        return image;
    }

    public void setImage(UploadedImage image) {
        this.image = image;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isRegistered() == order.isRegistered() &&
                Objects.equals(getRegistrationNo(), order.getRegistrationNo()) &&
                Objects.equals(getCustomer(), order.getCustomer()) &&
                Objects.equals(getProductDescription(), order.getProductDescription()) &&
                Objects.equals(getProductMaterial(), order.getProductMaterial()) &&
                Objects.equals(getProductDetails(), order.getProductDetails()) &&
                Objects.equals(getIrradiationPurpose(), order.getIrradiationPurpose()) &&
                Objects.equals(getIrradiationMode(), order.getIrradiationMode()) &&
                Objects.equals(getRequiredDose(), order.getRequiredDose()) &&
                Objects.equals(getProductDimensions(), order.getProductDimensions()) &&
                Objects.equals(getProductWeight(), order.getProductWeight()) &&
                Objects.equals(getProductCount(), order.getProductCount()) &&
                Objects.equals(getExtraInfo(), order.getExtraInfo()) &&
                Objects.equals(getReceiptDate(), order.getReceiptDate()) &&
                Objects.equals(getImage(), order.getImage()) &&
                Objects.equals(getComments(), order.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNo(), getCustomer(), getProductDescription(), getProductMaterial(), getProductDetails(), getIrradiationPurpose(), getIrradiationMode(), getRequiredDose(), getProductDimensions(), getProductWeight(), getProductCount(), getExtraInfo(), getReceiptDate(), getImage(), getComments(), isRegistered());
    }
}
