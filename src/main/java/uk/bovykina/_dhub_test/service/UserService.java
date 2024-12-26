package uk.bovykina._dhub_test.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.bovykina._dhub_test.model.dto.UserDto;
import uk.bovykina._dhub_test.mapper.UserMapper;
import uk.bovykina._dhub_test.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInt {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserDto getUserByLastName(String lastName) {
        logger.info("Fetching user by last name: {}", lastName);
        return userRepo.findByLastName(lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with last name: {}", lastName);
                    return new IllegalArgumentException("User not found");
                });
    }

    public void createUser(UserDto userDto) {
        logger.info("Creating user with last name: {}", userDto.getLastName());
        userRepo.save(userMapper.toEntity(userDto));
        logger.info("User created successfully: {}", userDto.getLastName());
    }
}
