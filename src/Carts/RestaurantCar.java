package Carts;

import Carts.RailroadCar;

public class RestaurantCar extends RailroadCar {
    private final int numberOfTables;
    private final double tablewareWeight;
     private double foodSupplies;

    public RestaurantCar(double netWeight, int numberOfTables, double foodSupplies) {
        super(netWeight);
        this.numberOfTables = numberOfTables;
        this.tablewareWeight = numberOfTables * 10;
        this.foodSupplies = foodSupplies;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public double getFoodSupplies() {
        return foodSupplies;
    }

    @Override
    public boolean hasElectricalGrid() {
        return true;
    }

    @Override
    public double getTotalWeight() {
        return super.getNetWeight() + numberOfTables * 30 +  tablewareWeight;
    }

    //when at station cant eat
    public void stopEating(){

    }

    public void eating(){

    }

    public void refillFoodSupplies(){
        foodSupplies+= 100;
    }

    @Override
    public String toString() {
        return  " ID: " + super.getID() +
                " WEIGHT: " + this.getTotalWeight() +
                " TYPE: RestaurantCar| ";
    }
}
