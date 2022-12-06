package io.codelex.flightplanner.airports;

import io.codelex.flightplanner.airports.objects.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "inmemory")
public class AirportInMemoryService implements AirportService {

    private AirportInMemoryRepository repository;

    public AirportInMemoryService(AirportInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Airport> getAirport(String search) {
        try {
            return repository.getAirport(search.trim().toLowerCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found!");
        }
    }

    @Override
    public Airport saveAirport(Airport airport) {
        return repository.saveAirport(airport);
    }


}
