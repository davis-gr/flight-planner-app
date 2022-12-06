package io.codelex.flightplanner.flights.objects;

import io.codelex.flightplanner.airports.objects.Airport;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Table(name = "flights")
public class Flight {

    @Transient
    private static Long counter = 1L;
    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name="airport_id_from", referencedColumnName = "id")
    private Airport from;
    @ManyToOne
    @JoinColumn(name="airport_id_to", referencedColumnName = "id")
    private Airport to;
    @Column
    private String carrier;
    @Column
    private LocalDateTime departureTime;
    @Column
    private LocalDateTime arrivalTime;

    public Flight(Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = counter++;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getDepartureTime() {
        return departureTime.format(formatter);
    }

    public String getArrivalTime() {
        return arrivalTime.format(formatter);
    }

    public boolean validDates() {
        return arrivalTime.isAfter(departureTime);
    }

    public boolean areFlightsEqual(Flight secondFlight) {
        return this.getFrom().equals(secondFlight.getFrom()) && this.getTo().equals(secondFlight.getTo()) && this.getCarrier().equals(secondFlight.getCarrier())
                && this.getDepartureTime().equals(secondFlight.getDepartureTime()) && this.getArrivalTime().equals(secondFlight.getArrivalTime());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", carrier='" + carrier + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return Objects.equals(getId(), flight.getId()) && Objects.equals(getFrom(), flight.getFrom()) && Objects.equals(getTo(), flight.getTo()) && Objects.equals(getCarrier(), flight.getCarrier()) && Objects.equals(getDepartureTime(), flight.getDepartureTime()) && Objects.equals(getArrivalTime(), flight.getArrivalTime()) && Objects.equals(formatter, flight.formatter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime(), formatter);
    }


}
