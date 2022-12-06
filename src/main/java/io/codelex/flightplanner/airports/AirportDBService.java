package io.codelex.flightplanner.airports;

import io.codelex.flightplanner.airports.objects.Airport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@ConditionalOnProperty(prefix = "flightplanner", name = "appmode", havingValue = "database")
public class AirportDBService implements AirportService {


    private AirportDBRepository repository;

    public AirportDBService(AirportDBRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Airport> getAirport(String search) {
        try {
            return repository.findAirport(search.toLowerCase().trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found!");
        }
    }

    @Override
    public Airport saveAirport(Airport airport) {
        Optional<Airport> maybeAirport = repository.findById(airport.getAirport());
        return maybeAirport.orElseGet(() -> repository.save(airport));
    }


}
