package Carts;

import Carts.RailroadCar;

import java.util.Random;

public class PassengerCar extends RailroadCar {

    private final int seats;
    private int seatsTaken;
    public int peopleEntered;



    public PassengerCar(double netWeight, int seats) {
        super(netWeight);
        this.seats = seats;
        this.seatsTaken =50;

    }

    public int getSeats() {
        return seats;
    }


    @Override
    public double getTotalWeight() {
        return super.getNetWeight() + this.seatsTaken * 2;
    }

    @Override
    public boolean hasElectricalGrid() {
        return true;
    }

    public void loadPassengers(){
        Random rand = new Random();
         peopleEntered = rand.nextInt(40);
        this.seatsTaken +=  peopleEntered;
    }

    public void unloadPassengers(){
        Random rand = new Random();
        this.seatsTaken -= rand.nextInt(this.seatsTaken -1);
    }

    public boolean everyoneSafe(){
        return true;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    @Override
    public String getSecurityInfo() {
        return "The train has lost control, keep calm and try " +
                "to use emergency brakes on the top right side of the window" +
                "train service is taking care of it now. ";
    }


    @Override
    public String toString() {
        return  " ID: " + super.getID() +
                " WEIGHT: " + this.getTotalWeight() +
                " TYPE: PassengerCar| ";
    }
}
