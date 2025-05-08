package car.rental.repos;

import car.rental.models.User;

public interface UserRepository {

    User findUserById(String userId);

}
