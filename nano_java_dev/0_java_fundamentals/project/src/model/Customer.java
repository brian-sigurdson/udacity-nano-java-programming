package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String emailRegex = "(.*)@(.*).com";

    public Customer(String firstName, String lastName, String email) {
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    @Override
    public String toString() {
        return "First name: " + firstName + "; Last name: " + lastName + "; email: " + email;
    }
}
