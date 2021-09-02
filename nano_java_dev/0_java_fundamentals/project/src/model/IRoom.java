package model;

import java.time.LocalDate;

public interface IRoom {
    public Integer getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public Boolean isFree();
    public void addReservation(Reservation reservation);
    public boolean isAvailable(LocalDate checkInDate, LocalDate checkOutDate);
}
