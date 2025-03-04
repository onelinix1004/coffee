package com.project.coffee.service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Retrieves a list of all users from the repository.
     *
     * @return a list of UserDTO objects representing all users
     */
    List<UserDTO> getAllUsers();

    /**
     * Retrieves a user by their ID from the repository.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the UserDTO object if found, empty otherwise
     */
    Optional<UserDTO> getUserById(Integer userId);

    /**
     * Creates a new user and stores it in the repository.
     *
     * @param userDTO the UserDTO to create a user from
     * @return a UserDTO representing the newly created user
     * @throws com.project.coffee.exception.BadRequestException if the user creation fails due to invalid input
     */
    UserDTO createUser(UserDTO userDTO);

    /**
     * Updates an existing user in the repository with the provided details.
     *
     * @param userId the ID of the user to update
     * @param userDTO the UserDTO containing the updated user details
     * @return a UserDTO representing the updated user
     * @throws com.project.coffee.exception.ResourceNotFoundException if no user is found with the given ID
     */
    UserDTO updateUser(Integer userId, UserDTO userDTO);

    /**
     * Deletes a user from the repository by their ID.
     *
     * @param userId the ID of the user to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if no user is found with the given ID
     */
    void deleteUser(Integer userId);
}