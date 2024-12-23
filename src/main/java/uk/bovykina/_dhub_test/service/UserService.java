package uk.bovykina._dhub_test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.bovykina._dhub_test.model.dto.UserDto;
import uk.bovykina._dhub_test.model.entity.User;
import uk.bovykina._dhub_test.repo.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserDto getUserByLastName(String lastName) {
        User user = userRepo.findByLastName(lastName)
                .orElseThrow(() -> new IllegalArgumentException("User with last name " + lastName + " was not found."));
        return toDto(user);
    }

    public void createUser(UserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("User data cannot be null.");
        }

        Optional<User> existingUser = userRepo.findByLastName(userDto.getLastName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with last name " + userDto.getLastName() + " already exists.");
        }

        User user = dtoToEntity(userDto);
        userRepo.save(user);
    }

    private User dtoToEntity(UserDto userDto) {

        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phone(userDto.getPhone())
                .build();
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }
}
