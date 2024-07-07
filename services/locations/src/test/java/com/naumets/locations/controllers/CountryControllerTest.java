import com.naumets.locations.controllers.CountryController;
import com.naumets.locations.models.Country;
import com.naumets.locations.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CountryControllerTest {

    @InjectMocks
    private CountryController countryController;

    @Mock
    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Country> countries = Arrays.asList(new Country(), new Country());
        when(countryService.findAll()).thenReturn(countries);

        List<Country> response = countryController.findAll();

        assertEquals(countries, response);
    }

    @Test
    public void testFindById() {
        Country country = new Country();
        Optional<Country> optionalCountry = Optional.of(country);
        when(countryService.findById(1)).thenReturn(optionalCountry);

        Optional<Country> response = countryController.findById(1);

        assertEquals(optionalCountry, response);
    }

    @Test
    public void testAddNew() {
        Country country = new Country();
        when(countryService.save(country)).thenReturn(country);

        ResponseEntity<Country> response = countryController.addNew(country);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(country, response.getBody());
    }

    @Test
    public void testUpdate() {
        Country country = new Country();
        when(countryService.save(country)).thenReturn(country);

        Country response = countryController.update(country);

        assertEquals(country, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(countryService).delete(1);

        ResponseEntity<Void> response = countryController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(countryService, times(1)).delete(1);
    }
}
