package Carts;

public abstract class BasicFreightCar extends FreightCar implements CarInterface {

    private final double maxCapacity = 63_500;

    public BasicFreightCar(double netWeight, String shipper) {
        super(netWeight, shipper);
    }

    // 1 type
    public static class RefrigeratedCar extends BasicFreightCar  {

        public RefrigeratedCar(double netWeight, String shipper) {
            super(netWeight, shipper);
        }

        @Override
        public boolean hasElectricalGrid() {
            return true;
        }

        @Override
        public double getTotalWeight() {
            return super.getNetWeight();
        }

        @Override
        public String toString() {
            return  " ID: " + super.getID() +
                    " WEIGHT: " + this.getTotalWeight() +
                    " TYPE: RefrigeratedCar| ";
        }
    }

    // 2 type
    public static class LiquidMaterialsCar extends BasicFreightCar  {

        public LiquidMaterialsCar(double netWeight, String shipper) {
            super(netWeight,shipper);
        }

        @Override
        public boolean hasElectricalGrid() {
            return false;
        }

        @Override
        public double getTotalWeight() {
            return super.getNetWeight();
        }

        @Override
        public String toString() {
            return  " ID: " + super.getID() +
                    " WEIGHT: " + this.getTotalWeight() +
                    " TYPE: LiquidMaterialsCar| ";
        }
    }

    // 3 type
    public static class GaseousMaterialsCar extends BasicFreightCar {

        public GaseousMaterialsCar(double netWeight, String shipper) {
            super(netWeight,shipper);
        }

        @Override
        public boolean hasElectricalGrid() {
            return false;
        }

        @Override
        public double getTotalWeight() {
            return super.getNetWeight();
        }

        @Override
        public String toString() {
            return  " ID: " + super.getID() +
                    " WEIGHT: " + this.getTotalWeight() +
                    " TYPE: GaseousMaterialsCar| ";
        }
    }

}
