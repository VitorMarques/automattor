package br.com.kolin.automattor.model;

import br.com.kolin.automattor.model.place.PlaceType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "companiessearch")
public class CompanySearch {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private UUID uuid;
    private String queryText;
    private PlaceType placeType;
    private Status searchStatus;

    public enum Status {
        NOTDONE,
        SUCCESS,
        NO_RESULT,
        OVER_LIMIT,
        ERROR,
    }
}
