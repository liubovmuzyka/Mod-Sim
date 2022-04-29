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
        Simulation Sim = new Simulation(entryTimeStamp, peopleInCar, eventList);
        Sim.run();
    }
}
