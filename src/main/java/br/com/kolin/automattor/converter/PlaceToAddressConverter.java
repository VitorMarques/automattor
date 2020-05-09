package br.com.kolin.automattor.converter;

import br.com.kolin.automattor.model.Address;
import br.com.kolin.automattor.model.place.detail.PlaceAddress;
import br.com.kolin.automattor.model.place.detail.PlaceDetail;
import br.com.kolin.automattor.model.place.detail.PlaceLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlaceToAddressConverter implements Function<PlaceDetail, Address> {

    private static final int LATITUDE = 0;
    private static final int LONGITUDE = 1;

    @Override
    public Address apply(PlaceDetail placeDetail) {

        Address address = new Address();
        PlaceAddress location = placeDetail.getLocation().getAddress();
        Double[] position = placeDetail.getLocation().getPosition();

        address.setCity(location.getCity());
        address.setState(location.getState());
        address.setNumber(location.getNumber());
        address.setStreet(location.getStreet());
        address.setPostalCode(location.getPostalCode());
        address.setNeighborhood(location.getDistrict());
        address.setCountry(location.getCountry());
        address.setFullAddress(location.getFullAddress());
        address.setCoordinates(new Point(position[LATITUDE], position[LONGITUDE]));

        return address;
    }
}
