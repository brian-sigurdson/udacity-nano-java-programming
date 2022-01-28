package creatingclass;

public class Dog {
    private String dogType;
    private String dogName;
    private String dogColor;
    private int dogAge;

    public Dog(String dogType, String dogName, String dogColor, int dogAge) {
        this.dogType = dogType;
        this.dogName = dogName;
        this.dogColor = dogColor;
        this.dogAge = dogAge;
    }

    public void setDogType(String dogType) {
        this.dogType = dogType;
    }

    public String getDogType() {
        return dogType;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogColor(String dogColor) {
        this.dogColor = dogColor;
    }

    public String getDogColor() {
        return dogColor;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    public int getDogAge() {
        return dogAge;
    }

    public String toString() {
        return "Dog type: " + dogType + " Dog name: " + dogName + " Dog color: " + dogColor + " Dog age: " + dogAge;
    }
    
}
