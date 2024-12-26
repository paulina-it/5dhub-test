package uk.bovykina._dhub_test.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.bovykina._dhub_test.model.dto.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.bovykina._dhub_test.service.UserServiceInt;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserServiceInt userService;

    @GetMapping("/{lastName}")
    public UserDto getUserByLastName(@PathVariable String lastName) {
        logger.info("Fetching user by last name: {}", lastName);
        UserDto user = userService.getUserByLastName(lastName);
        logger.info("Fetched user: {}", user);
        return user;
    }

    @PostMapping
    public void createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user: {}", userDto);
        userService.createUser(userDto);
        logger.info("User created successfully: {}", userDto.getLastName());
    }
}
