package de.htwberlin.webtech.einundzwanzig.web;

import de.htwberlin.webtech.einundzwanzig.service.UserService;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import de.htwberlin.webtech.einundzwanzig.web.api.UserCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserRestController {


    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public ModelAndView showHelloWorld(){
        return new ModelAndView("index");
    }

    @GetMapping(path = "/api/v1/users")
    public ResponseEntity<List<User>> fetchUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/api/v1/users/{id}")
    public ResponseEntity<User> fetchUsersById(@PathVariable Long id){
        var user = userService.findById(id);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) throws URISyntaxException {
        var user = userService.create(request);
        URI uri = new URI("/api/v1/users" + user.getId());
        return ResponseEntity.created(uri).build();
    }
}
