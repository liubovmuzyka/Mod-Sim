package teststation;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Timestamp;
import java.util.Queue;

public class Leaving extends Event

{
    static Logger logger = Logger.getLogger(Arriving.class);
    public Leaving(int  timeStamp, int carID, int peopleInCar)
    {
        super(timeStamp, carID, peopleInCar);
    }

    @Override
    public Event generateFutureEvent(int entryTimeStamp, int carID, int peopleInCar)
    {
        return null;
    }

    @Override
    public void printLn(Eventlist eventList) {
        PropertyConfigurator.configure("log4j.properties");

        logger.info("The car "+ this.getCarID() + " is leaving test station at " + this.getEntryTimeStamp() + " sek, with " + this.getPeopleInCar() + " people " + "["+this.getClass().getName() + "]");

        //System.out.println("Leaving car "+ this.getCarID());
    }

    @Override
    public void printLnNoSpace(Eventlist eventList) {

    }

}
