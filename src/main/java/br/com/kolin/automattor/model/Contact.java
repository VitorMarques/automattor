package br.com.kolin.automattor.model;

import lombok.Data;
import lombok.ToString;

import java.net.URI;

@Data
@ToString(includeFieldNames = false)
public class Contact {

    private URI website;
    private URI placeUrl;
    private String email;
    private String telefone;

}
