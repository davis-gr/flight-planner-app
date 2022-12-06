package io.codelex.flightplanner.airports;

import io.codelex.flightplanner.airports.objects.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportDBRepository extends JpaRepository <Airport, String> {

    @Query("SELECT a FROM Airport a WHERE lower(a.airport) LIKE ?1% or lower(a.city) LIKE ?1% or lower(a.country) LIKE ?1%")
    List<Airport> findAirport(String searchPhrase);
}