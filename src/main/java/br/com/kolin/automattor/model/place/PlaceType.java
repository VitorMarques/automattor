package br.com.kolin.automattor.model.place;

public enum PlaceType {

    EAT("eat-drink"),
    GOING_OUT("going-out"),
    SIGHTS_MUSEUMS("sights-museums"),
    NATURAL_GEOGRAPHICAL("natural-geographical"),
    TRANSPORT("transport"),
    ACCOMMODATION("accommodation"),
    LEISURE_OUTDOOR("leisure-outdoor"),
    SHOPPING("shopping"),
    BUSINESS_SERVICES("business-services"),
    FACILITIES("facilities"),
    AREAS_BUILDINGS("administrative-areas-buildings");
    /*FAST_FOOD("snacks-fast-food"),
    BAR("bar-pub"),
    CAFE("coffee"),
    NIGHT_CLUB("dance-night-club"),
    CINEMA("cinema"),
    THEATRE("theatre-music-culture"),
    CASINO("casino"),
    MUSEUMS("museum"),
    AIRPORT("airport"),
    RAILWAY("railway-station"),
    PUBLIC_TRANSPORT("public-transport"),
    FERRY("ferry-terminal"),
    TAXI("taxi-stand"),
    HOTEL("hotel"),
    MOTEL("motel"),
    HOSTEL("hostel"),
    CAMPING("camping"),
    SHOPPING("shopping"),
    CONVENIENCE("kiosk-convenience-store"),
    WINE("wine-and-liquor"),
    MALL("mall"),
    DEPARTMENT_STORE("department-store"),
    BOOKSHOP("bookshop"),
    PHARMACY("pharmacy"),
    ELETRONICS_SHOP("electronics-shop"),
    CLOTHING_ACCESSORIES("clothing-accessories-shop"),
    SHOP("shop"),
    ATM("atm-bank-exchange"),
    POLICE("police-station"),
    AMBULANCE("ambulance-services"),
    FIRE_DEPARTMENT("fire-department"),
    POST_OFFICE("post-office"),
    PETROL_STATION("petrol-station"),
    CAR_RENTAL("car-rental"),
    CAR_REPAIR("car-dealer-repair"),
    TRAVEL_AGENCY("travel-agency"),
    COMMUNICATION("communication-media"),
    HOSPITAL("hospital-health-care-facility"),
    EDUCATION("education-facility"),
    LIBRARY("library"),
    CONVENTIONS("fair-convention-facility"),
    PARKING("parking-facility");*/

    private String placeType;

    private PlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String toString() {
        return this.placeType;
    }
}
