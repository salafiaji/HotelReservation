package view;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainMenu extends Menu {

    public MainMenu() {
        super("Main");
        addMenuItems();
    }

    @Override
    public void start() {
        int option;
        do {
            display(this);
            option = getMenuOption("Please select a number for the menu option");

        } while(executeOption(option));
    }
    @Override
    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    @Override
    public boolean executeOption(int option) {
        switch(option) {
            case 1:
                makeAReservation();
                break;
            case 2:
                seeMyReservations();
                break;
            case 3:
                createAnAccount();
                break;
            case 4:
                openAdmin();
                break;
            case 5:
                return false;
        }
        return true;
    }

    private void openAdmin() {
        AdminMenu admin = new AdminMenu();
        admin.start();

    }

    private String createAnAccount() {
        String firstName, lastName, email;
        Customer cust;
        do {
            email = getEmail("Enter Email Format: name@domain.com");
            cust = HotelResource.getCustomer(email);
            if(HotelResource.getCustomer(email) != null) {
                log("A customer already exists with the given email. Please enter a different email.");
                log(cust.toString());
            }
        } while(cust != null);
        firstName = getString("Enter your first name");
        lastName = getString("Enter your last name");
        HotelResource.createACustomer(email, firstName, lastName);
        return email;
    }

    private void seeMyReservations() {
        String email;
        email = getEmail("Enter Email Format: name@domain.com");
        if(HotelResource.getCustomer(email) == null) {
            log("A customer with the given email doesn't exist");
            return;
        }
        displayCollection(HotelResource.getCustomerReservations(email), null);
    }
    public void addMenuItems() {
        menuItems.add(new MenuItem("Find and reserve a room"));
        menuItems.add(new MenuItem("See my reservations"));
        menuItems.add(new MenuItem("Create an account"));
        menuItems.add(new MenuItem("Admin"));
        menuItems.add(new MenuItem("Exit"));
    }

    private void makeAReservation() {
        Date checkInDate, checkOutDate;
        String email, roomNumber;
        Customer customer;
        IRoom room;
        String message = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        checkInDate = Menu.getDate("Enter CheckIn Date mm/dd/yyyy example 02/12/2020");
        checkOutDate = Menu.getDate("Enter CheckOut Date mm/dd/yyyy example 02/12/2020");
        Collection<IRoom> rooms = HotelResource.findARoom(checkInDate, checkOutDate);
        if(rooms == null) {
            log("No room available for the given dates");
            checkInDate = Menu.addDays(checkInDate, 7);
            checkOutDate = Menu.addDays(checkOutDate, 7);
            rooms = HotelResource.findARoom(checkInDate, checkOutDate);
            if(rooms == null)
                return;
            message = String.format("Recommended Rooms for %s to %s", formatter.format(checkInDate), formatter.format(checkOutDate));
        }
        Menu.displayCollection(rooms, message);
        if (getBooleanInput("Would you like to book a room?")) {
            if (getBooleanInput("Do you have an account with us?")) {
                email = getEmail("Enter Email format: name@domain.com");
                do {
                    customer = HotelResource.getCustomer(email);
                    if (customer == null)
                        log("404 NOT FOUND.");
                } while (customer == null);
            }
            else {
                email = createAnAccount();
            }
            do {
                roomNumber = getString("What room number would you like to reserve?");
                room = HotelResource.getRoom(roomNumber);
                if (room == null)
                    log("The given room number doesn't exist.");
                else if (!rooms.contains(room)) {
                    log("The given room is not free for the given checkIn and checkOut Date.");
                    room = null;
                }
            } while (room == null);
            Reservation reservation = HotelResource.bookARoom(email, room, checkInDate, checkOutDate);
            log(reservation.toString());
        }

    }
}