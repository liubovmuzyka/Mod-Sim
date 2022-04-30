package teststation;

import java.util.ArrayList;
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
    }

    int amountOfCars = 1;
    int amountOfPeople = 1;
    int amountOfCarsWithNoPlace = 1;
    int amountOfTestingCars = 1;

    public void run(int capacity) {

        Random rand = new Random();
        int newArrival = 0;
        int entryTimeStamp = this.startTime;
        Event result = getThisEvent();

        while (eventList.events.size() > 0) {
            result.printLn(eventList);
            if (eventList.carIDs.size() < capacity) {
                int intervalNewArrival = (int) (Math.random()*90)+30;
                int newPeopleInCar = (int) (Math.random()*4)+1;
                newArrival = entryTimeStamp + intervalNewArrival;
                if (newArrival < 7200) {

                    Arriving newEvent = new Arriving(newArrival, newPeopleInCar);
                    amountOfCars++;
                    amountOfPeople += newPeopleInCar;
                    eventList.events.add(newEvent);
                    eventList.carIDs.add(newEvent.getCarID());
                    entryTimeStamp = newArrival;

                }
            } else {
                //System.out.println("The queue is full");
                amountOfCarsWithNoPlace++;
            }
            if (result.getClass().getName().equals("teststation.Testing")){
                amountOfTestingCars++;
            }
            addNewEvent(result);
            result = getThisEvent();

        }

        System.out.println("On average in one car are sitting: " + (double)amountOfPeople/amountOfCars +" people.");
        System.out.println(amountOfCarsWithNoPlace + " cars leave the testing station because the queue is full");
        //System.out.println("On average in the testing lane are: " + (double)amountOfTestingCars/amountOfCars +" cars.");

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
