package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSmartBoyPark {
    @Test
    void should_all_car_park_to_lot_2() {
        final int capacity = 2;
        final int lotNumbers = 2;
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        parkingLots[0] = new ParkingLot(capacity);
        parkingLots[1] = new ParkingLot(10);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for (int index=0; index<lotNumbers*capacity; index++)
            smartParkingBoy.park(new Car());

        assertEquals(0, parkingLots[0].getCars().size());
        assertEquals(4, parkingLots[1].getCars().size());
    }

    @Test
    void should_3_cars_park_to_lot_2_then_lot_1() {
        final int capacity = 2;
        final int lotNumbers = 2;
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        parkingLots[0] = new ParkingLot(capacity);
        parkingLots[1] = new ParkingLot(5);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for (int index=0; index<lotNumbers*capacity; index++)
            smartParkingBoy.park(new Car());

        assertEquals(1, parkingLots[0].getCars().size());
        assertEquals(3, parkingLots[1].getCars().size());
    }

}
