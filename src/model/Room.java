package model;

public class Room implements IRoom{
    public Object getRoomNumber() {
        System.out.println("get room Num");
        return null;
    }
    public void getRoomPrice() {
        System.out.println("Room Price");
    }
    public void getRoomType() {
        System.out.println("Type of room");

    }
    public boolean isFree() {
        System.out.println("Free");
        return false;
    }

    private String roomNumber;
    protected Double price;

    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + enumeration +
                '}';
    }
}
