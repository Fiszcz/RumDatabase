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
}
