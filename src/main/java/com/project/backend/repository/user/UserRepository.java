package com.project.backend.repository.user;

import com.project.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their username.
     *
     * @param username The username to search for.
     * @return An Optional containing a User if found, or an empty Optional if not.
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email.
     *
     * @param email The email to search for.
     * @return An Optional containing a User if found, or an empty Optional if not.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the specified username.
     *
     * @param username The username to check for existence.
     * @return true if a user with the specified username exists, false otherwise.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists with the specified email.
     *
     * @param email The email to check for existence.
     * @return true if a user with the specified email exists, false otherwise.
     */
    boolean existsByEmail(String email);
}
