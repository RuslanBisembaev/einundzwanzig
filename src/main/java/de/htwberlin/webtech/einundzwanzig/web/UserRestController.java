package de.htwberlin.webtech.einundzwanzig.web;

import de.htwberlin.webtech.einundzwanzig.web.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    private List<User> users;

    public UserRestController(){
        users = new ArrayList<>();
        users.add(new User(1, "Alpha", "max.mustermann@gmail.com", 1000));
        users.add(new User(2, "Beta", "lisa.s@gmail.com", 1500));
    }

    @GetMapping(path = "/api/v1/users")
    public ResponseEntity<List<User>> fetchUsers() {
        return ResponseEntity.ok(users);
    }
}
