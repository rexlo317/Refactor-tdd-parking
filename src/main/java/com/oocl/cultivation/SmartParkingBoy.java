package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy {

    private ParkingLot[] parkingLots;
    private String lastErrorMessage;
    private int parkingLotNumber;
    private Manager manager;

    public SmartParkingBoy(ParkingLot[] parkingLots){
        super(parkingLots);
        this.parkingLots = parkingLots;
        this.parkingLotNumber = parkingLots.length;
    }

    public SmartParkingBoy(ParkingLot[] parkingLots, Manager manager) {
        super(parkingLots, manager);
        this.parkingLots = parkingLots;
        this.parkingLotNumber = parkingLots.length;
        this.manager = manager;
    }
    @Override
    public ParkingTicket park(Car car) {
        for (int index = 0; index<this.parkingLotNumber; index++) {
            if (this.parkingLots[index].getAvailableParkingPosition() >= 1) {
               break;
            }
            if (index >= this.parkingLotNumber-1){
                    this.lastErrorMessage = "The parking lot is full.";
                    if(manager!=null)
                        this.manager.setErrorMessage("The parking lot is full.");
                    return null;
            }
        }
        ParkingTicket parkingTicket = checkedLotsParkNow(car);
        return parkingTicket;
    }

    private ParkingTicket checkedLotsParkNow(Car car){
        int maxEmptyCapacityLotNumber = getMax();
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingLots[maxEmptyCapacityLotNumber].getCars().put(parkingTicket, car);
        if(manager!=null)
            this.manager.setErrorMessage(null);
        this.lastErrorMessage = null;
        return parkingTicket;
    }

    private int getMax(){
        int maxNumber = 0;
        for (int index = 1; index<this.parkingLotNumber; index++){
            if(this.parkingLots[maxNumber].getAvailableParkingPosition() < this.parkingLots[index].getAvailableParkingPosition())
                maxNumber = index;
        }
        return maxNumber;
    }
}
