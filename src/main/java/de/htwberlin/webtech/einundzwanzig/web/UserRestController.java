package de.htwberlin.webtech.einundzwanzig.web;

import de.htwberlin.webtech.einundzwanzig.service.UserService;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}
