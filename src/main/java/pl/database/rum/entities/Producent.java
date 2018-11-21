package pl.database.rum.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "producen")
public class Producent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String country;

    Integer yearFounding;

    public Producent () {}

    public Producent(String name, String country, Integer yearFounding) {
        this.name = name;
        this.country = country;
        this.yearFounding = yearFounding;
    }

    @Override
    public String toString() {
        return "Producent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", yearFounding=" + yearFounding +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
