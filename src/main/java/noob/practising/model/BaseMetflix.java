package noob.practising.model;

import java.util.Objects;

public abstract class BaseMetflix {

    private int id;
    private String name;
    private String nationality;

    public BaseMetflix() {
    }

    public BaseMetflix(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseMetflix(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public BaseMetflix(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseMetflix)) return false;
        BaseMetflix that = (BaseMetflix) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "BaseMetflix{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}