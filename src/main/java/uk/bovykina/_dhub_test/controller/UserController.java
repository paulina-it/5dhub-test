package uk.bovykina._dhub_test.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uk.bovykina._dhub_test.model.dto.UserDto;
import uk.bovykina._dhub_test.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/{lastName}")
    public UserDto getUserByLastName(@PathVariable String lastName) {
        return userService.getUserByLastName(lastName);
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

}
