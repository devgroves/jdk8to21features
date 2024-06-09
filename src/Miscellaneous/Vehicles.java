package Miscellaneous;

public class Vehicles {

    abstract sealed static class Vehicle permits Car, Truck{

        private final String registrationNumber;

        public Vehicle(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

    }

    public static non-sealed class Car extends Vehicle implements Service, CarSafety {

        private final int numberOfSeats;

        public Car(int numberOfSeats, String registrationNumber) {
            super(registrationNumber);
            this.numberOfSeats = numberOfSeats;
        }

        public int getNumberOfSeats() {
            return numberOfSeats;
        }

        @Override
        public int getMaxServiceIntervalInMonths() {
            return 5;
        }

        @Override
        public int getMaxDistanceBetweenServicesInKilometers() {
            return Service.super.getMaxDistanceBetweenServicesInKilometers();
        }

        @Override
        public boolean isAirBalloonAvailable() {
            return true;
        }
    }

    public static final class Truck extends Vehicle implements Service {

        private final int loadCapacity;

        public Truck(int loadCapacity, String registrationNumber) {
            super(registrationNumber);
            this.loadCapacity = loadCapacity;
        }

        public int getLoadCapacity() {
            return loadCapacity;
        }

        @Override
        public int getMaxServiceIntervalInMonths() {
            return 0;
        }

        @Override
        public int getMaxDistanceBetweenServicesInKilometers() {
            return Service.super.getMaxDistanceBetweenServicesInKilometers();
        }
    }

    public class PorscheCar extends Car {
        public PorscheCar(int numberOfSeats, String registrationNumber) {
            super(numberOfSeats, registrationNumber);
        }
    }

}