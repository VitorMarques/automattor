package br.com.kolin.automattor.model.place.browse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrowseSearch {

    private Item[] items;
    @JsonProperty("next")
    private String nextPage;

}
