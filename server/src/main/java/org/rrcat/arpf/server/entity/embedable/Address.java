package org.rrcat.arpf.server.entity.embedable;

import org.dae.arpf.dto.AddressDTO;
import org.dae.arpf.dto.CustomerDTO;
import org.rrcat.arpf.server.entity.Customer;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class Address {
    private String addressText;
    private String city;
    private String state;
    private String pinCode;
    private String phone;

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(final String addressText) {
        this.addressText = addressText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Address address = (Address) o;
        return Objects.equals(getAddressText(), address.getAddressText()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getState(), address.getState()) &&
                Objects.equals(getPinCode(), address.getPinCode()) &&
                Objects.equals(getPhone(), address.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressText(), getCity(), getState(), getPinCode(), getPhone());
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressText='" + addressText + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static Address fromDTO(final AddressDTO dto) {
        final Address address = new Address();
        address.setState(dto.state());
        address.setAddressText(dto.addressText());
        address.setCity(dto.city());
        address.setPhone(dto.phone());
        address.setPinCode(dto.pinCode());
        return address;
    }
}
