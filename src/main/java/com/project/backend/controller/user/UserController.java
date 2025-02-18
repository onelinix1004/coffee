package com.project.backend.controller.user;

import com.project.backend.dto.user.UserDTO;
import com.project.backend.service.user.UserService;
import com.project.backend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Handles the HTTP GET request to retrieve all users.
     *
     * @return A list of UserDTO objects representing all users.
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        // Delegate the call to the userService to fetch all users
        return userService.getAllUsers();
    }

    /**
     * Handles the HTTP GET request to retrieve a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return A ResponseEntity containing a UserDTO if the user is found, or a 404 status if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // Attempt to find the user by ID using the UserService
        Optional<UserDTO> user = userService.getUserById(id);

        // Return the user if found, otherwise return a 404 Not Found response
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles the HTTP POST request to create a new user.
     *
     * @param user The user information to be added to the database.
     * @return A UserDTO object representing the newly created user.
     */
    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        // Delegate the call to the userService to create the user
        return userService.createUser(user);
    }

    /**
     * Handles the HTTP PUT request to update an existing user.
     *
     * @param id   The ID of the user to be updated.
     * @param user The user information to be updated in the database.
     * @return A UserDTO object representing the updated user.
     */
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        // Delegate the call to the UserService to update the user
        return userService.updateUser(id, user);
    }

    /**
     * Handles the HTTP DELETE request to delete a user.
     * <p>
     * When this endpoint is called, it will delete the user with the specified ID.
     *
     * @param id The ID of the user to be deleted.
     * @return An empty response with a 204 No Content status if the user is found, or a 404 Not Found response if not.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

