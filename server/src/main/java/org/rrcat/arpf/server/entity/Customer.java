package org.rrcat.arpf.server.entity;

import org.dae.arpf.dto.*;
import org.rrcat.arpf.server.entity.embedable.Address;
import org.rrcat.arpf.server.entity.embedable.ContactInfo;
import org.rrcat.arpf.server.entity.embedable.Organization;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.util.Objects;

@Entity
@Table(name = "customer")
public final class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_pk")
    private Integer registrationNo;

    @Embedded
    @Column(name = "organization")
    private Organization organization;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "research_head_name")),
            @AttributeOverride(name = "mobileNo", column = @Column(name = "research_head_mobile")),
            @AttributeOverride(name = "email", column = @Column(name = "research_head_email"))
    })
    @Column(name = "research_head_info")
    private ContactInfo researchHeadInfo;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "research_officer_name")),
            @AttributeOverride(name = "mobileNo", column = @Column(name = "research_officer_mobile")),
            @AttributeOverride(name = "email", column = @Column(name = "research_officer_email"))
    })
    @Column(name = "research_officer_info")
    private ContactInfo researchOfficerInfo;

    @Column(name = "extra_info")
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

    public static CustomerDTO toDTO(final Customer customer) {
        if (customer == null) return null;
        final Address address = customer.getAddress();
        final Organization organization = customer.getOrganization();
        final ContactInfo researchHead = customer.getResearchHeadInfo();
        final ContactInfo researchOfficer = customer.getResearchOfficerInfo();
        return CustomerDTOBuilder.builder()
                .registrationNo(customer.getRegistrationNo())
                .address(
                        AddressDTOBuilder.builder()
                                .addressText(address.getAddressText())
                                .city(address.getCity())
                                .phone(address.getPhone())
                                .pinCode(address.getPinCode())
                                .state(address.getState())
                                .build()
                )
                .extraInfo(customer.getExtraInfo())
                .imageKey(customer.getImage().getId())
                .organization(
                        OrganizationDTOBuilder.builder()
                                .name(organization.getName())
                                .type(organization.getType())
                                .build()
                )
                .researchHeadInfo(
                        ContactInfoDTOBuilder.builder()
                                .email(researchHead.getEmail())
                                .mobileNo(researchHead.getMobileNo())
                                .email(researchHead.getName())
                                .build()
                )
                .researchOfficerInfo(
                        ContactInfoDTOBuilder.builder()
                                .email(researchOfficer.getEmail())
                                .mobileNo(researchOfficer.getMobileNo())
                                .email(researchOfficer.getName())
                                .build()
                )
                .build();
    }

    public static Customer fromDTO(final CustomerDTO dto, final UploadedImage image) {
        final Customer customer = new Customer();
        customer.setRegistrationNo(dto.registrationNo());
        customer.setAddress(Address.fromDTO(dto.address()));
        customer.setExtraInfo(dto.extraInfo());
        customer.setImage(image);
        customer.setResearchHeadInfo(ContactInfo.fromDTO(dto.researchHeadInfo()));
        customer.setResearchOfficerInfo(ContactInfo.fromDTO(dto.researchOfficerInfo()));
        return customer;
    }
}
