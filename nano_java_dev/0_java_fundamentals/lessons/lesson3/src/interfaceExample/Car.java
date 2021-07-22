package interfaceExample;

public class Car implements Vehicle {
    private String type;
    private String speed;
    private String color;
    private String direction;

    public Car (String type, String speed, String color, String direction) {
        this.type = type;
        this.speed = speed;
        this.color = color;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getSpeed() {
        return speed;
    }

    @Override
    public String getColor() {
        return color;
    }
}
