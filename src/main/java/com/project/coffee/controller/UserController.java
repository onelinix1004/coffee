package com.project.coffee.controller;

import com.project.coffee.dto.UserDTO;
import com.project.coffee.service.UserService;
import com.project.coffee.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all users from the repository.
     *
     * @return a ResponseEntity containing a list of UserDTO objects representing all users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by its ID from the repository.
     *
     * @param userId the ID of the user to retrieve
     * @return a ResponseEntity containing the UserDTO object if found,
     *         or a 404 response if not found
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    /**
     * Creates a new user and stores it in the repository.
     *
     * @param userDTO the UserDTO to create a user from
     * @return a ResponseEntity containing the newly created UserDTO,
     *         or a 400 response if the request body is invalid
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.status(201).body(createdUser);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing user in the repository with the provided details.
     *
     * @param userId the ID of the user to update
     * @param userDTO the UserDTO containing the updated user details
     * @return a ResponseEntity containing the updated UserDTO if found,
     *         or a 404 response if not found
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Deletes a user from the repository by its ID.
     *
     * @param userId the ID of the user to delete
     * @return a ResponseEntity with no content if the deletion is successful,
     *         or a 404 response if the user with the given ID is not found
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}