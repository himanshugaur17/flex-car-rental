package car.rental.service;

import java.util.List;

import car.rental.models.Vehicle;
import car.rental.models.enums.VehicleStatus;
import car.rental.requests.VehicleSearchCriteria;

public interface VehicleInventoryService {
    Vehicle addVehicle(Vehicle vehicle, String locationId);

    Vehicle findVehicleById(String vehicleId);

    List<Vehicle> searchVehiclesByCriteria(VehicleSearchCriteria criteria);

    void updateVehicleStatus(String vehicleId, VehicleStatus status);

    void updateVehicleLocation(String vehicleId, String locationId);

    Vehicle removeVehicle(String vehicleId);

}
