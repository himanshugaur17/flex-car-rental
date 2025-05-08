package car.rental.repos;

import java.util.List;

import car.rental.models.Location;

public interface LocationRepository {
    Location addLocation(Location location);

    List<Location> getAllLocations();

    Location getLocationById(String locationId);

    void deleteLocation(String locationId);
}
