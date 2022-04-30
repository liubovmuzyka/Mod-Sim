package teststation;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File f = new File("full-logger.log");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
        int entryTimeStamp = 0;
        int peopleInCar = 1;
        PriorityQueue<Event> events = new PriorityQueue<>(new EventComparison());
        ArrayList<Integer> carIDs = new ArrayList<>();
        Eventlist eventList = new Eventlist(events, carIDs);

        Simulation sim = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("Simulation with 10 places capacity");
        sim.run(10);
        sim.setAmountOfArrivingTimeAndLeaving();
        System.out.println("On average in one car are sitting: " + (double) sim.amountOfPeople / sim.amountOfCars + " people."); // 1.3.1
        System.out.println("On average in the testing lane are: " + (double) sim.amountOfTestingCars / sim.amountOfIteration + " cars."); // 1.3.2
        System.out.println(sim.amountOfCarsWithNoPlace + " cars leave the testing station because the queue is full"); //1.3.3

        System.out.println("Dwell time: " + sim.amountOfDwellTime.toString()); //1.3.2 addition

      /*  System.out.println("---------------------------------------------------");
        Simulation sim2 = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("Simulation with 14 places capacity");
        sim2.run(14);
        System.out.println("Percentage of cars who leave the testing station directly with 14 capacity: "
                + (100 - ((double) (sim2.amountOfCarsWithNoPlace * 100) / sim2.amountOfCarsWithNoPlace)));*/


        /*  System.out.println("---------------------------------------------------");
        Simulation sim3 = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("Simulation with 20 places capacity");
        sim3.run(20);
        System.out.println("Percentage of cars who leave the testing station directly with 20 capacity: "
                + (100 - ((double) (sim3.amountOfCarsWithNoPlace * 100) / sim3.amountOfCarsWithNoPlace)));*/
    }
}
