package org.dae.arpf.dto;

import java.util.Objects;

public final class AddressDTO {
    private final String addressText;
    private final String city;
    private final String state;
    private final String pinCode;
    private final String phone;

    public AddressDTO(final String addressText, final String city, final String state, final String pinCode, final String phone) {
        this.addressText = addressText;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.phone = phone;
    }

    public String getAddressText() {
        return addressText;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(getAddressText(), that.getAddressText()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getState(), that.getState()) &&
                Objects.equals(getPinCode(), that.getPinCode()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressText(), getCity(), getState(), getPinCode(), getPhone());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String addressText;
        private String city;
        private String state;
        private String pinCode;
        private String phone;

        public Builder addressText(String addressText) {
            this.addressText = addressText;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder pinCode(String pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public AddressDTO build() {
            return new AddressDTO(addressText, city, state, pinCode, phone);
        }
    }
}