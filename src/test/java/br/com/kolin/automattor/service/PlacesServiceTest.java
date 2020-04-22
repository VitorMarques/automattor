package br.com.kolin.automattor.service;

import br.com.kolin.automattor.model.place.PlaceType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlacesServiceTest {

    @Autowired
    private PlacesService service;

    @Test
    public void shouldGetPlacesByTextSearch() {
        service.getCompaniesByLocationAndType("Madureira", PlaceType.EAT);
    }
}
