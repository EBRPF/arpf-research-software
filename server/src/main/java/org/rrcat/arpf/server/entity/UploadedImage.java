package org.rrcat.arpf.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class UploadedImage {
    @Id
    private Integer id;
    private String tag;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String directory) {
        this.tag = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadedImage that = (UploadedImage) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTag(), that.getTag()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTag(), getName());
    }

    @Override
    public String toString() {
        return "UploadedFile{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
