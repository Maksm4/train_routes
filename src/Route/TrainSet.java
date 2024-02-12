package Route;

import Carts.PassengerCar;
import Carts.RailroadCar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TrainSet extends Thread {
    public static List<TrainSet> trains = new ArrayList<>();

    List<RailroadCar> wagons = new ArrayList<>();
    private final Locomotive locomotive;
    private double currentWeight;
    private final int ID;
    private static int idCounter;
    private double wholeDistanceToNext;
    private double distanceTravelledFrom1to2;
    private Station currStation;

    private double distanceTravelled;
    private  int currentGrids;

    public TrainSet(Locomotive locomotive) {
        this.locomotive = locomotive;
        currentGrids =0;
        currentWeight =0;
        this.currStation = locomotive.getHome();
        this.distanceTravelled = 0;
        ID = idCounter++;
        trains.add(this);
        currentWeight += this.locomotive.getWeight();

    }

    public void addRailroadCar(RailroadCar car)  {
        try {
            if (currentGrids + car.isElectricalGrid() > locomotive.getMaxElectricalGrids()) {
                throw new TrainSetException("Not enough electrical grids");
            }
            else if (wagons.size() == locomotive.getMaxRailroadCars()) {
                throw new TrainSetException("Already reached the maximum amount of cars");
            }
            else if (currentWeight + car.getTotalWeight() > locomotive.getMaxWeight()) {
                throw new TrainSetException("its too heavy");
            }
            else if (!car.isAdded()) {
                wagons.add(car);
                currentWeight += car.getTotalWeight();
                car.setAdded(true);
                if (car.hasElectricalGrid()) currentGrids++;
            }
            else {
                throw new TrainSetException("this car is already added to other train");
            }
        }catch (TrainSetException e){
            System.out.println("Cant add railroad car because of: " + e.getMessage());
        }
    }

    public void removeCar(RailroadCar car){
        wagons.remove(car);
        currentWeight -= car.getTotalWeight();
        if (car.hasElectricalGrid()) currentGrids --;
        car.setAdded(false);
    }

    @Override
    public  void  run() {
        int currIndex = 0;
        locomotive.routeDetermination();
        boolean flag = false;
        if (currIndex == locomotive.getDistances().size()) flag = true;
        double startingSpeed = locomotive.getSpeed();
        double distanceToNext = locomotive.getDistances(currIndex);
        wholeDistanceToNext = locomotive.getDistances(currIndex);

        while (!currentThread().isInterrupted()){


            Random rand = new Random();
            int random = rand.nextInt(2);

            if (random == 1) locomotive.setSpeed(locomotive.getSpeed()*1.03);
            else   locomotive.setSpeed(locomotive.getSpeed()/1.03);

            if (locomotive.getSpeed() >= 200){
                try{
                    throw new RailroadHazard("Railroad Hazard 200km/h + in train of ID "
                            + this.ID + "with " + wagons.size() + "wagons attached");

                }catch (RailroadHazard e){
                    System.out.println(e.getMessage());
                    System.out.println(this.ID + " makes an emergency break!");
                    for (RailroadCar car: wagons) {
                        System.out.println(car.getSecurityInfo());
                    }
                    locomotive.setSpeed(0);
                    try {
                        currentThread().sleep(10000);
                        locomotive.setSpeed(startingSpeed);
                        System.out.println(this.ID + " going back on track");
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }

            distanceTravelled += locomotive.getSpeed();
            distanceToNext -= locomotive.getSpeed();
            distanceTravelledFrom1to2 = locomotive.getDistances(currIndex) - distanceToNext;


            try {
                currentThread().sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

          if1:  if (distanceToNext <= 0) {
                locomotive.setSpeed(0);
                stopAtStation(currStation);
                this.currStation = locomotive.getRoute(++currIndex);
                if (currIndex == locomotive.getDistances().size()) {
                    flag = true;
                    break if1;
                }
                distanceToNext = locomotive.getDistances(currIndex);
                wholeDistanceToNext = locomotive.getDistances(currIndex);
                distanceTravelledFrom1to2 =0;
                locomotive.setSpeed(startingSpeed);
            }

            if (locomotive.getDistanceToDestination() <= distanceTravelled) {
                try {
                    System.out.println("train " + this.ID + " has reached it's destination");
                    currentThread().sleep(30000);
                    System.out.println("train " + this.ID + " starts another route");
                    locomotive.setDistances(new LinkedList<>());
                    locomotive.setRoute(null);
                    locomotive.setHome(locomotive.getDestination());
                    locomotive.routeDetermination();
                    distanceTravelled = 0;
                    currIndex = 0;
                    locomotive.setSpeed(startingSpeed);
                    distanceToNext = locomotive.getDistances(currIndex);
                    wholeDistanceToNext = locomotive.getDistances(currIndex);
                    distanceTravelledFrom1to2 = 0;
                    locomotive.setSpeed(startingSpeed);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (flag){
                currentThread().interrupt();
            }
        }
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public static void saveToFile(){
        trains.sort(Comparator.comparing(TrainSet::routeLength).reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("AppState.txt"))) {
            for (TrainSet trainSet : trains) {
                trainSet.wagons.sort(RailroadCar::compareTo);
                int travelledInPercent = (int)(trainSet.distanceTravelled / trainSet.locomotive.getDistanceToDestination() * 100);
                int travelledInPercentToNext = (int)(trainSet.distanceTravelledFrom1to2 / trainSet.wholeDistanceToNext * 100);
                if (travelledInPercentToNext > 100) travelledInPercentToNext = 100;
                if (travelledInPercent >= 100) {
                    travelledInPercent = 100;
                    travelledInPercentToNext = 100;
                }
                writer.write(" |train ID: " +trainSet.ID + " |weight: " + (int)(trainSet.getCurrentWeight())
                        +" |distance travelled to next station: " + travelledInPercentToNext
                        + "% |distance travelled to destination " + travelledInPercent
                        + "% |current speed: " + (int)(trainSet.locomotive.getSpeed())
                        + " km/h |Route Length: " + trainSet.routeLength()
                        + " m |railroadS cars attached: " + trainSet.wagons.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopAtStation(Station station){
        synchronized (station) {
            try {
                System.out.println("train " + this.ID + " has stopped at " + station.getName());
                Thread.sleep(2000);
                for (RailroadCar car : wagons) {
                    if (car instanceof PassengerCar) {
                        ((PassengerCar) car).unloadPassengers();
                        ((PassengerCar) car).loadPassengers();
                        System.out.println(((PassengerCar) car).getSeatsTaken() + " Passengers on board of train: " + this.ID);
                    }
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getID() {
        return ID;
    }
    public int routeLength(){
        int sum =0;
        for (int i = 0; i < locomotive.getDistances().size()-1; i++) {
            sum += locomotive.getDistances(i);
        }
        return sum;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public boolean isMaxRailroadCars(){
        return wagons.size() == locomotive.getMaxRailroadCars();
    }
}


