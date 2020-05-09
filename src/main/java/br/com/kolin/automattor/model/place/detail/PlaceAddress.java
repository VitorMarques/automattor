package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceAddress {

    @JsonProperty("text")
    private String fullAddress;
    @JsonProperty("house")
    private String number;
    private String street;
    private String postalCode;
    private String district;
    private String city;
    private String state;
    private String country;
    private String countryCode;

}
