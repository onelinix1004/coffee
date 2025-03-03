package com.project.coffee.repository;


import com.project.coffee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Retrieves a user from the repository by their email address.
     *
     * @param email the email address to search for
     * @return a User object representing the user with the given email address, or null if no user is found
     */
    User findByEmail(String email);

    /**
     * Retrieves a user from the repository by their username.
     *
     * @param username the username to search for
     * @return a User object representing the user with the given username, or null if no user is found
     */
    User findByUsername(String username);

    /**
     * Retrieves a list of users from the repository whose role matches the given role.
     *
     * @param role the role to search for
     * @return a list of User objects representing the users with the given role, or an empty list if no users are found
     */
    List<User> findByRole(String role);
}