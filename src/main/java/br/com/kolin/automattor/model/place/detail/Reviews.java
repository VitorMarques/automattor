package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reviews {

    private String href;
    private String date;
    private String title;
    private Integer rating;
    private String description;

}
