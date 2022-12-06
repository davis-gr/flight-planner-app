package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.airports.AirportService;
import io.codelex.flightplanner.api.AddFlightRequest;
import io.codelex.flightplanner.api.SearchFlightsRequest;
import io.codelex.flightplanner.api.SearchFlightsResponse;
import io.codelex.flightplanner.flights.objects.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public interface FlightService {

    public Flight saveFlight(AddFlightRequest flightRequest);

    public void clearFlights();

    public void deleteFlight(Long flightId);

    public Flight fetchFlight(Long flightId);

    public List<Flight> getFlightList();

    public SearchFlightsResponse searchFlights(@RequestBody SearchFlightsRequest searchFlightsRequest);
}
