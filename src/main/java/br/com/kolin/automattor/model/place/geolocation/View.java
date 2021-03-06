package br.com.kolin.automattor.model.place.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class View {

    @JsonProperty("Result")
    private Result[] results;

}
