package car.rental.models;

import car.rental.models.enums.UserRole;

public record User(String name, String id, UserRole role) {

}
