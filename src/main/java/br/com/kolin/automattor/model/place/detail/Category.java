package br.com.kolin.automattor.model.place.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.net.URI;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    private String id;
    private String title;
    private URI icon;

}
