package br.com.kolin.automattor.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

@Data
@ToString(includeFieldNames = false, exclude = "coordinates")
public class Address {

    private String country;
    private String city;
    private String state;
    private String number;
    private String street;
    private String postalCode;
    private String neighborhood;
    private String complementary;
    private String fullAddress;

    @GeoSpatialIndexed
    private Point coordinates;

}
