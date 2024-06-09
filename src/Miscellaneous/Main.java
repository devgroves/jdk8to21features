package Miscellaneous;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.LongStream;

import static java.lang.StringTemplate.STR;

public class Main {
    public static void main(String[] args) {
        Vehicles vehicles = new Vehicles();
        Vehicles.Car c1 = new Vehicles.Car(4, "TN39B8430");
        class ElectricCar extends Vehicles.Car {

            public ElectricCar(int numberOfSeats, String registrationNumber) {
                super(numberOfSeats, registrationNumber);
            }
        }
        System.out.println("CAR - number of seats : " + c1.getNumberOfSeats() );
        System.out.println("CAR - registration number : " + c1.getRegistrationNumber() );
        System.out.println("Hello world!");

        Object v1 = new ElectricCar(2, "TN45BA5432");

        //Testing for the record keyword - pojo of java 21 version..
        Customer customer = new Customer("lavs", 1);
        System.out.println("customer id" + customer.customerId());
        System.out.println("customer name " + customer.customerName());
        PrivilegedCustomer privilegedCustomer = new PrivilegedCustomer(customer);
        Object customerObj = new PrivilegedCustomer(customer);
        String customerType = switch (customerObj) {
            case Customer custObj1 -> "This is a customre";
            case PrivilegedCustomer privilegedCustomer1 -> "This is a privileged customer";
            default -> "Unrecognized customer type";
        };
        System.out.println("Identified customer object .. " + customerType);

        //checking date format for hash collision..
        LocalDate l1 = LocalDate.parse("2024-05-17");
        LocalDate l2 = LocalDate.parse("2024-05-17");
        HashMap<LocalDate, String> salesData = new HashMap<>();
        salesData.put(l1, "l1");
        salesData.put(l2, "l2");
        System.out.println("sales data size " + salesData.size());
        System.out.println("sales data size " + salesData);

        // Pattern matching in switch cases..
        System.out.println("priviledged customername  " + privilegedCustomer.c1().customerName());
        String resp = switch (v1) {
            case ElectricCar ec1 -> "This is electric car";
            case Vehicles.Truck t1 -> "This is a truck";
            case null -> "It is null";
            default -> "It is just an object";
        };
        System.out.println("identified the type of object!!" + resp);

        // string template substitution..
        String name = "Baeldung";
        String welcomeText = STR."Welcome to \{name} - \{privilegedCustomer}";
        System.out.println("welcome text " + welcomeText);

        RandomGeneratorFactory.all()
                .map(f -> f.name())
                .sorted()
                .forEach(n -> System.out.println(n));

        RandomGenerator random1 = RandomGenerator.of("Random");
        random1.longs().forEach(new LongConsumer() {
            @Override
            public void accept(long value) {
                System.out.println("value " + value);
            }
        });

        // exploring sequenced collections..
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        numbers.removeFirst();
        System.out.println("after removing first element, first element is " + numbers.getFirst() + " and size is " + numbers.size());
        numbers.removeLast();
        System.out.println("after removing first element, last element is " + numbers.getLast());
        System.out.println("Printing the reversed array list : first element:  " + numbers.reversed().getFirst() + " last element: " + numbers.reversed().getLast());
        System.out.println("Printing the complete list of elements " + numbers);

        Object voterObj = new Voter("Parmod", 35);
        switch (voterObj) {
            case Voter(String cn, Integer age) -> System.out.println("it is voter object" + cn);
            default -> System.out.println("it is not recognized object");
        }

        Thread vThread = Thread.startVirtualThread(() -> {
            //Code to execute in virtual thread
            System.out.println("Inside Runnable");
        });
    }
}