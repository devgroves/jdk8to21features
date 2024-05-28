package Miscellaneous;

public sealed interface Service permits Vehicles.Car, Vehicles.Truck {
    int getMaxServiceIntervalInMonths();

    default int getMaxDistanceBetweenServicesInKilometers() {
        return 100000;
    }
}
