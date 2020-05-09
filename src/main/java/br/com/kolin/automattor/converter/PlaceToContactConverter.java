package br.com.kolin.automattor.converter;

import br.com.kolin.automattor.model.Contact;
import br.com.kolin.automattor.model.place.detail.PlaceDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlaceToContactConverter implements Function<PlaceDetail, Contact> {

    @Override
    public Contact apply(PlaceDetail placeDetail) {

        br.com.kolin.automattor.model.place.detail.Contact[] phone = placeDetail.getContacts().getPhone();
        br.com.kolin.automattor.model.place.detail.Contact[] website = placeDetail.getContacts().getWebsite();
        br.com.kolin.automattor.model.place.detail.Contact[] email = placeDetail.getContacts().getEmail();

        Contact contact = new Contact();
        contact.setPlaceUrl(placeDetail.getPlaceUrl());
        contact.setTelefone((phone != null && phone.length > 0) ? phone[0].getValue() : "");
        contact.setWebsite((website != null && website.length > 0) ? URI.create(website[0].getValue()) : URI.create(""));
        contact.setEmail((email != null && email.length > 0) ? email[0].getValue() : "");

        return contact;
    }
}
