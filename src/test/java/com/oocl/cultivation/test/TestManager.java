package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.Manager;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestManager {

    @Test
    void should_manager_assign_smart_boy_to_work_and_fetch_last_car() {
        ParkingLot[] parkingLots = new ParkingLot[2];
        parkingLots[0] = new ParkingLot(2);
        parkingLots[1] = new ParkingLot(10);
        Manager manager = new Manager(parkingLots);
        manager.addParkingBoy("Smart Parking Boy");
        Car car = new Car();
        ParkingTicket ticket = null;

        for (int index=0; index<4; index++)
            ticket = manager.getParkingBoy("Smart Parking Boy").park(car);

        Car fetched = manager.getParkingBoy("Smart Parking Boy").fetch(ticket);

        assertSame(fetched, car);
        assertEquals(0, parkingLots[0].getCars().size());
        assertEquals(3, parkingLots[1].getCars().size());
    }

    @Test
    void should_manager_assign_super_smart_boy_to_work_and_fetch_last_car() {
        ParkingLot[] parkingLots = new ParkingLot[2];
        parkingLots[0] = new ParkingLot(10);
        parkingLots[1] = new ParkingLot(1);
        Manager manager = new Manager(parkingLots);
        manager.addParkingBoy("Super Smart Parking Boy");
        Car car = new Car();
        ParkingTicket ticket = null;

        ticket = manager.getParkingBoy("Super Smart Parking Boy").park(car);

        //Now Lot1 has 9 empty space with 90% empty ratio.
        //Lot2 has 1 empty space with 100% empty ratio.
        //Should park to lot 2.

        ticket = manager.getParkingBoy("Super Smart Parking Boy").park(car);

        assertEquals(1, parkingLots[0].getCars().size());
        assertEquals(1, parkingLots[1].getCars().size());

        //Should get the car from lot 2
        Car fetched = manager.getParkingBoy("Super Smart Parking Boy").fetch(ticket);
        assertSame(fetched, car);
        assertEquals(0, parkingLots[1].getCars().size());
    }

    @Test
    void should_manager_park() {
        ParkingLot[] parkingLots = new ParkingLot[2];
        parkingLots[0] = new ParkingLot(1);
        Manager manager = new Manager(parkingLots);
        Car car = new Car();
        ParkingTicket ticket = manager.park(car);

        Car fetched = manager.fetch(ticket);

        assertSame(fetched, car);
    }


    @Test
    void should_manager_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        Manager manager = new Manager(parkingLot);
        manager.addParkingBoy("Parking Boy");

        manager.getParkingBoy("Parking Boy").park(new Car());
        manager.getParkingBoy("Parking Boy").park(new Car());
        String message = manager.getErrorMessage();

        assertEquals("The parking lot is full.", message);
    }

    @Test
    void should_manager_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        manager.addParkingBoy("Parking Boy");
        ParkingTicket wrongTicket = new ParkingTicket();

        manager.getParkingBoy("Parking Boy").fetch(wrongTicket);
        String message = manager.getErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_manager_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        Manager manager = new Manager(parkingLot);
        manager.addParkingBoy("Parking Boy");
        ParkingTicket wrongTicket = new ParkingTicket();

        manager.getParkingBoy("Parking Boy").fetch(wrongTicket);
        assertNotNull(manager.getParkingBoy("Parking Boy").getLastErrorMessage());

        ParkingTicket ticket =manager.getParkingBoy("Parking Boy").park(new Car());
        String message = manager.getErrorMessage();

        assertNotNull(ticket);
        assertNull(message);
    }
}
