package car.rental.repos;

import java.util.List;

import car.rental.models.Vehicle;
import car.rental.specs.ISpecification;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);

    Vehicle findVehicleById(String vehicleId);

    List<Vehicle> searchVehiclesBySpecification(ISpecification<Vehicle> specification);

    Vehicle update(Vehicle vehicle);

    void delete(String vehicleId);
}
