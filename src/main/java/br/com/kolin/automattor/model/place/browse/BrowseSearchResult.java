package br.com.kolin.automattor.model.place.browse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrowseSearchResult {

    private BrowseSearch results;

}
