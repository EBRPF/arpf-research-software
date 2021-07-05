package org.rrcat.arpf.server.entity;

import org.rrcat.arpf.server.entity.embedable.Address;
import org.rrcat.arpf.server.entity.embedable.ContactInfo;
import org.rrcat.arpf.server.entity.embedable.Organization;

import javax.persistence.*;
import java.util.Objects;

@Entity
public final class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_pk")
    private Integer registrationNo;

    @Embedded
    private Organization organization;

    @Embedded
    private ContactInfo researchHeadInfo;

    @Embedded
    private Address address;

    @Embedded
    private ContactInfo researchOfficerInfo;

    private String extraInfo;

    @OneToOne
    private UploadedImage image;

    public Integer getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(final Integer registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public ContactInfo getResearchHeadInfo() {
        return researchHeadInfo;
    }

    public void setResearchHeadInfo(final ContactInfo researchHeadInfo) {
        this.researchHeadInfo = researchHeadInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public ContactInfo getResearchOfficerInfo() {
        return researchOfficerInfo;
    }

    public void setResearchOfficerInfo(final ContactInfo researchOfficerInfo) {
        this.researchOfficerInfo = researchOfficerInfo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(final String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public UploadedImage getImage() {
        return image;
    }

    public void setImage(UploadedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getRegistrationNo(), customer.getRegistrationNo()) &&
                Objects.equals(getOrganization(), customer.getOrganization()) &&
                Objects.equals(getResearchHeadInfo(), customer.getResearchHeadInfo()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getResearchOfficerInfo(), customer.getResearchOfficerInfo()) &&
                Objects.equals(getExtraInfo(), customer.getExtraInfo()) &&
                Objects.equals(getImage(), customer.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNo(), getOrganization(), getResearchHeadInfo(), getAddress(), getResearchOfficerInfo(), getExtraInfo(), getImage());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "registrationNo=" + registrationNo +
                ", organization=" + organization +
                ", researchHeadInfo=" + researchHeadInfo +
                ", address=" + address +
                ", researchOfficerInfo=" + researchOfficerInfo +
                ", extraInfo='" + extraInfo + '\'' +
                ", image=" + image +
                '}';
    }
}
