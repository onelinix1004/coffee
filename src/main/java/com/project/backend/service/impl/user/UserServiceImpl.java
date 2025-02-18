package com.project.backend.service.impl.user;



import com.project.backend.dto.user.UserDTO;
import com.project.backend.repository.user.UserRepository;
import com.project.backend.service.user.UserService;
import com.project.backend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves all users from the database.
     *
     * @return A list of UserDTO objects representing all users in the database.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        // Fetch all users from the repository
        return userRepository.findAll().stream()
                // Map each User entity to a UserDTO
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
                        user.getPhone(), user.getAddress(), user.getRole(), user.getAvatarUrl(), user.getIsActive()))
                // Collect the results into a List
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return An Optional containing a UserDTO if the user exists, or an empty Optional if not.
     */
    @Override
    public Optional<UserDTO> getUserById(Long id) {
        // Find the user by ID from the repository
        return userRepository.findById(id)
                // Map the User entity to a UserDTO
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(),
                        user.getPhone(), user.getAddress(), user.getRole(), user.getAvatarUrl(), user.getIsActive()));
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user information to be added to the database.
     * @return A UserDTO object representing the newly created user.
     */
    @Override
    public UserDTO createUser(User user) {
        // Hash the password with the configured password encoder
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Map the saved User entity to a UserDTO
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.getPhone(), savedUser.getAddress(), savedUser.getRole(), savedUser.getAvatarUrl(), savedUser.getIsActive());
    }

    /**
     * Updates an existing user in the database.
     *
     * @param id The ID of the user to be updated.
     * @param updatedUser The user information to be updated in the database.
     * @return A UserDTO object representing the updated user.
     * @throws ResourceNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public UserDTO updateUser(Long id, User updatedUser) {
        // Retrieve the user from the repository or throw an exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update the user's fields with the new information
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setAddress(updatedUser.getAddress());
        user.setRole(updatedUser.getRole());
        user.setAvatarUrl(updatedUser.getAvatarUrl());
        user.setIsActive(updatedUser.getIsActive());

        // Encode and update the password only if it's not null
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Save the updated user to the repository
        User savedUser = userRepository.save(user);

        // Return a UserDTO object to represent the updated user
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.getPhone(), savedUser.getAddress(), savedUser.getRole(), savedUser.getAvatarUrl(), savedUser.getIsActive());
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to be deleted.
     */
    @Override
    public void deleteUser(Long id) {
        // Delete the user by ID from the repository
        userRepository.deleteById(id);
    }
}

