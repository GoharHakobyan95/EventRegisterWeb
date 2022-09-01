package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    private int id;
    private String name;
    private String place;
    private boolean isOnline;
    private double price;
    private EventType type;
    private Date eventDate;

    public Event(String name, String place, boolean isOnline, double price, EventType type, Date eventDate) {
        this.name = name;
        this.place = place;
        this.isOnline = isOnline;
        this.price = price;
        this.type = type;
        this.eventDate = eventDate;
    }

}
