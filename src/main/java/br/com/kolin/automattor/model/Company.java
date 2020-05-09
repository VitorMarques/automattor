package br.com.kolin.automattor.model;

import br.com.kolin.automattor.model.place.detail.Reviews;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;
import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "companies")
public class Company implements Codded {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private UUID uuid;
    @Indexed(unique = true)
    private String placeId;
    private String name;
    private URI icon;
    private Double rating;
    private Integer userRatingTotal;
    private String category;
    private Address address;
    private Contact contact;
    private String openingHours;
    private Status status;
    private String[] images;
    private Reviews[] reviews;

    public enum Status {
        ENABLED, DISABLED,
    }

}
