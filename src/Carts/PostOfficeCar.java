package Carts;

import Carts.RailroadCar;

public class PostOfficeCar extends RailroadCar {
    private int stockLetters;
    private int queue;
    private int inkSupply;

    public PostOfficeCar(double netWeight, int stockLetters) {
        super(netWeight);
        this.stockLetters = stockLetters;
    }


    public int getStockLetters() {
        return stockLetters;
    }

    @Override
    public boolean hasElectricalGrid() {
        return true;
    }

    @Override
    public double getTotalWeight() {
        return super.getNetWeight() + stockLetters * 10;
    }

    //refilling letters and ink at the station if 2 low
    public void refillLettersAndInk(){
        if (stockLetters <=100)
           stockLetters+= 10;
        else if (inkSupply < 50)
            inkSupply +=5;
    }

    //randomly adding or subtracting ppl
    public void queueStatus(){

    }

    @Override
    public String toString() {
        return  " ID: " + super.getID() +
                " WEIGHT: " + this.getTotalWeight() +
                " TYPE: PostOfficeCar| ";
    }
}
