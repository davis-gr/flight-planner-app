package io.codelex.flightplanner.airports.objects;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airports")
public class Airport {

    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @Id
    @Column(name = "id")
    @NotBlank
    private String airport;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAirport() {
        return airport;
    }

    public void setCountry(String country) {
        this.country = country.trim();
    }

    public void setCity(String city) {
        this.city = city.trim();
    }

    public void setAirport(String airport) {
        this.airport = airport.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airport='" + airport + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Airport airport1 = (Airport) o;
        return airport != null && Objects.equals(airport, airport1.airport);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
