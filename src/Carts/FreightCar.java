package Carts;

import Carts.RailroadCar;

abstract class FreightCar extends RailroadCar {
    private String shipper;

    public FreightCar(double netWeight, String shipper) {
        super(netWeight);
        this.shipper = shipper;
    }


}
