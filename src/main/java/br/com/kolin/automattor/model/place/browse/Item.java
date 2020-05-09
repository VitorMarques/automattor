package br.com.kolin.automattor.model.place.browse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "link")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String id;
    @JsonProperty("href")
    private String link;

}
