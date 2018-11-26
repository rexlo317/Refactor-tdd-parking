package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TwoLotsTest {

    @Test
    void should_park_to_second_lot() {
        final int capacity = 1;
        final int lotNumbers = 2;
        Car car = new Car();
        Car car2 = new Car();
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        for (int index=0; index<lotNumbers; index++) {
            parkingLots[index] = new ParkingLot(capacity);
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ParkingTicket ticket = parkingBoy.park(car);
        ParkingTicket ticket2 = parkingBoy.park(car2);
        Car fetched = parkingBoy.fetch(ticket);
        Car fetched2 = parkingBoy.fetch(ticket2);

        assertSame(fetched, car);
        assertSame(fetched2, car2);
    }
    @Test
    void should_second_lot_full() {
        final int capacity = 1;
        final int lotNumbers = 2;
        Car car = new Car();
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        for (int index=0; index<lotNumbers; index++) {
            parkingLots[index] = new ParkingLot(capacity);
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_second_lot_2_cars() {
        final int capacity = 2;
        final int lotNumbers = 2;
        ParkingLot[] parkingLots = new ParkingLot[lotNumbers];
        for (int index=0; index<lotNumbers; index++) {
            parkingLots[index] = new ParkingLot(capacity);
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        for (int index=0; index<lotNumbers*capacity; index++)
            parkingBoy.park(new Car());

        assertEquals(2, parkingLots[0].getCars().size());
        assertEquals(2, parkingLots[1].getCars().size());
    }

}
