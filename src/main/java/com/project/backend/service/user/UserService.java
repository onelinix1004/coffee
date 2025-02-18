package com.project.backend.service.user;


import com.project.backend.dto.user.UserDTO;
import com.project.backend.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * This method returns all users in the database.
     *
     * @return A list of UserDTO objects, which represent the users in the database.
     */
    List<UserDTO> getAllUsers();

    /**
     * This method returns a specific user, identified by the provided ID, if it exists in the database.
     *
     * @param id The ID of the user to be retrieved.
     * @return An Optional containing a UserDTO object, which represents the user with the provided ID, if it exists in the database.
     */
    Optional<UserDTO> getUserById(Long id);

    /**
     * This method creates a new user in the database.
     *
     * @param user The user information to be added to the database.
     * @return A UserDTO object, which represents the newly created user.
     */
    UserDTO createUser(User user);

    /**
     * This method updates an existing user in the database.
     *
     * @param id   The ID of the user to be updated.
     * @param user The user information to be updated in the database.
     * @return A UserDTO object, which represents the updated user.
     */
    UserDTO updateUser(Long id, User user);

    /**
     * This method deletes a user from the database.
     *
     * @param id The ID of the user to be deleted.
     */
    void deleteUser(Long id);
}

