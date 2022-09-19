package model;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "events")
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private List<Event> events;
    private String profilePic;
    private String password;
    private UserRole userRole;

}
