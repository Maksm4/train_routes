package Route;

import java.util.*;

public class Locomotive {
    private String name;
    private Station home;
    private Station destination;
    private LinkedList<Station> route;
   private LinkedList<Integer> distances;
    private double distanceToDestination;

    private double speed;

    private final int  maxRailroadCars;
    private int maxElectricalGrids;
    private double maxWeight;

    private final int ID;
    private static int idCounter;

    public Locomotive(String name, Station home, double speed)  {
        this.name = name;
        this.home = home;
        this.speed = speed;
        this.ID = idCounter++;
        this.route = new LinkedList<>();
        Random rand = new Random();
        this.distances =  new LinkedList<>();
        maxWeight = rand.nextDouble(10000) + 5000;
        maxRailroadCars = rand.nextInt(6) + 5;
        maxElectricalGrids = rand.nextInt(6) + 1;
    }


    public void routeDetermination() {
        LinkedList<Station> route = new LinkedList<>();
        route.add(this.home);
        int currConnections = 1;
        Random rand = new Random();
        int maxConnections = rand.nextInt(Station.stations.size());
        while (currConnections < maxConnections) {
            Station currStation = route.getLast();
            Map<Station, Integer> nextStations = Station.connections.get(currStation.getName());
            if (nextStations.isEmpty()) break;

            int randIndex = rand.nextInt(nextStations.size());
            Station[] nextStationsArr = nextStations.keySet().toArray(new Station[0]);
            Station nextStation = nextStationsArr[randIndex];
            route.add(nextStation);
            currConnections++;
        }


        //calculating distance from home to end
        this.destination = route.getLast();
        this.distanceToDestination = 0;
        for (int i = 0; i < route.size()-1; i++) {
            Station station1 = route.get(i);
            Station station2 = route.get(i + 1);
            Map<Station, Integer> connections = Station.connections.get(station1.getName());
            distanceToDestination += connections.get(station2);
        }
        this.route = route;

        //after determining route, saving distances between stations in a list
        for (int i = 0; i < route.size() - 1; i++) {
            Station currStation = route.get(i);
            Station nextStation = route.get(i + 1);
            int distance = Station.connections.get(currStation.getName()).get(nextStation);
            distances.add(distance);
        }
    }



    public Station getHome() {
        return home;
    }


    public Station getDestination() {
        return destination;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMaxRailroadCars() {
        return maxRailroadCars;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getID() {
        return ID;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getMaxElectricalGrids() {
        return maxElectricalGrids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHome(Station home) {
        this.home = home;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



    public void setMaxElectricalGrids(int maxElectricalGrids) {
        this.maxElectricalGrids = maxElectricalGrids;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Station getRoute(int index) {
        return route.get(index);
    }

    public double getDistanceToDestination() {
        return distanceToDestination;
    }

    public void setDistanceToDestination(double distanceToDestination) {
        this.distanceToDestination = distanceToDestination;
    }

    public double getDistances(int index) {
        return distances.get(index);
    }

    public LinkedList<Integer> getDistances() {
        return distances;
    }

    public void setRoute(LinkedList<Station> route) {
        this.route = route;
    }

    public void setDistances(LinkedList<Integer> distances) {
        this.distances = distances;
    }

    public double getWeight(){
        return 200;
    }

}
