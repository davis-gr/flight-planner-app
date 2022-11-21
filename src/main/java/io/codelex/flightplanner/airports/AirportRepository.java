package io.codelex.flightplanner.airports;

import io.codelex.flightplanner.airports.objects.Airport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirportRepository {

    public List<Airport> airportList = new ArrayList<>();

    public AirportRepository() {
    }

    public List<Airport> getAirport(String search) {
        return airportList.stream().filter(a -> a.getCity().toLowerCase().contains(search)
                || a.getCountry().toLowerCase().contains(search)
                || a.getAirport().toLowerCase().contains(search)).findFirst().stream().toList();
    }

    public List<Airport> getAirportList() {
        return airportList;
    }

    public void saveAirport(Airport airport) {
        airportList.add(airport);
    }

}
