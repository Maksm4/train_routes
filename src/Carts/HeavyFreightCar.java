package Carts;

public abstract class HeavyFreightCar extends FreightCar {
    private final double maxCapacity = 90_500;
    private int slowsDown;

    public HeavyFreightCar(double netWeight, String shipper, int slowsDown) {
        super(netWeight, shipper);
        this.slowsDown = slowsDown;
    }

    public int getSlowsDown() {
        return slowsDown / 100;
    }

    //1 type
    public static class ExplosivesCar extends  HeavyFreightCar {
        private boolean restricted;

        public ExplosivesCar(double netWeight, String shipper, int slowsDown,boolean restricted) {
            super(netWeight, shipper, slowsDown);
            this.restricted = restricted;
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
        public String getSecurityInfo() {
            return "Watch out, explosive materials, make sure to secure the transported materials";
        }

        @Override
        public String toString() {
            return  " ID: " + super.getID() +
                    " WEIGHT: " + this.getTotalWeight() +
                    " TYPE: ExplosiveCar| ";
        }


    }

    //2 type
    public static class ToxicMaterialsCar extends  HeavyFreightCar {
        private boolean dangerous;

        public ToxicMaterialsCar(double netWeight, String shipper, int slowsDown, boolean dangerous) {
            super(netWeight, shipper, slowsDown);
            this.dangerous = dangerous;
        }

        @Override
        public boolean hasElectricalGrid() {
            return false;
        }

        @Override
        public double getTotalWeight() {
            return super.getNetWeight();
        }

        public boolean isDangerous() {
            return dangerous;
        }

        @Override
        public String toString() {
            return  " ID: " + super.getID() +
                    " WEIGHT: " + this.getTotalWeight() +
                    " TYPE: ToxicMaterialsCar| ";
        }
    }

    //3 type
    public static class LiquidToxicMaterialCar extends  HeavyFreightCar {



        public LiquidToxicMaterialCar(double netWeight, String shipper, int slowsDown) {
            super(netWeight, shipper, slowsDown);

        }

        @Override
        public boolean hasElectricalGrid() {
            return false;
        }

        @Override
        public double getTotalWeight() {
            return super.getNetWeight();
        }
    }

    @Override
    public String toString() {
        return  " ID: " + super.getID() +
                " WEIGHT: " + this.getTotalWeight() +
                " TYPE: LiquidToxicMaterialsCar| ";
    }

}
