package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.airports.AirportService;
import io.codelex.flightplanner.airports.objects.Airport;
import io.codelex.flightplanner.api.AddFlightRequest;
import io.codelex.flightplanner.api.SearchFlightsRequest;
import io.codelex.flightplanner.api.SearchFlightsResponse;
import io.codelex.flightplanner.flights.objects.Flight;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "database")
public class FlightDBService implements FlightService {

    private FlightDBRepository flightRepository;
    private AirportService airportService;

    public FlightDBService(FlightDBRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    @Override
    public synchronized Flight saveFlight(AddFlightRequest flightRequest) {
        Airport airportFrom = airportService.saveAirport(flightRequest.getFrom());
        Airport airportTo = airportService.saveAirport(flightRequest.getTo());
        Flight flight = new Flight(airportFrom, airportTo, flightRequest.getCarrier(), flightRequest.getDepartureTime(), flightRequest.getArrivalTime());
        for (Flight anyFlight : flightRepository.findAll()) {
            if (flight.areFlightsEqual(anyFlight)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "That flight already exists!");
            }
        }
        if (airportFrom.equals(airportTo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't fly to the same airport!");
        }
        if (!flight.validDates()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid departure/arrival times!");
        }
        return flightRepository.save(flight);
    }

    @Override
    public void clearFlights() {
        flightRepository.deleteAll();
    }

    @Override
    public void deleteFlight(Long flightId) {
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);
        }
    }

    @Override
    public Flight fetchFlight(Long flightId) {
        System.out.println(flightId);
        System.out.println(flightRepository.findById(flightId));
        return flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found!"));
    }

    @Override
    public List<Flight> getFlightList() {
        return flightRepository.findAll();
    }

    @Override
    public SearchFlightsResponse searchFlights(@RequestBody SearchFlightsRequest searchFlightsRequest) {
        SearchFlightsRequest searchRequest = new SearchFlightsRequest(searchFlightsRequest.getFrom(), searchFlightsRequest.getTo(), searchFlightsRequest.getDepartureDate());
        if (searchRequest.getFrom().equals(searchRequest.getTo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't fly to the same airport!");
        }
        List<Flight> searchResult = flightRepository.findAll().stream().filter(a -> a.getFrom().getAirport().equalsIgnoreCase(searchFlightsRequest.getFrom())
                || a.getTo().getAirport().equalsIgnoreCase(searchFlightsRequest.getTo())
                || a.getDepartureTime().equals(searchFlightsRequest.getDepartureDate())).toList();

        return new SearchFlightsResponse(searchResult, 0, searchResult.size());
    }
}
