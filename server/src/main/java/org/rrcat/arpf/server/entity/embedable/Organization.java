package org.rrcat.arpf.server.entity.embedable;

import org.dae.arpf.dto.ContactInfoDTO;
import org.dae.arpf.dto.OrganizationDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Organization {
    @Column(unique = true)
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

    public static Organization fromDTO(final OrganizationDTO dto) {
        final Organization org = new Organization();
        org.setName(dto.name());
        org.setType(dto.type());
        return org;
    }
}
