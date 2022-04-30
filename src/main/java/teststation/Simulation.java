package teststation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

public class Simulation {
    Eventlist eventList;
    int startTime;
    int peopleInCar;


    Simulation(int entryTimeStamp, int peopleInCar, Eventlist eventList) {
        startTime = entryTimeStamp;
        this.peopleInCar = peopleInCar;
        this.eventList = eventList;
        Arriving newEvent = new Arriving(this.startTime, this.peopleInCar);
        eventList.events.add(newEvent);
        eventList.carIDs.add(newEvent.getCarID());
        amountOfArrivingTime.put(newEvent.getCarID(), (this.startTime));
        newEvent.printLn(eventList);
    }

    int amountOfCars = 1;
    int amountOfPeople = 1;
    int amountOfCarsWithNoPlace = 0;
    int amountOfTestingCars = 1;
    int amountOfIteration = 1;
    HashMap<Integer, Integer> amountOfArrivingTime = new HashMap<>();
    HashMap<Integer, Integer> amountOfLeavingTime = new HashMap<>();
    HashMap<Integer, Pair<Integer, Integer>> amountOfArrivingTimeAndLeaving = new HashMap<>();
    ArrayList<Integer> amountOfDwellTime = new ArrayList<>();

    public void setAmountOfArrivingTimeAndLeaving() {
        amountOfArrivingTime.forEach((k, v) -> {
            amountOfArrivingTimeAndLeaving.put(k, new Pair<>(v, amountOfLeavingTime.get(k)));
        });

        amountOfArrivingTimeAndLeaving.forEach((k, v) -> amountOfDwellTime.add(v.getV() - v.getU()));
        //System.out.println(amountOfArrivingTime.size());
        //System.out.println(amountOfArrivingTime.toString());
        //System.out.println(amountOfLeavingTime.toString());
    }

    public void run(int capacity) {

        int newArrival = 0;
        int entryTimeStamp = this.startTime;
        Event result = getThisEvent();

        while (eventList.events.size() > 0) {
            amountOfIteration++;
            if (result instanceof Testing || result instanceof Leaving) {
                result.printLn(eventList);
            }
            if (result instanceof Leaving) {
                amountOfLeavingTime.put(result.getCarID(), result.getEntryTimeStamp());
            }
            if (result instanceof Testing) {
                amountOfTestingCars++;
            }
            int intervalNewArrival = (int) (Math.random() * 90) + 30;
            int newPeopleInCar = (int) (Math.random() * 4) + 1;
            newArrival = entryTimeStamp + intervalNewArrival;
            Arriving newEvent = new Arriving(newArrival, newPeopleInCar);
            if (eventList.carIDs.size() < capacity) {
                if (newArrival < 7200) {
                    eventList.events.add(newEvent);
                    eventList.carIDs.add(newEvent.getCarID());
                    newEvent.printLn(eventList);

                    amountOfArrivingTime.put(newEvent.getCarID(), newArrival);
                    amountOfCars++;
                    amountOfPeople += newPeopleInCar;

                    entryTimeStamp = newArrival;
                }
            } else {
                amountOfCars++;
                amountOfPeople += newPeopleInCar;
                //amountOfArrivingTime.put(newEvent.getCarID(), newArrival);
                // amountOfLeavingTime.put(newEvent.getCarID(), newArrival);
                newEvent.printLnNoSpace(eventList);
                amountOfCarsWithNoPlace++;
            }

            addNewEvent(result);
            result = getThisEvent();
        }

    }


    public Event getThisEvent() {
        return eventList.events.peek();
    }

    public void addNewEvent(Event event) {
        Event newEvent = event.processEvent(eventList);
        if (newEvent != null) {
            eventList.events.add(newEvent);
        }
    }

}
