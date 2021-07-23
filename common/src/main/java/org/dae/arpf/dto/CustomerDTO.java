package org.dae.arpf.dto;

import java.util.Objects;

public final class CustomerDTO {
    private final Integer registrationNo;
    private final OrganizationDTO organization;
    private final ContactInfoDTO researchHeadInfo;
    private final AddressDTO address;
    private final ContactInfoDTO researchOfficerInfo;
    private final String extraInfo;
    private final Integer imageKey;

    public CustomerDTO(final Integer registrationNo, final OrganizationDTO organization, final ContactInfoDTO researchHeadInfo, final AddressDTO address, final ContactInfoDTO researchOfficerInfo, final String extraInfo, final Integer imageKey) {
        this.registrationNo = registrationNo;
        this.organization = organization;
        this.researchHeadInfo = researchHeadInfo;
        this.address = address;
        this.researchOfficerInfo = researchOfficerInfo;
        this.extraInfo = extraInfo;
        this.imageKey = imageKey;
    }

    public Integer getRegistrationNo() {
        return registrationNo;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public ContactInfoDTO getResearchHeadInfo() {
        return researchHeadInfo;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public ContactInfoDTO getResearchOfficerInfo() {
        return researchOfficerInfo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public Integer getImageKey() {
        return imageKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(getRegistrationNo(), that.getRegistrationNo()) &&
                Objects.equals(getOrganization(), that.getOrganization()) &&
                Objects.equals(getResearchHeadInfo(), that.getResearchHeadInfo()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getResearchOfficerInfo(), that.getResearchOfficerInfo()) &&
                Objects.equals(getExtraInfo(), that.getExtraInfo()) &&
                Objects.equals(getImageKey(), that.getImageKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNo(), getOrganization(), getResearchHeadInfo(), getAddress(), getResearchOfficerInfo(), getExtraInfo(), getImageKey());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer registrationNo;
        private OrganizationDTO organization;
        private ContactInfoDTO researchHeadInfo;
        private AddressDTO address;
        private ContactInfoDTO researchOfficerInfo;
        private String extraInfo;
        private Integer imageKey;

        public Builder registrationNo(Integer registrationNo) {
            this.registrationNo = registrationNo;
            return this;
        }

        public Builder organization(OrganizationDTO organization) {
            this.organization = organization;
            return this;
        }

        public Builder researchHeadInfo(ContactInfoDTO researchHeadInfo) {
            this.researchHeadInfo = researchHeadInfo;
            return this;
        }

        public Builder address(AddressDTO address) {
            this.address = address;
            return this;
        }

        public Builder researchOfficerInfo(ContactInfoDTO researchOfficerInfo) {
            this.researchOfficerInfo = researchOfficerInfo;
            return this;
        }

        public Builder extraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
            return this;
        }

        public Builder imageKey(Integer imageKey) {
            this.imageKey = imageKey;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(registrationNo, organization, researchHeadInfo, address, researchOfficerInfo, extraInfo, imageKey);
        }
    }
}

