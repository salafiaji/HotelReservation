package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class ReservationService {
    static Collection<Reservation> allReservations = new ArrayList<>();
    static Collection<IRoom> allRooms = new ArrayList<>();

    public static void addRoom(IRoom room) {
        if(room == null) return;
        allRooms.add(room);
    }

    public static IRoom getARoom(String roomId) {
        if(allRooms == null || allRooms.isEmpty()) return null;
        for(IRoom room: allRooms) {
            if(Objects.equals(room.getRoomNumber(), roomId)) {
                return room;
            }
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.add(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>(allRooms);
        for(Reservation reservation: allReservations) {
            if(reservation.isReserved(checkInDate, checkOutDate)) availableRooms.remove(reservation.getRoom());
        }
        return availableRooms.isEmpty() ? null: availableRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> availableReservations = new ArrayList<>();
        if(allReservations.isEmpty()) return null;
        for(Reservation reservation: allReservations) {
            if(reservation.getCustomer() == customer) availableReservations.add(reservation);
        }
        return availableReservations.isEmpty() ? null : availableReservations;
    }

    public static Collection<IRoom> getAllRooms() {
        return allRooms;
    }

    public static void printAllReservation() {
        System.out.println(String.format("Printing all reservations [%d]", allReservations.size()));
        for(Reservation reservation: allReservations) {
            System.out.println(reservation);
        }
    }
}