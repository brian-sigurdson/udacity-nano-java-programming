package model;

import java.util.regex.Pattern;

/**
 * A class to model hotel customers.
 *
 * @author Brian Sigurdson
 */
public class Customer {
    private static String emailRegex = "^(.+)@(.+).(.+)$";
    private static Pattern pattern = Pattern.compile(emailRegex);

    private String firstName;
    private String lastName;
    private String email;


    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException{

        if (!Customer.pattern.matcher(email).matches()) {
            throw new IllegalArgumentException();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "First name: " + firstName + "; Last name: " + lastName + "; email: " + email;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        } else if (!(object instanceof Customer)) {
            return false;
        } else {
            Customer customer = (Customer) object;
            return customer.email.equalsIgnoreCase(this.email) &&
                    customer.firstName.equalsIgnoreCase(this.firstName) &&
                    customer.lastName.equalsIgnoreCase(this.lastName);
        }
    }

    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = email.hashCode();
        result += 31 + firstName.hashCode();
        result += 31 + lastName.hashCode();
        return result;
    }
}
