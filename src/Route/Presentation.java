package Route;

import Carts.*;


import java.util.*;

public class Presentation {
    public static void main(String[] args) {


        //creating 100 stations
        for (int i = 0; i < 100; i++) {
            new Station("Station" +i);
        }

        //creating connections between them
        for (Station station : Station.stations) {
            station.createConnection();
        }

        //creating 25 TrainSets
        for (int i = 0; i <25; i++) {
            Random rand = new Random();
            new TrainSet(new Locomotive("train" + i,Station.stations.get(i),rand.nextDouble(100) + 50)).start();

        }


        //creating 250 Railroad cars randomly
        for (int i = 0; i < 250; i++) {
            Random rand1 = new Random();
            Random rand2 = new Random();
           int RailroadType = rand1.nextInt(10);
           RailroadCar car = null;
           switch (RailroadType){
               case 0-> car = new PassengerCar(rand2.nextInt(70)+30, rand2.nextInt(120)+80);

               case 1 -> car = new PostOfficeCar(rand2.nextInt(70)+ 30,rand2.nextInt(300) + 100);

               case 2 -> car = new BaggageMailCar(rand2.nextInt(100) +50,rand2.nextInt(800) + 200);

               case 3 -> car = new RestaurantCar(rand2.nextInt(100) +50,rand2.nextInt(20) + 20,100);

               case 4 -> car = new BasicFreightCar.RefrigeratedCar(rand2.nextInt(200) +100,"shipper nr " +i);

               case 5 -> car = new BasicFreightCar.LiquidMaterialsCar(rand2.nextInt(100) +50,"shipper nr " +i);

               case 6 -> car = new BasicFreightCar.GaseousMaterialsCar(rand2.nextInt(10),"shipper nr " +i);

               case 7 -> car = new HeavyFreightCar.ExplosivesCar(rand2.nextInt(500)+ 200,"shipper nr " +i, rand2.nextInt(50)+10,true);

               case 8 -> car = new HeavyFreightCar.ToxicMaterialsCar(rand2.nextInt(500)+ 200,"shipper nr " +i, rand2.nextInt(50)+10,rand2.nextBoolean());

               case 9 -> car = new HeavyFreightCar.LiquidToxicMaterialCar(rand2.nextInt(100)+ 200,"shipper nr " +i, rand2.nextInt(50)+10);


           }
            for (TrainSet train :TrainSet.trains) {
                if (train.isMaxRailroadCars())
                    continue;

                if (car instanceof HeavyFreightCar){
                    train.getLocomotive().setSpeed(train.getLocomotive().getSpeed()- (train.getLocomotive().getSpeed() * ((HeavyFreightCar) car).getSlowsDown()));
                }
                train.addRailroadCar(car);
                }
            }

        //thread for saving to file, infinite loop
        Thread saveTrainSetsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    TrainSet.saveToFile();
                } catch (InterruptedException exe) {
                    exe.printStackTrace();
                }
            }
        });
        saveTrainSetsThread.start();

    }
}
