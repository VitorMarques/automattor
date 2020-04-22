package br.com.kolin.automattor.dto;

import br.com.kolin.automattor.model.Address;
import br.com.kolin.automattor.model.Contact;
import lombok.Data;

import java.util.UUID;

@Data
public class CompanyOut {

    private UUID uuid;
    private String name;
    private Double rating;
    private String category;
    private Address address;
    private Contact contact;

}
