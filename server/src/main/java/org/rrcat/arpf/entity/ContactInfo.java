package org.rrcat.arpf.entity;

import java.util.Objects;

public final class ContactInfo {
    private final String name;
    private final String mobileNo;
    private final String email;

    public ContactInfo(final String name, final String mobileNo, final String email) {
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
    public String toString() {
        return "ContactInfo{" +
                "name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mobileNo, email);
    }
}
