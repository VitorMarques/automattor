package br.com.kolin.automattor.model.place.geolocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceGeolocationResult {

    @JsonProperty("Response")
    private PlaceGeolocationResponse response;

}
