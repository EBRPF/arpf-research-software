package org.rrcat.arpf.server.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
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

}
