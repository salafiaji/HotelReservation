package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    private final String emailRegex = "^(.+)@(.+).com$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Enter a valid email address");
        }
    }

    public static boolean validateEmail(String email) {
        String validPattern = "^(.+)@(.+).(.+)";
        Pattern pattern = Pattern.compile(validPattern);
        return pattern.matcher(email).matches();
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
