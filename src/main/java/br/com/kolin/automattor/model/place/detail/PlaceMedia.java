package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceMedia {

    private PlaceImages images;
    private PlaceReviews reviews;
    private PlaceRatings ratings;

}
