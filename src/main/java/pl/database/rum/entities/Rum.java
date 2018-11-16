package pl.database.rum.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rums")
public class Rum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Integer alcoholPercentage;

    @Enumerated(EnumType.STRING)
    TypeRum rumType;

    Float rating;

    String finish;

    Integer minimalAge;

}
