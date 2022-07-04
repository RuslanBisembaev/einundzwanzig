package de.htwberlin.webtech.einundzwanzig.web;

import de.htwberlin.webtech.einundzwanzig.service.UserService;
import de.htwberlin.webtech.einundzwanzig.web.api.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("should return found users from user service")
    void returnFoundUserFromUserService() throws Exception {
        var users = List.of(
                new User(1, "Player", 10, 5, 4, 2),
                new User(2, "Player2", 20, 10, 8, 4));
        doReturn(users).when(userService).findAll();

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("Player"))
                .andExpect(jsonPath("$[0].coins").value(10))
                .andExpect(jsonPath("$[0].wins").value(5))
                .andExpect(jsonPath("$[0].losses").value(4))
                .andExpect(jsonPath("$[0].draws").value(2))
                .andExpect(jsonPath("$[1].username").value("Player2"))
                .andExpect(jsonPath("$[1].coins").value(20))
                .andExpect(jsonPath("$[1].wins").value(10))
                .andExpect(jsonPath("$[1].losses").value(8))
                .andExpect(jsonPath("$[1].draws").value(4));
    }
    @Test
    @DisplayName("should return 404 if user is not found")
    void return404IfUserNotFound() throws Exception {
        doReturn(null).when(userService).findById(anyLong());

        mockMvc.perform(get("/api/v1/users/123"))
                .andExpect(status().isNotFound());
    }
}
