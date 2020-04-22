package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceImages {

    private Integer available;
    private Images[] items;

}
