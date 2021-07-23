package exceptionExample;

public class Phone {
    private String phoneType;
    private String phoneNumber;

    public Phone(String phoneType, String phoneNumber) {

        if (phoneType == null || phoneNumber == null){
            throw new IllegalArgumentException("The type or number cannot be null.");
        } else {
            this.phoneType = phoneType;
            this.phoneNumber = phoneNumber;
        }

    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "Phone type: " + phoneType + "; Phone number: " + phoneNumber;
    }
}
