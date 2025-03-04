package com.project.coffee.service.impl;

import com.project.coffee.entity.User;
import com.project.coffee.exception.BadRequestException;
import com.project.coffee.exception.ResourceNotFoundException;
import com.project.coffee.repository.UserRepository;
import com.project.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of all users from the repository.
     *
     * @return a list of UserDTO objects representing all users
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by their ID from the repository.
     *
     * @param userId the ID of the user to retrieve
     * @return an Optional containing the UserDTO object if found, empty otherwise
     */
    @Override
    public Optional<UserDTO> getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(this::convertToDTO);
    }

    /**
     * Creates a new user and stores it in the repository.
     *
     * @param userDTO the UserDTO to create a user from
     * @return a UserDTO representing the newly created user
     * @throws BadRequestException if the user creation fails due to invalid input
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new BadRequestException("User email is required");
        }
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw new BadRequestException("Username is required");
        }
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new BadRequestException("Username already exists");
        }
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Updates an existing user in the repository with the provided details.
     *
     * @param userId the ID of the user to update
     * @param userDTO the UserDTO containing the updated user details
     * @return a UserDTO representing the updated user
     * @throws com.project.coffee.exception.ResourceNotFoundException if no user is found with the given ID
     */
    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setLoyaltyPoints(userDTO.getLoyaltyPoints());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    /**
     * Deletes a user from the repository by their ID.
     *
     * @param userId the ID of the user to delete
     * @throws com.project.coffee.exception.ResourceNotFoundException if no user is found with the given ID
     */
    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setLoyaltyPoints(user.getLoyaltyPoints());
        return dto;
    }

    /**
     * Converts a UserDTO into a User entity.
     *
     * @param dto The UserDTO object to convert
     * @return The converted User entity
     */
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setLoyaltyPoints(dto.getLoyaltyPoints());
        return user;
    }
}