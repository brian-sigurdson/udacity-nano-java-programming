package model;

import java.util.regex.Pattern;

/**
 * A class to model hotel customers.
 *
 * @author Brian Sigurdson
 */
public class Customer {
    private static final String emailRegex = "^(.+)@(.+).com$";
    private static final Pattern pattern = Pattern.compile(emailRegex);

    private final String firstName;
    private final String lastName;
    private final String email;


    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException{

        if (Customer.pattern.matcher(email).matches()) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        } else {
            throw new IllegalArgumentException();
        }
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

    /**
     * A method for the default printing of an object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[First name: " + firstName + ", Last name: " + lastName + ", email: " + email + "]";
    }

    /**
     * A method to test for object equality
     * @param object
     * @return true or false
     */
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

    /**
     * A method to determine an object's hashcode
     * @return the object's hashcode
     */
    @Override
    public int hashCode () {
        // The recipe for a proper hash code, from Effective Java 3rd ed.
        int result = email.hashCode();
        result += 31 + firstName.hashCode();
        result += 31 + lastName.hashCode();
        return result;
    }
}
