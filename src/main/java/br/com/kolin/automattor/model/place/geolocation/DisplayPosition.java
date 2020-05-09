package br.com.kolin.automattor.model.place.geolocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DisplayPosition {

    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Longitude")
    private Double longitude;

}
