package org.rrcat.arpf.entity;

import java.util.Objects;

public final class Location {
    private final String city;
    private final String state;
    private final String pincode;

    public Location(final String city, final String state, final String pincode) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(pincode, location.pincode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state, pincode);
    }
}
