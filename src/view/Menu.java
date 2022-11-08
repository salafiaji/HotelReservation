package view;

import java.text.SimpleDateFormat;
import java.util.*;

import model.Customer;

public abstract class Menu {
    List<MenuItem> menuItems;
    String name;

    public Menu(String name) {
        this.name = name;
        this.menuItems = new ArrayList<MenuItem>();
    }

    public abstract void addMenuItems();
    public abstract List<MenuItem> getMenuItems();

    public abstract boolean executeOption(int option);
    public abstract void start();

    public static <T> void displayCollection(Collection<T> collection, String message) {
        int i = 1;
        if (collection == null || collection.isEmpty()) {
            log("Nothing to show.");
            return;
        }
        if(message != null)
            log(message);
        System.out.println("------------------------------------------");
        for(T element: collection) {
            System.out.println(String.format("%d. %s", i++, element));
        }
        System.out.println("------------------------------------------");
    }

    public static Date addDays(Date date, int numDays) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numDays);
        return cal.getTime();
    }

    public static boolean getBooleanInput(String message) {
        System.out.println(message + " y/n");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            try {
                input = scanner.nextLine().toLowerCase();
            }
            catch(Exception ex) {
                System.out.println("Invalid input. Please enter y for yes or n for no.");
            }

        }while(!(input.equals("y") || input.equals("n")));
        return input.equals("y");
    }

    public static String getEmail(String message) {
        String email = "";
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                log(message);
                email = scanner.nextLine();
            }
            catch(Exception e) {
                System.out.println("Invalid email format. Please use the format as hello@domain.com");
            }
        }while(!Customer.validateEmail(email));
        return email;
    }
    public static Date getDate(String message) {
        String dateInput;
        Date date = null;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        boolean invalid = true;
        System.out.println(message);
        while (invalid) {
            try {
                dateInput = scanner.nextLine();
                date = formatter.parse(dateInput);
                invalid = false;
            } catch (Exception e) {
                System.out.println("Please enter the valid date pattern in the format MM/dd/yyyy.");
            }
        }
        return date;
    }
    public static int getInteger(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean invalid = true;
        do {
            try {
                input = scanner.nextInt();
                invalid = false;
            }
            catch(Exception ex) {
                System.out.println("Invalid input. Please enter integer.");
            }

        }while(invalid);
        return input;
    }

    public int getMenuOption(String message) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean invalid = true;
        do {
            input = getInteger(message);
            if(!(input >= 1 && input <= menuItems.size()))
                System.out.println("Please enter a valid menu option.");
            else invalid = false;
        }while(invalid);
        return input;
    }
    public static void display(Menu menu) {
        List<MenuItem> menuItems = menu.getMenuItems();
        System.out.println(menu.name.toUpperCase() + " MENU");
        displayCollection(menuItems, null);
    }

    public static void log(String message) {
        System.out.println(message);
    }

    public static String getString(String message) {
        log(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}