package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuperSmartBoyPark {

    @Test
    void should_park_to_lot1_then_lot2_even_lot1_has_more_empty_place() {
        final int lotNumbers = 2;
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        parkingLots[0] = new ParkingLot(10);
        parkingLots[1] = new ParkingLot(1);
        ParkingBoy smartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        //Now Lot1 has 9 empty space with 90% empty ratio.
        //Lot2 has 1 empty space with 100% empty ratio.
        //Should park to lot 2.
        smartParkingBoy.park(new Car());

        assertEquals(1, parkingLots[1].getCars().size());
    }

}
