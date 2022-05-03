package teststation;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File f = new File("full-logger.log");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
        int capacity = 10;
        int entryTimeStamp = 0;
        int peopleInCar = 1;
        PriorityQueue<Event> events = new PriorityQueue<>(new EventComparison());
        ArrayList<Integer> carIDs = new ArrayList<>();
        Eventlist eventList = new Eventlist(events, carIDs);

        Simulation sim = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("Simulation with " + capacity + " places capacity:");
        sim.run(capacity);
        sim.setAmountOfArrivingTimeAndLeaving();
        System.out.println("1. On average in one car are sitting: " + (double) sim.amountOfPeople / sim.amountOfCars + " people."); // 1.3.1
        System.out.println("2. On average in the testing lane are: " + (double) sim.amountOfCarsInLane / 8023 + " cars."); // 1.3.2
        System.out.println("3. " + sim.amountOfCarsWithNoPlace + " cars leave the testing station because the queue is full"); //1.3.3

        System.out.println("Dwell time for all cars: " + sim.amountOfDwellTime.toString()); //1.3.2 addition

        /*sim.amountOfArrivingTime.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByKey())
                .forEach(System.out::println);

        sim.amountOfLeavingTime.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByKey())
                .forEach(System.out::println);*/

    }
}
