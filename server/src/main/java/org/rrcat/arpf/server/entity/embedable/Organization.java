package org.rrcat.arpf.server.entity.embedable;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Organization {
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
