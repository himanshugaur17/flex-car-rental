package car.rental.service;

import java.util.List;

import car.rental.models.Location;
import car.rental.models.Vehicle;
import car.rental.models.enums.VehicleStatus;
import car.rental.repos.BookingRepository;
import car.rental.repos.LocationRepository;
import car.rental.repos.VehicleRepository;
import car.rental.requests.VehicleSearchCriteria;
import car.rental.specs.ISpecification;
import car.rental.specs.impl.VehicleAvailabilitySpec;
import car.rental.specs.impl.VehicleTypeSpecification;

public class VehicleInventoryServiceImpl implements VehicleInventoryService {
    private final LocationRepository locationRepository;
    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;

    public VehicleInventoryServiceImpl(LocationRepository locationRepository, VehicleRepository vehicleRepository,
            BookingRepository bookingRepository) {
        this.locationRepository = locationRepository;
        this.vehicleRepository = vehicleRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle, String locationId) {
        Location location = locationRepository.getLocationById(locationId);
        if (location == null) {
            throw new IllegalArgumentException("Location not found");
        }
        vehicle.setCurLocation(location);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle findVehicleById(String vehicleId) {
        return vehicleRepository.findVehicleById(vehicleId);
    }

    @Override
    public List<Vehicle> searchVehiclesByCriteria(VehicleSearchCriteria criteria) {
        return vehicleRepository.searchVehiclesBySpecification(buildSpecification(criteria));
    }

    private ISpecification<Vehicle> buildSpecification(VehicleSearchCriteria criteria) {
        ISpecification<Vehicle> spec = new VehicleAvailabilitySpec(bookingRepository, criteria.expectedPickUpTime(),
                criteria.expectedDropOffTime());
        ISpecification<Vehicle> vehicleTypeSpec = new VehicleTypeSpecification(criteria.vehicleType());
        return spec.and(vehicleTypeSpec);
    }

    @Override
    public void updateVehicleStatus(String vehicleId, VehicleStatus status) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        vehicle.setVehicleStatus(status);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicleLocation(String vehicleId, String locationId) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        Location location = locationRepository.getLocationById(locationId);
        if (location == null) {
            throw new IllegalArgumentException("Location not found");
        }
        vehicle.setCurLocation(location);
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle removeVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        vehicleRepository.delete(vehicleId);
        return vehicle;
    }

}
