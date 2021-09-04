package model;

public class FreeRoom extends Room {

    // TODO:  figure out what to do with this class
    /*
    Looking on the forum, I found this:  https://knowledge.udacity.com/questions/534314
    I skimmed it, but I think it corroborates my thought that it doesn't really fit in well.

    This one isn't directly on FreeRoom, but read it all, because it make use of the free room class a lot to
    explain some concepts, so perhaps I'll get a better understanding of how it is used.
    https://knowledge.udacity.com/questions/570279

    ** Actually, I'm starting to think that this really is suppose to be a "free" room, as opposed to
    a premium or economy room.  If so, i think something more in line to an actual hotel usage, such as
    a "premium" room, would have been a better choice.

    See if there are any other comments that clarify this class as well.

    Otherwise, I don't think it is really used.
     */
    public FreeRoom(Integer roomNumber, Double price, Integer roomType) {
        super(roomNumber, 0.0, roomType);
    }

    // TODO: Double check that this is in line with the example output
    @Override
    public String toString() {
        return "FreeRoom: " + super.toString();
    }
}
