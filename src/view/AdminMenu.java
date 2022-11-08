package view;

import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminMenu extends Menu {

    public AdminMenu() {
        super("Admin");
        addMenuItems();
    }
    @Override
    public void start() {
        int option;
        do {
            display(this);
            option = getMenuOption("Please select a number for the admin menu option");
        } while(executeOption(option));
    }

    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    @Override
    public boolean executeOption(int option) {
        switch(option) {
            case 1:
                Collection<Customer> customers = AdminResource.getAllCustomers();
                displayCollection(customers, null);
                break;
            case 2:
                Collection<IRoom> rooms = AdminResource.getAllRooms();
                displayCollection(rooms,null);
                break;
            case 3:
                AdminResource.displayAllReservations();
                break;
            case 4:
                getARoomInput();
                break;
            case 5:
                return false;
        }
        return true;
    }

    private void getARoomInput() {
        List<IRoom> rooms = new ArrayList<>();
        do {
            rooms = new ArrayList<>();
            String roomId = "";
            double price;
            int roomType;
            boolean invalid = true;
            do {
                roomId = getString("Enter room number");
                if (HotelResource.getRoom(roomId) == null)
                    invalid = false;
                else
                    log("The given room already exists. Try different room number.");
            } while (invalid);

            price = getInteger("Enter price per night");
            do {
                roomType = getInteger("Enter room type: 1 for single bed, 2 for double bed");
            } while (roomType != 1 && roomType != 2);
            rooms.add(new Room(roomId, price, roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE));
            AdminResource.addRoom(rooms);
        }while(getBooleanInput("Do you want to add another room?"));

    }

    public void addMenuItems() {
        menuItems.add(new MenuItem("See all Customers"));
        menuItems.add(new MenuItem("See all Rooms"));
        menuItems.add(new MenuItem("See all Reservations"));
        menuItems.add(new MenuItem("Add a Room"));
        menuItems.add(new MenuItem("Back to Main Menu"));
    }
}