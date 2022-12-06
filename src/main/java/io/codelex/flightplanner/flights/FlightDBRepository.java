package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.objects.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDBRepository extends JpaRepository <Flight, Long> {

}
