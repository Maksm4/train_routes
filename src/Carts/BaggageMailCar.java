package Carts;



public class BaggageMailCar  extends RailroadCar {
    private int numberOfBaggage;
    private double weightOfBaggage;

    public BaggageMailCar(double netWeight, int numberOfBaggage) {
        super(netWeight);
        this.numberOfBaggage = numberOfBaggage;
        this.weightOfBaggage = numberOfBaggage * 5;

    }

    public int getNumberOfBaggage() {
        return numberOfBaggage;
    }

    @Override
    public boolean hasElectricalGrid() {
        return false;
    }

    @Override
    public double getTotalWeight() {
        return super.getNetWeight()+ this.weightOfBaggage;
    }


    // routine check if nobody has stolen a luggage
    public boolean checkOfBaggage(){
        return true;
    }

    //if ppl go in and out we need to - or +
    public void addSubBaggage(){
        int x = 10;
        numberOfBaggage -= x;
        weightOfBaggage -= x*20;
    }

    @Override
    public String toString() {
        return  " ID: " + super.getID() +
                " WEIGHT: " + this.getTotalWeight() +
                " TYPE: BaggageCar| ";
    }
}

