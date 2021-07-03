package org.rrcat.arpf.server.entity;

import org.rrcat.arpf.server.entity.embedable.Address;
import org.rrcat.arpf.server.entity.embedable.ContactInfo;
import org.rrcat.arpf.server.entity.embedable.Organization;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class Customer {
    @Id
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
}
