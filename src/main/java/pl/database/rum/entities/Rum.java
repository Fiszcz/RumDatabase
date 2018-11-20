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

    String rumType;

    Double rating;

    String finish;

    Integer minimalAge;

    @ManyToOne
    private Producent producent;

    public Rum() {}

    public Rum(String name, Integer alcoholPercentage, String rumType, Double rating, String finish, Integer minimalAge, Producent producent) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.rumType = rumType;
        this.rating = rating;
        this.finish = finish;
        this.minimalAge = minimalAge;
        this.producent = producent;
    }

    @Override
    public String toString() {
        return "Rum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                ", rumType='" + rumType + '\'' +
                ", rating=" + rating +
                ", finish='" + finish + '\'' +
                ", minimalAge=" + minimalAge +
                ", producent=" + producent +
                '}';

    }

    public void setID(long id){
        this.id = id;
    }
}
