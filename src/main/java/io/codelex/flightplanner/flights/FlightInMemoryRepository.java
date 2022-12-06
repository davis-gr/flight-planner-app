package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.flights.objects.Flight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightInMemoryRepository {


    private List<Flight> flightList = new ArrayList<>();

    public FlightInMemoryRepository() {
    }

    public synchronized void saveFlight(Flight flight) {
        flightList.add(flight);
    }

    public synchronized void deleteFlight(Long flightId) {
        flightList.removeIf(flight -> flight.getId().equals(flightId));
    }

    public Optional<Flight> fetchFlight(Long flightId) {
        return flightList.stream().filter(f -> f.getId().equals(flightId)).findFirst();
    }

    public void clearFlights() {
        flightList.clear();
    }

    public synchronized List<Flight> getFlightList() {
        return flightList;
    }
}
