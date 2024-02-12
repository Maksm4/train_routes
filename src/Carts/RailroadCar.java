package Carts;

import java.util.Comparator;

public abstract class RailroadCar implements WeightInterface, CarInterface, Comparable<RailroadCar> {

    private double netWeight;
    private String securityInfo;

    private boolean electricalGrid;

    private final int ID;
    private static int idCounter;
    private boolean  isAdded;



    public RailroadCar(double netWeight) {
        this.netWeight = netWeight;
        ID = idCounter++;
        isAdded = false;
    }

    public double getNetWeight() {
        return netWeight;
    }


    public int isElectricalGrid() {
        if (electricalGrid) return 1;
        return 0;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }


    public void setElectricalGrid(boolean electricalGrid) {
        this.electricalGrid = electricalGrid;
    }

    public int getID() {
        return ID;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    @Override
    public int compareTo(RailroadCar otherCar) {
        return Double.compare(this.getTotalWeight(),otherCar.getTotalWeight());
    }

    @Override
    public String toString() {
        return " ID: " + this.ID +
                " WEIGHT: " + getTotalWeight() + "| ";
    }

    public String getSecurityInfo() {
        return "the train has lost control, the speed is over 200 km/h, use emergency brakes";
    }
}
