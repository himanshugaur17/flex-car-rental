package car.rental.models;

import car.rental.models.enums.VehicleStatus;
import car.rental.models.enums.VehicleType;

public class Vehicle {
    private final int vehicleId;
    private final String name;
    private final String description;
    private final String imageUrl;
    private final VehicleType vehicleType;
    private Location curLocation;
    private VehicleStatus vehicleStatus;

    public Vehicle(int vehicleId, String name, String description, String imageUrl, VehicleType vehicleType,
            Location curLocation, VehicleStatus vehicleStatus) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.vehicleType = vehicleType;
        this.curLocation = curLocation;
        this.vehicleStatus = vehicleStatus;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Location getCurLocation() {
        return curLocation;
    }

    public void setCurLocation(Location curLocation) {
        this.curLocation = curLocation;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
