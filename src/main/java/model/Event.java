package model;

import lombok.*;

import java.util.Date;
import java.util.List;

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
    private List<User> users;


}
