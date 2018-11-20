package pl.database.rum.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="rums")
public class Rum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Integer alcoholPercentage;

    @Enumerated(EnumType.STRING)
    TypeRum rumType;

    Float rating;

    String finish;

    Integer minimalAge;

    @ManyToOne
    private Producent producent;

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.alcoholPercentage + " " + this.rumType;
    }
}
