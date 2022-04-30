package teststation;

import java.io.File;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        File f = new File("full-logger.log");
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
        int entryTimeStamp = 0;
        int peopleInCar = 1;
        PriorityQueue<Event> events = new PriorityQueue<>(new EventComparison());
        ArrayList<Integer> carIDs = new ArrayList<Integer>();
        Eventlist eventList = new Eventlist(events, carIDs);
        Simulation sim = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("Simulation with 10 places capacity");
        sim.run(10);
        Simulation sim2 = new Simulation(entryTimeStamp, peopleInCar, eventList);
        System.out.println("---------------------------------------------------");
        System.out.println("Simulation with 20 places capacity");
        sim2.run(20);
        System.out.println(100-((double)(sim2.amountOfCarsWithNoPlace*100)/sim.amountOfCarsWithNoPlace));
    }
}
