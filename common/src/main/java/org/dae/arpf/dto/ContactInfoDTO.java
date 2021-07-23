package org.dae.arpf.dto;

import java.util.Objects;

public final class ContactInfoDTO {
    private final String name;
    private final String mobileNo;
    private final String email;

    public ContactInfoDTO(final String name, final String mobileNo, final String email) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfoDTO that = (ContactInfoDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMobileNo(), that.getMobileNo()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMobileNo(), getEmail());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String mobileNo;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public ContactInfoDTO build() {
            return new ContactInfoDTO(name, mobileNo, email);
        }
    }
}
