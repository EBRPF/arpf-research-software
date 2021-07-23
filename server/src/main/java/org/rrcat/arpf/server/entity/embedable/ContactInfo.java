package org.rrcat.arpf.server.entity.embedable;

import org.dae.arpf.dto.AddressDTO;
import org.dae.arpf.dto.ContactInfoDTO;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class ContactInfo {
    private String name;
    private String mobileNo;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getMobileNo(), that.getMobileNo()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMobileNo(), getEmail());
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static ContactInfo fromDTO(final ContactInfoDTO dto) {
        final ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(dto.email());
        contactInfo.setMobileNo(dto.mobileNo());
        contactInfo.setName(dto.name());
        return contactInfo;
    }
}
