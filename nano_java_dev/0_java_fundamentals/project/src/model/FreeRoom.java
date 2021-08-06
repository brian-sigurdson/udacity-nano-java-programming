package model;

public class FreeRoom extends Room {

    // TODO:  figure out what to do with this class
    /*
    I know that the intent is to practice inheritance, but doesn't really make sense.
    You subclass to create something with some specific behavior, but a room being free in this context suggests that
    it is simply not reserved.

    This seems clunky and either not thought out or we've not been given enough info.

    Check the moderators forum for more info.

    It seems to me that a room's status as free, depends solely on a specific date.
    It could be reserved today and free tomorrow.
     */
    public FreeRoom() {
        super();
        this.price = 0.0;
    }

    // TODO: Double check that this is in line with the example output
    @Override
    public String toString() {
        return super.toString();
    }
}
