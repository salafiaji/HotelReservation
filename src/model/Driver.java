package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("First", "Second", "aji@gmail.com");
        System.out.println(customer);

        /*//Testing output of Room class
        Room hy = new Room("20", 10.0, RoomType.SINGLE);
        System.out.println(hy);*/
    }
}
