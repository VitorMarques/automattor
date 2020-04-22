package br.com.kolin.automattor.model.place.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceGeolocationResponse {

    @JsonProperty("View")
    private View[] views;

}
