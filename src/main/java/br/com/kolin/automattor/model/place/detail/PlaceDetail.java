package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URI;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetail {

    private String name;
    private String placeId;
    @JsonProperty("view")
    private URI placeUrl;
    private PlaceLocation location;
    private PlaceContacts contacts;
    private Extended extended;
    private Category[] categories;
    private PlaceError error;
    private PlaceMedia media;

}
