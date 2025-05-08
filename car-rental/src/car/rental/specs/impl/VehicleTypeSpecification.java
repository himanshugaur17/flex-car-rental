package car.rental.specs.impl;

import car.rental.models.Vehicle;
import car.rental.models.enums.VehicleType;
import car.rental.specs.ISpecification;

public class VehicleTypeSpecification implements ISpecification<Vehicle> {
    private final VehicleType vehicleType;

    public VehicleTypeSpecification(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean isSatisfied(Vehicle item) {
        return item.getVehicleType() == vehicleType;
    }
}
