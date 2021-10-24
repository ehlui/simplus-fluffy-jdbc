package noob.practising.model;

public class Director extends BaseMetflix {

    public Director(int id, String name, String nationality) {
        super(id, name, nationality);
    }

    public Director(String name, String nationality) {
        super(name, nationality);
    }

    public Director(int id, String name) {
        super(id, name);
    }

    public Director() {
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", nationality='" + getNationality() + '\'' +
                '}';
    }
}
