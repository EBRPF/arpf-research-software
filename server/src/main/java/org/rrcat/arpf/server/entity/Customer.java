package org.rrcat.arpf.server.entity;

import java.util.Objects;

public final class Customer {
    private final String registrationNumber;
    private final Organization organization;
    private final ContactInfo headInfo;
    private final String officeAddress;
    private final Location location;
    private final ContactInfo contactInfo;
    private final ContactInfo researchOfficerContactInfo;
    private final String extraInfo;

    public Customer(String registrationNumber, Organization organization, ContactInfo headInfo, String officeAddress, Location location, ContactInfo contactInfo, ContactInfo researchOfficerContactInfo, String extraInfo) {
        this.registrationNumber = registrationNumber;
        this.organization = organization;
        this.headInfo = headInfo;
        this.officeAddress = officeAddress;
        this.location = location;
        this.contactInfo = contactInfo;
        this.researchOfficerContactInfo = researchOfficerContactInfo;
        this.extraInfo = extraInfo;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Organization getOrganization() {
        return organization;
    }

    public ContactInfo getHeadInfo() {
        return headInfo;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public Location getLocation() {
        return location;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public ContactInfo getResearchOfficerContactInfo() {
        return researchOfficerContactInfo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", organization=" + organization +
                ", headInfo=" + headInfo +
                ", officeAddress='" + officeAddress + '\'' +
                ", location=" + location +
                ", contactInfo=" + contactInfo +
                ", researchOfficerContactInfo=" + researchOfficerContactInfo +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(registrationNumber, customer.registrationNumber) &&
                Objects.equals(organization, customer.organization) &&
                Objects.equals(headInfo, customer.headInfo) &&
                Objects.equals(officeAddress, customer.officeAddress) &&
                Objects.equals(location, customer.location) &&
                Objects.equals(contactInfo, customer.contactInfo) &&
                Objects.equals(researchOfficerContactInfo, customer.researchOfficerContactInfo) &&
                Objects.equals(extraInfo, customer.extraInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, organization, headInfo, officeAddress, location, contactInfo, researchOfficerContactInfo, extraInfo);
    }
}
