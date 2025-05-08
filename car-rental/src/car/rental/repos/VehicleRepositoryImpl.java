package car.rental.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import car.rental.models.Vehicle;
import car.rental.specs.ISpecification;

public class VehicleRepositoryImpl implements VehicleRepository {
    private final Map<Integer, Vehicle> vehicles = new HashMap<>();

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
        return vehicle;
    }

    @Override
    public Vehicle findVehicleById(String vehicleId) {
        return vehicles.get(Integer.parseInt(vehicleId));
    }

    @Override
    public List<Vehicle> searchVehiclesBySpecification(ISpecification<Vehicle> specification) {
        return vehicles.values().stream()
                .filter(specification::isSatisfied)
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
        return vehicle;
    }

    @Override
    public void delete(String vehicleId) {
        vehicles.remove(Integer.parseInt(vehicleId));
    }
}
